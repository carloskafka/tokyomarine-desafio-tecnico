package br.carloskafka.tokyomarineserver.factory;

import java.util.ArrayList;
import java.util.List;

import br.carloskafka.tokyomarinecommons.dtos.ClienteDTO;
import br.carloskafka.tokyomarineserver.domain.Cliente;

public class FabricaCliente {

	public static Cliente converterParaDominio(ClienteDTO clienteDto) {
		Cliente cliente = null;

		if (clienteDto != null) {
			cliente = new Cliente();

			if (clienteDto.getIdCliente() != null) {
				cliente.setIdCliente(clienteDto.getIdCliente());
			}

			cliente.setNomeCliente(clienteDto.getNomeCliente());
			cliente.setCpfCliente(clienteDto.getCpfCliente());
			cliente.setApolicesCliente(FabricaApolice.converterParaDominio(clienteDto.getApolicesClienteDto()));
		}

		return cliente;

	}

	public static ClienteDTO converterParaDTO(Cliente cliente) {
		ClienteDTO clienteDto = null;

		if (cliente != null) {
			clienteDto = new ClienteDTO();

			if (cliente.getIdCliente() != null) {
				clienteDto.setIdCliente(cliente.getIdCliente());
			}

			clienteDto.setNomeCliente(cliente.getNomeCliente());
			clienteDto.setCpfCliente(cliente.getCpfCliente());
			clienteDto.setApolicesClienteDto(FabricaApolice.converterParaDTO(cliente.getApolicesCliente()));
		}
		return clienteDto;
	}

	public static List<ClienteDTO> converterParaDTO(List<Cliente> clientes) {
		List<ClienteDTO> clientesDto = new ArrayList<>();

		if (clientes != null) {
			for (Cliente cliente : clientes) {
				clientesDto.add(converterParaDTO(cliente));
			}
		}

		return clientesDto;
	}
}
