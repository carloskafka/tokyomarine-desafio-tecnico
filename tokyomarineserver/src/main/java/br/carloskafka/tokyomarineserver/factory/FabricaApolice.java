package br.carloskafka.tokyomarineserver.factory;

import java.util.ArrayList;
import java.util.List;

import br.carloskafka.tokyomarinecommons.dtos.ApoliceDTO;
import br.carloskafka.tokyomarineserver.domain.Apolice;

public class FabricaApolice {

	public static List<Apolice> converterParaDominio(List<ApoliceDTO> apolicesClienteDto) {
		List<Apolice> apolices = new ArrayList<>();

		if (apolicesClienteDto != null) {
			for (ApoliceDTO apoliceDTO : apolicesClienteDto) {
				apolices.add(converterParaDominio(apoliceDTO));
			}
		}
		return apolices;
	}

	public static List<ApoliceDTO> converterParaDTO(List<Apolice> apolicesCliente) {
		List<ApoliceDTO> apolicesDto = new ArrayList<>();

		if (apolicesCliente != null) {
			for (Apolice apolice : apolicesCliente) {
				apolicesDto.add(converterParaDTO(apolice));
			}
		}
		return apolicesDto;
	}

	private static Apolice converterParaDominio(ApoliceDTO apoliceDTO) {
		Apolice apolice = null;

		if (apoliceDTO != null) {
			apolice = new Apolice();
			if (apoliceDTO.getIdApolice() != null) {
				apolice.setIdApolice(apoliceDTO.getIdApolice());
			}

			apolice.setCodProduto(apoliceDTO.getCodProduto());
		}

		return apolice;
	}

	private static ApoliceDTO converterParaDTO(Apolice apolice) {
		ApoliceDTO apoliceDto = null;

		if (apolice != null) {
			apoliceDto = new ApoliceDTO();

			if (apolice.getIdApolice() != null) {
				apoliceDto.setIdApolice(apolice.getIdApolice());
			}
			apoliceDto.setCodProduto(apolice.getCodProduto());
		}
		return apoliceDto;
	}

}
