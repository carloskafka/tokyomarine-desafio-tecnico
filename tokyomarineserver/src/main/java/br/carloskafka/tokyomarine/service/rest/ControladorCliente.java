package br.carloskafka.tokyomarine.service.rest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.carloskafka.tokyomarine.domain.Cliente;
import br.carloskafka.tokyomarine.dtos.ClienteDTO;
import br.carloskafka.tokyomarine.dtos.ResultadoListagemClienteDTO;
import br.carloskafka.tokyomarine.service.ServicoCliente;
import br.carloskafka.tokyomarine.service.jms.producer.ProducerFilaActiveMq;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
public class ControladorCliente {

	@Autowired
	private ProducerFilaActiveMq producerFilaActiveMq;

	@Autowired
	private ServicoCliente servicoCliente;

	@ApiOperation(value = "Adicionar um cliente", response = Cliente.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Adicionou um novo cliente com sucesso.") })
	@PostMapping(value = ContratoRest.CLIENTE_API)
	public @ResponseBody ClienteDTO cadastrarCliente(@RequestBody ClienteDTO clienteDto) {
		producerFilaActiveMq.send(clienteDto);
		return clienteDto;
	}

	@ApiOperation(value = "Obter todos os clientes", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Relação de clientes obtida com sucesso.") })
	@GetMapping(value = ContratoRest.CLIENTES_API)
	public String obterClientes(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		ResultadoListagemClienteDTO resultadoListagemClienteDto = servicoCliente.obterClientes();

		if (resultadoListagemClienteDto.isSucesso()) {
			final int currentPage = page.orElse(1);
			final int pageSize = size.orElse(5);

			PageRequest pagina = (PageRequest.of(currentPage - 1, pageSize));
			Page<ClienteDTO> paginaClienteDto = obterPaginacaoClientesDto(pagina, resultadoListagemClienteDto.getClientesDto());

			model.addAttribute("paginaClienteDto", paginaClienteDto);

			int totalPages = paginaClienteDto.getTotalPages();
			if (totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
				model.addAttribute("pageNumbers", pageNumbers);
			}

			model.addAttribute("urlClientesApi", ContratoRest.CLIENTES_API);
		}

		return "index.html";
	}

	public Page<ClienteDTO> obterPaginacaoClientesDto(Pageable pageable, List<ClienteDTO> clientesDto) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<ClienteDTO> clientesDtoPaginado;

		if (clientesDto.size() < startItem) {
			clientesDtoPaginado = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, clientesDto.size());
			clientesDtoPaginado = clientesDto.subList(startItem, toIndex);
		}

		Page<ClienteDTO> paginaClienteDto = new PageImpl<ClienteDTO>(clientesDtoPaginado, PageRequest.of(currentPage, pageSize),
				clientesDto.size());

		return paginaClienteDto;
	}
}
