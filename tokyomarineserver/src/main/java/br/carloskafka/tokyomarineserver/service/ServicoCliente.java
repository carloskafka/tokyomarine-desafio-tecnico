package br.carloskafka.tokyomarineserver.service;

import br.carloskafka.tokyomarinecommons.dtos.ClienteDTO;
import br.carloskafka.tokyomarinecommons.dtos.ResultadoCadastroClienteDTO;
import br.carloskafka.tokyomarinecommons.dtos.ResultadoListagemClienteDTO;

public interface ServicoCliente {
	public ResultadoListagemClienteDTO obterClientesPorPaginaEComQuantidadeTotalPorPagina(int paginaAtual, int quantidadeTotalPorPagina);
	public int obterQuantidadeTotalPaginasDeClientes(int quantidadeTotalPorPagina);
	public ResultadoCadastroClienteDTO cadastrarCliente(ClienteDTO clienteDto);
}
