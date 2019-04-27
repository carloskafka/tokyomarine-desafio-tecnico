package br.carloskafka.tokyomarineserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.carloskafka.tokyomarineserver.dominio.Planeta;

@Repository
public class RepositorioPlanetaImpl implements RepositorioPlaneta {
	private final Logger logger = LoggerFactory.getLogger(RepositorioPlanetaImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Planeta adicionarPlaneta(Planeta planetaASalvar) {
		String acao;
		try {
			if (planetaASalvar.getId() == null) {
				em.persist(planetaASalvar);
				acao = "Inclus�o";
			} else {
				em.merge(planetaASalvar);
				planetaASalvar = em.find(Planeta.class, planetaASalvar.getId());
				acao = "Alteraç�o";
			}
			logger.info(acao + " do " + planetaASalvar + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do " + planetaASalvar + ". \nDetalhes: " + e);
		}
		return planetaASalvar;
	}

	public List<Planeta> listarPlanetas() {
		List<Planeta> planetas = new ArrayList<Planeta>();

		try {
			TypedQuery<Planeta> query = em.createQuery("SELECT p FROM Planeta p", Planeta.class);
			planetas = query.getResultList();
		} catch (Exception e) {
			logger.error("N�o foi poss�vel obter a quantidade de planetas. \nDetalhe:" + e);
		}
		return planetas;
	}

	public Planeta buscarPorNome(String nome) {
		Planeta planeta = null;

		try {
			TypedQuery<Planeta> query = em.createQuery("SELECT p FROM Planeta p WHERE p.nome = :nome", Planeta.class);
			query.setParameter("nome", nome);
			planeta = query.getSingleResult();
		} catch (Exception e) {
			logger.error("N�o foi poss�vel obter o planeta pelo nome " + nome + ". \nDetalhe:" + e);
		}
		return planeta;
	}

	public Planeta buscarPorId(Long id) {
		Planeta planeta = null;

		try {
			TypedQuery<Planeta> query = em.createQuery("SELECT p FROM Planeta p WHERE p.id = :id", Planeta.class);
			query.setParameter("id", id);
			planeta = query.getSingleResult();
		} catch (Exception e) {
			logger.error("N�o foi poss�vel obter o planeta pelo id " + id + ". \nDetalhe:" + e);
		}
		return planeta;
	}

}
