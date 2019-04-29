package br.carloskafka.tokyomarine.dtos;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {
	private Long idCliente;
	private String nomeCliente;
	private String cpfCliente;
	private List<ApoliceDTO> apolicesClienteDto;

	public ClienteDTO() {
		apolicesClienteDto = new ArrayList<>();
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

	public List<ApoliceDTO> getApolicesClienteDto() {
		return apolicesClienteDto;
	}

	public void setApolicesClienteDto(List<ApoliceDTO> apolicesClienteDto) {
		this.apolicesClienteDto = apolicesClienteDto;
	}

	@Override
	public String toString() {
		return "ClienteDTO [idCliente=" + idCliente + ", nomeCliente=" + nomeCliente + ", cpfCliente=" + cpfCliente
				+ ", apolicesClienteDto=" + apolicesClienteDto + "]";
	}

}
