package br.carloskafka.tokyomarineserver.fachadas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.carloskafka.tokyomarinecommons.dto.PlanetaDTO;
import br.carloskafka.tokyomarinecommons.dto.ResultadoConsultaPlanetaDTO;
import br.carloskafka.tokyomarinecommons.dto.ResultadoEdicaoPlanetaDTO;
import br.carloskafka.tokyomarineserver.dominio.Planeta;
import br.carloskafka.tokyomarineserver.fabricas.FabricaPlaneta;
import br.carloskafka.tokyomarineserver.repositorios.RepositorioPlaneta;
import br.carloskafka.tokyomarineserver.servicos.ServicoListagemPlanetaApiStarWars;

@Component
public class FachadaPlaneta {
	@Autowired
	private RepositorioPlaneta repositorioPlaneta;
	@Autowired
	private ServicoListagemPlanetaApiStarWars servicoListagemPlanetaApiStarWars;

	public ResultadoEdicaoPlanetaDTO adicionarPlaneta(PlanetaDTO planetaDto) {
		ResultadoEdicaoPlanetaDTO resultadoEdicaoPlanetaDTO = new ResultadoEdicaoPlanetaDTO();

		Planeta planeta = FabricaPlaneta.converterParaDominio(planetaDto);

		planeta.validarObrigatoriedadeDeDados();

		if (planeta.isValidado()) {
			Planeta planetaApiObtido = servicoListagemPlanetaApiStarWars.buscarPlanetaPorNome(planeta.getNome());

			if (planetaApiObtido != null) {
				planeta.setQuantidadeDeAparicoesEmFilmes(planetaApiObtido.getQuantidadeDeAparicoesEmFilmes());
			}

			PlanetaDTO planetaDtoSalvo = FabricaPlaneta.converterParaDTO(repositorioPlaneta.adicionarPlaneta(planeta));
			resultadoEdicaoPlanetaDTO.efetuadoComSucesso(planetaDtoSalvo);
		} else {
			resultadoEdicaoPlanetaDTO.setErros(planeta.getErros());
		}

		return resultadoEdicaoPlanetaDTO;
	}

	public ResultadoConsultaPlanetaDTO listarPlanetas() {
		return new ResultadoConsultaPlanetaDTO(FabricaPlaneta.converterParaDTO(repositorioPlaneta.listarPlanetas()));
	}

	public ResultadoConsultaPlanetaDTO listarPlanetasApi() {
		return new ResultadoConsultaPlanetaDTO(FabricaPlaneta.converterParaDTO(servicoListagemPlanetaApiStarWars.listarPlanetas()));
	}

	public ResultadoConsultaPlanetaDTO buscarPorNome(String nome) {
		ResultadoConsultaPlanetaDTO resultadoConsultaPlanetaDto = new ResultadoConsultaPlanetaDTO();

		Planeta planetaEncontrado = repositorioPlaneta.buscarPorNome(nome);

		if (planetaEncontrado != null) {
			resultadoConsultaPlanetaDto.efetuadoComSucesso(FabricaPlaneta.converterParaDTO(planetaEncontrado));
		} else {
			resultadoConsultaPlanetaDto.adicionarErro("Nenhum planeta foi encontrado com esse nome.");
		}

		return resultadoConsultaPlanetaDto;
	}

	public ResultadoConsultaPlanetaDTO buscarPorId(Long id) {
		ResultadoConsultaPlanetaDTO resultadoConsultaPlanetaDto = new ResultadoConsultaPlanetaDTO();

		Planeta planetaEncontrado = repositorioPlaneta.buscarPorId(id);

		if (planetaEncontrado != null) {
			resultadoConsultaPlanetaDto.efetuadoComSucesso(FabricaPlaneta.converterParaDTO(planetaEncontrado));
		} else {
			resultadoConsultaPlanetaDto.adicionarErro("Nenhum planeta foi encontrado com esse id.");
		}

		return resultadoConsultaPlanetaDto;
	}

}
