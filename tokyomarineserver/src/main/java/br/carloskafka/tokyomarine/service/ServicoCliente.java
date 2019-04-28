package br.carloskafka.tokyomarine.service;

import br.carloskafka.tokyomarine.dtos.ClienteDTO;
import br.carloskafka.tokyomarine.dtos.ResultadoCadastroClienteDTO;
import br.carloskafka.tokyomarine.dtos.ResultadoListagemClienteDTO;

public interface ServicoCliente {
	public ResultadoListagemClienteDTO obterClientes();
	public ResultadoCadastroClienteDTO add(ClienteDTO clienteDto);
}
