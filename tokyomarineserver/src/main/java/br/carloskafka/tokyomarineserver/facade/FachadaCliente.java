package br.carloskafka.tokyomarineserver.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.carloskafka.tokyomarinecommons.dtos.ClienteDTO;
import br.carloskafka.tokyomarinecommons.dtos.ResultadoCadastroClienteDTO;
import br.carloskafka.tokyomarinecommons.dtos.ResultadoListagemClienteDTO;
import br.carloskafka.tokyomarineserver.domain.Cliente;
import br.carloskafka.tokyomarineserver.factory.FabricaCliente;
import br.carloskafka.tokyomarineserver.repository.RepositorioCliente;
import br.carloskafka.tokyomarineserver.service.ServicoCliente;

@Component
public class FachadaCliente implements ServicoCliente {

	@Autowired
	private RepositorioCliente repositorioCliente;

	@Override
	public ResultadoCadastroClienteDTO cadastrarCliente(ClienteDTO clienteDto) {
		ResultadoCadastroClienteDTO resultadoCadastroClienteDTO = new ResultadoCadastroClienteDTO();

		Cliente clienteASerSalvo = FabricaCliente.converterParaDominio(clienteDto);

		clienteASerSalvo.validarObrigatoriedadeDeDados();

		if (clienteASerSalvo.isValidado()) {
			Cliente clienteSalvo = repositorioCliente.cadastrarCliente(clienteASerSalvo);

			if (clienteSalvo != null) {
				resultadoCadastroClienteDTO.setClienteDto(FabricaCliente.converterParaDTO(clienteSalvo));
			}
		} else {
			resultadoCadastroClienteDTO.setErros(clienteASerSalvo.getErros());
		}

		return resultadoCadastroClienteDTO;
	}
	
	public int obterQuantidadeTotalPaginasDeClientes(int quantidadeClientesPorPagina) {
		return repositorioCliente.obterQuantidadeTotalPaginasDeClientes(quantidadeClientesPorPagina);
	}

	@Override
	public ResultadoListagemClienteDTO obterClientesPorPaginaEComQuantidadeTotalPorPagina(int numeroPagina, int quantidadeClientesPorPagina) {
		ResultadoListagemClienteDTO resultadoListagemClienteDto = new ResultadoListagemClienteDTO();

		resultadoListagemClienteDto.setClientesDto(FabricaCliente.converterParaDTO(repositorioCliente.obterClientesPorPaginaEComQuantidadeTotalPorPagina(numeroPagina, quantidadeClientesPorPagina)));

		return resultadoListagemClienteDto;
	}
}
