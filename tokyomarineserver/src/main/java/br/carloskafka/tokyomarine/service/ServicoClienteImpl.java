package br.carloskafka.tokyomarine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.carloskafka.tokyomarine.domain.Cliente;
import br.carloskafka.tokyomarine.dtos.ClienteDTO;
import br.carloskafka.tokyomarine.dtos.ResultadoCadastroClienteDTO;
import br.carloskafka.tokyomarine.dtos.ResultadoListagemClienteDTO;
import br.carloskafka.tokyomarine.factory.FabricaCliente;
import br.carloskafka.tokyomarine.repository.RepositorioCliente;

@Component
public class ServicoClienteImpl implements ServicoCliente {

	@Autowired
	private RepositorioCliente repositorioCliente;

	@Override
	public ResultadoCadastroClienteDTO add(ClienteDTO clienteDto) {
		ResultadoCadastroClienteDTO resultadoCadastroClienteDTO  = new ResultadoCadastroClienteDTO();

		Cliente clienteASerSalvo = FabricaCliente.converterParaDominio(clienteDto);

		clienteASerSalvo.validarObrigatoriedadeDeDados();

		if (clienteASerSalvo.isValidado()) {
			Cliente clienteSalvo = repositorioCliente.add(clienteASerSalvo);

			if (clienteSalvo != null) {
				resultadoCadastroClienteDTO.setClienteDto(FabricaCliente.converterParaDTO(clienteSalvo));
			} 
		} else {
			resultadoCadastroClienteDTO.setErros(clienteASerSalvo.getErros());
		}

		return resultadoCadastroClienteDTO;
	}

	@Override
	public ResultadoListagemClienteDTO obterClientes() {
		ResultadoListagemClienteDTO resultadoListagemClienteDto = new ResultadoListagemClienteDTO();
		
		resultadoListagemClienteDto.setClientesDto(FabricaCliente.converterParaDTO(repositorioCliente.obterClientes()));
		
		return resultadoListagemClienteDto;
	}
}
