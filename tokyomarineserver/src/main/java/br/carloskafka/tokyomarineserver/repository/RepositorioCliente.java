package br.carloskafka.tokyomarineserver.repository;

import java.util.List;

import br.carloskafka.tokyomarineserver.domain.Cliente;

public interface RepositorioCliente {
	public Cliente cadastrarCliente(Cliente clienteASerSalvo);
	public List<Cliente> obterClientesPorPaginaEComQuantidadeTotalPorPagina(int numeroPagina, int quantidadeTotalPorPagina);
	public int obterQuantidadeTotalClientes();
	public int obterQuantidadeTotalPaginasDeClientes(int quantidadeTotalPorPagina);
}
