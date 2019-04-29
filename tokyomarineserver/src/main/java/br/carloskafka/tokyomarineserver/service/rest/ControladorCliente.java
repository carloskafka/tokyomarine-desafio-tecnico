package br.carloskafka.tokyomarineserver.service.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.carloskafka.tokyomarinecommons.dtos.ClienteDTO;
import br.carloskafka.tokyomarinecommons.dtos.ResultadoListagemClienteDTO;
import br.carloskafka.tokyomarinecommons.service.rest.ContratoRest;
import br.carloskafka.tokyomarineserver.service.ServicoCliente;
import br.carloskafka.tokyomarineserver.service.jms.producer.ProducerFilaActiveMq;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping("/api")
public class ControladorCliente {

	@Autowired
	private ProducerFilaActiveMq producerFilaActiveMq;

	@Autowired
	private ServicoCliente servicoCliente;

	@ApiOperation(value = "Adicionar um cliente", response = ClienteDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Adicionou um novo cliente com sucesso.") })
	@PostMapping(value = ContratoRest.CLIENTE_API)
	public @ResponseBody ClienteDTO cadastrarCliente(@RequestBody ClienteDTO clienteDto) {
		producerFilaActiveMq.send(clienteDto);
		return clienteDto;
	}

	@ApiOperation(value = "Obter todos os clientes", response = ResultadoListagemClienteDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Relação de clientes obtida com sucesso.") })
	@GetMapping(value = ContratoRest.CLIENTES_API)
	public ResultadoListagemClienteDTO obterClientes(@RequestParam("page") Optional<Integer> pagina,
			@RequestParam("size") Optional<Integer> quantidade) {
		final int paginaAtual = pagina.orElse(1);
		final int quantidadeTotalPorPagina = quantidade.orElse(5);
		
		ResultadoListagemClienteDTO resultadoListagemClienteDto = servicoCliente.obterClientesPorPaginaEComQuantidadeTotalPorPagina(paginaAtual, quantidadeTotalPorPagina);

		if (resultadoListagemClienteDto.isSucesso()) {
			resultadoListagemClienteDto.atribuirQuantidadeTotalPaginas(servicoCliente.obterQuantidadeTotalPaginasDeClientes(quantidadeTotalPorPagina));
			resultadoListagemClienteDto.setPaginaAtual(paginaAtual);
			resultadoListagemClienteDto.setQuantidadeTotalPorPagina(quantidadeTotalPorPagina);
		}

		return resultadoListagemClienteDto;
	}
	
}
