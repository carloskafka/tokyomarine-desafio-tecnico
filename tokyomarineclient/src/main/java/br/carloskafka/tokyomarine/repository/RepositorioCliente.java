package br.carloskafka.tokyomarine.repository;

import java.util.List;

import br.carloskafka.tokyomarine.domain.Cliente;

public interface RepositorioCliente {
	public Cliente cadastrarCliente(Cliente clienteASerSalvo);
	public List<Cliente> obterClientesPorPaginaEComQuantidadeTotalPorPagina(int numeroPagina, int quantidadeTotalPorPagina);
	public int obterQuantidadeTotalClientes();
	public int obterQuantidadeTotalPaginasDeClientes(int quantidadeTotalPorPagina);
}
