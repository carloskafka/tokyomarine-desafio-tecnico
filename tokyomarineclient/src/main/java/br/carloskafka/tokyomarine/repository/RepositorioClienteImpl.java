package br.carloskafka.tokyomarine.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.carloskafka.tokyomarine.domain.Cliente;

@Repository
public class RepositorioClienteImpl implements RepositorioCliente {
	private final Logger logger = LoggerFactory.getLogger(RepositorioClienteImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Cliente cadastrarCliente(Cliente clienteASerSalvo) {
		String acao;
		try {
			if (clienteASerSalvo.getIdCliente() == null) {
				em.persist(clienteASerSalvo);
				acao = "Inclusão";
			} else {
				em.merge(clienteASerSalvo);
				clienteASerSalvo = em.find(Cliente.class, clienteASerSalvo.getIdCliente());
				acao = "Alteração";
			}
			logger.info(acao + " do " + clienteASerSalvo + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do " + clienteASerSalvo + ". \nDetalhes: " + e);
		}
		return clienteASerSalvo;
	}

	@Override
	public List<Cliente> obterClientesPorPaginaEComQuantidadeTotalPorPagina(int numeroPagina, int quantidadeTotalPorPagina) {
		List<Cliente> clientes = new ArrayList<>();

		try {
			TypedQuery<Cliente> typedQuery = em.createQuery("SELECT c FROM Cliente c JOIN FETCH c.apolicesCliente",
					Cliente.class);
			typedQuery.setFirstResult((numeroPagina - 1) * quantidadeTotalPorPagina);
			typedQuery.setMaxResults(quantidadeTotalPorPagina);
			clientes = typedQuery.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a relação de clientes. \nDetalhe:" + e);
		}
		return clientes;
	}

	@Override
	public int obterQuantidadeTotalClientes() {
		int quantidadeTotalClientes = 0;
		
		try {
			Query queryTotal = em.createQuery("Select count(c.idCliente) from Cliente c");
			quantidadeTotalClientes = (int) (long) queryTotal.getSingleResult();
		} catch (Exception e) {
			logger.error("Não foi possível obter a quantidade total de clientes. \nDetalhe:" + e);
		}

		return quantidadeTotalClientes;
	}

	@Override
	public int obterQuantidadeTotalPaginasDeClientes(int quantidadeTotalPorPagina) {
		return (int) ((obterQuantidadeTotalClientes() / quantidadeTotalPorPagina) + 1);
	}

}