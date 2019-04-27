package br.carloskafka.tokyomarine.dtos;

import java.util.ArrayList;
import java.util.List;

public class ResultadoCadastroClienteDTO {
	private List<String> erros;
	private ClienteDTO clienteDto;
	private boolean sucesso;

	public ResultadoCadastroClienteDTO() {
		erros = new ArrayList<>();
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	public ClienteDTO getClienteDto() {
		return clienteDto;
	}

	public void setClienteDto(ClienteDTO clienteDto) {
		this.clienteDto = clienteDto;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	@Override
	public String toString() {
		return "ResultadoCadastroClienteDTO [erros=" + erros + ", clienteDto=" + clienteDto + ", sucesso=" + sucesso
				+ "]";
	}
	

}
