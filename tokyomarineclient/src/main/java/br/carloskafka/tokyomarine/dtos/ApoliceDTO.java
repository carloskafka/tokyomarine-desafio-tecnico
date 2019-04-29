package br.carloskafka.tokyomarine.dtos;

public class ApoliceDTO {
	private Long idApolice;
	private String codProduto;

	public Long getIdApolice() {
		return idApolice;
	}

	public void setIdApolice(Long idApolice) {
		this.idApolice = idApolice;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	@Override
	public String toString() {
		return "ApoliceDTO [idApolice=" + idApolice + ", codProduto=" + codProduto + "]";
	}

}
