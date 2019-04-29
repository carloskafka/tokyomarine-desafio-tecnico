package br.carloskafka.tokyomarine.service;

import br.carloskafka.tokyomarine.dtos.ClienteDTO;
import br.carloskafka.tokyomarine.dtos.ResultadoCadastroClienteDTO;
import br.carloskafka.tokyomarine.dtos.ResultadoListagemClienteDTO;

public interface ServicoCliente {
	public ResultadoListagemClienteDTO obterClientesPorPaginaEComQuantidadeTotalPorPagina(int paginaAtual, int quantidadeTotalPorPagina);
	public int obterQuantidadeTotalPaginasDeClientes(int quantidadeTotalPorPagina);
	public ResultadoCadastroClienteDTO cadastrarCliente(ClienteDTO clienteDto);
}
