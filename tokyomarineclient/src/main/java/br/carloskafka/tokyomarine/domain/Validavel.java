package br.carloskafka.tokyomarine.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

public abstract class Validavel {
	@Transient
	private boolean validado;
	@Transient
	private List<String> erros;

	public Validavel() {
		erros = new ArrayList<>();
		validado = true;
	}

	public boolean isValidado() {
		return validado;
	}

	public void setValidado(boolean validado) {
		this.validado = validado;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		for (String erro : erros) {
			adicionarErro(erro);
		}
	}
	
	public void adicionarErro(String erro) {
		getErros().add(erro);
		validado = false;
	}
	
	public String obterDescricaoErros() {
		String descricaoErros = "";
		
		for (String erro : erros) {
			descricaoErros += erro + "\n";
		}
		
		return descricaoErros;
	}

	@Override
	public String toString() {
		return "Validavel [validado=" + validado + ", erros=" + erros + "]";
	}

}
