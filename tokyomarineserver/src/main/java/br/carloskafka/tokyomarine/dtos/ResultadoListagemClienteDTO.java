package br.carloskafka.tokyomarine.dtos;

import java.util.ArrayList;
import java.util.List;

public class ResultadoListagemClienteDTO {
	private List<String> erros;
	private List<ClienteDTO> clientesDto;
	private boolean sucesso;

	public ResultadoListagemClienteDTO() {
		erros = new ArrayList<>();
		clientesDto = new ArrayList<>();
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	public List<ClienteDTO> getClientesDto() {
		return clientesDto;
	}

	public void setClientesDto(List<ClienteDTO> clientesDto) {
		this.clientesDto = clientesDto;
		sucesso = true;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	@Override
	public String toString() {
		return "ResultadoListagemClienteDTO [erros=" + erros + ", clientesDto=" + clientesDto + ", sucesso=" + sucesso
				+ "]";
	}

}
