package br.carloskafka.tokyomarineserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "apolice")
public class Apolice extends Validavel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_apolice")
	private Long idApolice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@Column(name = "cod_produto")
	private String codProduto;

	public Long getIdApolice() {
		return idApolice;
	}

	public void setIdApolice(Long idApolice) {
		this.idApolice = idApolice;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}
	
	public void validarObrigatoriedadeDeDados() {
		if (cliente == null) {
			adicionarErro("Informe um cliente válido.");
		} else {
			cliente.validarObrigatoriedadeDeDados();
			if (!cliente.isValidado()) {
				setErros(cliente.getErros());
			}
		}
		if (codProduto == null || codProduto.isEmpty()) {
			adicionarErro("Informe um código do produto válido.");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idApolice == null) ? 0 : idApolice.hashCode());
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
		Apolice other = (Apolice) obj;
		if (idApolice == null) {
			if (other.idApolice != null)
				return false;
		} else if (!idApolice.equals(other.idApolice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Apolice [idApolice=" + idApolice + ", codProduto=" + codProduto + "]";
	}

}
