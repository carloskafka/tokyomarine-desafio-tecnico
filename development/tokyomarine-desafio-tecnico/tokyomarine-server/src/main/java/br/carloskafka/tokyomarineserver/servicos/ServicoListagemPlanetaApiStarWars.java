package br.carloskafka.tokyomarineserver.servicos;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.carloskafka.tokyomarinecommons.contrato.ContratoRest;
import br.carloskafka.tokyomarinecommons.dto.PlanetaDTO;
import br.carloskafka.tokyomarinecommons.dto.ResultadoConsultaPlanetaDTO;
import br.carloskafka.tokyomarineserver.dominio.Planeta;
import br.carloskafka.tokyomarineserver.fabricas.FabricaPlaneta;

@Component
public class ServicoListagemPlanetaApiStarWars {
	private static final Logger logger = LoggerFactory.getLogger(ServicoListagemPlanetaApiStarWars.class);

	private static List<Planeta> planetas = new ArrayList<>();
	
	private WebClient webClient = WebClient.create();
	
	public List<Planeta> listarPlanetas() {
				webClient
				.get()
				.uri(ContratoRest.URL_PLANETA_API_REATIVA_LISTAGEM_PLANETAS)
				.accept(MediaType.TEXT_EVENT_STREAM)
				.exchange()
				.flatMapMany(resposta -> resposta.bodyToFlux(ResultadoConsultaPlanetaDTO.class))
				.subscribe((resultadoConsultaPlanetaDTO) -> {
					for (PlanetaDTO planetaDto : resultadoConsultaPlanetaDTO.getPlanetasDto()) {
						Planeta planeta = FabricaPlaneta.converterParaDominio(planetaDto);

						if (!planetas.contains(planeta)) {
							planetas.add(planeta);
						}
					}
				});
		
		return planetas;
	}

	public Planeta buscarPlanetaPorNome(String nome) {
		Planeta planetaObtido = planetas.stream().filter(planeta -> planeta.getNome().equals(nome)).findFirst()
				.orElse(null);

		if (planetaObtido != null) {
			planetaObtido.validarObrigatoriedadeDeDados();

			if (!planetaObtido.isValidado()) {
				logger.error("Nenhum planeta encontrado para o nome " + nome);
			}
		}

		return planetaObtido;
	}

}