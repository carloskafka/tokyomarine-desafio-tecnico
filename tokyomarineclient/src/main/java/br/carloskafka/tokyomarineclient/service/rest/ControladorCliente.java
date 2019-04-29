package br.carloskafka.tokyomarineclient.service.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.carloskafka.tokyomarineclient.service.ServicoCliente;
import br.carloskafka.tokyomarinecommons.dtos.ClienteDTO;
import br.carloskafka.tokyomarinecommons.dtos.ResultadoCadastroClienteDTO;
import br.carloskafka.tokyomarinecommons.dtos.ResultadoListagemClienteDTO;
import br.carloskafka.tokyomarinecommons.service.rest.ContratoRest;

@Controller
public class ControladorCliente {

	@Autowired
	private ServicoCliente servicoCliente;

	@PostMapping(value = ContratoRest.CLIENTE_API)
	public @ResponseBody ResultadoCadastroClienteDTO cadastrarCliente(@RequestBody ClienteDTO clienteDto) {
		return servicoCliente.cadastrarCliente(clienteDto);
	}

	@GetMapping(value = ContratoRest.CLIENTES_API)
	public String obterClientes(Model model, @RequestParam("page") Optional<Integer> pagina,
			@RequestParam("size") Optional<Integer> quantidade) {
		final int paginaAtual = pagina.orElse(1);
		final int quantidadeTotalPorPagina = quantidade.orElse(5);

		ResultadoListagemClienteDTO resultadoListagemClienteDto = servicoCliente
				.obterClientesPorPaginaEComQuantidadeTotalPorPagina(paginaAtual, quantidadeTotalPorPagina);

		model.addAttribute("resultadoListagemClienteDto", resultadoListagemClienteDto);
		model.addAttribute("pageNumbers", resultadoListagemClienteDto.getQuantidadeTotalPaginas());
		model.addAttribute("urlClientesApi", ContratoRest.CLIENTES_API);

		return "index.html";
	}

}
