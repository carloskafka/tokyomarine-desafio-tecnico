package br.carloskafka.tokyomarine.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.carloskafka.tokyomarine.domain.Cliente;

@Repository
public class RepositorioCliente {
	private final Logger logger = LoggerFactory.getLogger(RepositorioCliente.class);

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Cliente add(Cliente clienteASerSalvo) {
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

	public List<Cliente> getAll() {
		List<Cliente> customers = new ArrayList<Cliente>();

		try {
			TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
			customers = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a relação de clientes. \nDetalhe:" + e);
		}
		return customers;
	}
}