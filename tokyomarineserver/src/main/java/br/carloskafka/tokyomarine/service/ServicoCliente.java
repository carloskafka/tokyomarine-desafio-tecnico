package br.carloskafka.tokyomarine.service;

import java.util.List;

import br.carloskafka.tokyomarine.dtos.ClienteDTO;
import br.carloskafka.tokyomarine.dtos.ResultadoCadastroClienteDTO;

public interface ServicoCliente {
	public List<ClienteDTO> getAll();
	public ResultadoCadastroClienteDTO add(ClienteDTO clienteDto);
}
