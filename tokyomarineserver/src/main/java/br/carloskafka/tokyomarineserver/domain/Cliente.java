package br.carloskafka.tokyomarineserver.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Validavel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long idCliente;

	@Column(name = "nome_cliente")
	private String nomeCliente;

	@Column(name = "cpf_cliente")
	private String cpfCliente;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Apolice> apolicesCliente;

	public Cliente() {
		apolicesCliente = new ArrayList<>();
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public List<Apolice> getApolicesCliente() {
		return apolicesCliente;
	}

	public void setApolicesCliente(List<Apolice> apolicesCliente) {
		for (Apolice apolice : apolicesCliente) {
			adicionarApolice(apolice);
		}
	}

	public void adicionarApolice(Apolice apolice) {
		apolice.setCliente(this);
		apolicesCliente.add(apolice);
	}

	public void removerApolice(Apolice apolice) {
		apolice.setCliente(null);
		apolicesCliente.remove(apolice);
	}

	public void validarObrigatoriedadeDeDados() {
		if (nomeCliente == null || nomeCliente.isEmpty()) {
			adicionarErro("Informe um nome do cliente válido.");
		}
		if (cpfCliente == null || cpfCliente.isEmpty()) {
			adicionarErro("Informe um CPF do cliente válido.");
		}	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nomeCliente=" + nomeCliente + ", cpfCliente=" + cpfCliente
				+ ", apolicesCliente=" + apolicesCliente + "]";
	}

}