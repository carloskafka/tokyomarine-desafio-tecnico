package br.carloskafka.tokyomarine.service.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String obterClientes(Model model, @RequestParam("page") Optional<Integer> pagina,
			@RequestParam("size") Optional<Integer> quantidade) {
		final int paginaAtual = pagina.orElse(1);
		final int quantidadeTotalPorPagina = quantidade.orElse(5);
		
		ResultadoListagemClienteDTO resultadoListagemClienteDto = servicoCliente.obterClientesPorPaginaEComQuantidadeTotalPorPagina(paginaAtual, quantidadeTotalPorPagina);

		if (resultadoListagemClienteDto.isSucesso()) {
			resultadoListagemClienteDto.setQuantidadeTotalPaginas(servicoCliente.obterQuantidadeTotalPaginasDeClientes(quantidadeTotalPorPagina));
			resultadoListagemClienteDto.setPaginaAtual(paginaAtual);
			resultadoListagemClienteDto.setQuantidadeTotalPorPagina(quantidadeTotalPorPagina);
			
			model.addAttribute("pageNumbers", resultadoListagemClienteDto.getQuantidadeTotalPaginas());
			model.addAttribute("resultadoListagemClienteDto", resultadoListagemClienteDto);
			model.addAttribute("urlClientesApi", ContratoRest.CLIENTES_API);
		}

		return "index.html";
	}
	
}
