package br.carloskafka.tokyomarineclient.facade;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.carloskafka.tokyomarineclient.service.ServicoCliente;
import br.carloskafka.tokyomarineclient.service.utils.HttpUtils;
import br.carloskafka.tokyomarinecommons.dtos.ClienteDTO;
import br.carloskafka.tokyomarinecommons.dtos.ResultadoCadastroClienteDTO;
import br.carloskafka.tokyomarinecommons.dtos.ResultadoListagemClienteDTO;

@Component
public class FachadaCliente implements ServicoCliente {

	private Logger logger = LoggerFactory.getLogger(FachadaCliente.class);
	
	private static final String ERRO_SERVICO_INDISPONIVEL_TENTE_NOVAMENTE_MAIS_TARDE = "Serviço indisponível. Tente novamente mais tarde.";

	@Override
	public ResultadoCadastroClienteDTO cadastrarCliente(ClienteDTO clienteDto) {
		ResultadoCadastroClienteDTO resultadoCadastroClienteDto = new ResultadoCadastroClienteDTO();

		try {
			resultadoCadastroClienteDto = new RestTemplate().postForEntity("http://localhost:8080/api/customer",
					HttpUtils.criar(clienteDto), ResultadoCadastroClienteDTO.class).getBody();
		} catch (Exception e) {
			resultadoCadastroClienteDto.setErros(Arrays.asList(ERRO_SERVICO_INDISPONIVEL_TENTE_NOVAMENTE_MAIS_TARDE));
		}

		return resultadoCadastroClienteDto;
	}

	@Override
	public ResultadoListagemClienteDTO obterClientesPorPaginaEComQuantidadeTotalPorPagina(int numeroPagina,
			int quantidadeClientesPorPagina) {
		ResultadoListagemClienteDTO resultadoListagemClienteDto = new ResultadoListagemClienteDTO();

		try {
			resultadoListagemClienteDto = new RestTemplate().getForEntity(
					"http://localhost:8080/api/customers?size=" + quantidadeClientesPorPagina + "&page=" + numeroPagina,
					ResultadoListagemClienteDTO.class).getBody();
		} catch (Exception e) {
			resultadoListagemClienteDto.setErros(Arrays.asList(ERRO_SERVICO_INDISPONIVEL_TENTE_NOVAMENTE_MAIS_TARDE));
			logger.error(e.getMessage());
		}

		return resultadoListagemClienteDto;
	}
}
