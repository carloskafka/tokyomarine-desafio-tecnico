package br.carloskafka.tokyomarineserver.controladores;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;

import br.carloskafka.tokyomarinecommons.contrato.ContratoRest;
import br.carloskafka.tokyomarinecommons.dto.PlanetaDTO;
import br.carloskafka.tokyomarinecommons.dto.ResultadoConsultaPlanetaDTO;
import br.carloskafka.tokyomarinecommons.dto.ResultadoEdicaoPlanetaDTO;
import br.carloskafka.tokyomarineserver.TestesIntegracaoAbstrato;

@SpringBootTest
public class TestesControladorPlaneta extends TestesIntegracaoAbstrato{
	private TestRestTemplate restTemplate;
	
	public TestesControladorPlaneta() {
		super("DadosTestesControladorPlaneta.xml");
		restTemplate = new TestRestTemplate();
	}
	
	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_todos_os_planetas_entao_retorna_a_listagem_de_planetas() {
		int quantidadeDePlanetasEsperado = 3;

		ResultadoConsultaPlanetaDTO resultadoConsultaPlaneta = restTemplate
				.getForEntity(ContratoRest.URL_COMPLETA_PLANETAS, ResultadoConsultaPlanetaDTO.class).getBody();

		Assert.assertEquals(resultadoConsultaPlaneta.getPlanetasDto().size(), quantidadeDePlanetasEsperado);
	}
	
	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_todos_os_planetas_por_nome_entao_retorna_a_listagem_de_planetas() {
		String nome = "Alderaan";
		int quantidadeDePlanetasEsperado = 1;

		ResultadoConsultaPlanetaDTO resultadoConsultaPlaneta = restTemplate
				.getForEntity(ContratoRest.URL_COMPLETA_PLANETAS_POR_NOME.replace(ContratoRest.URL_PARAMETRO_NOME, nome), ResultadoConsultaPlanetaDTO.class).getBody();

		Assert.assertEquals(resultadoConsultaPlaneta.getPlanetasDto().size(), quantidadeDePlanetasEsperado);
	}
	
	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_todos_os_planetas_por_nome_invalido_entao_retorna_a_listagem_vazia_de_planetas() {
		String erro = "Nenhum planeta foi encontrado com esse nome.";
		String nome = "INVALIDO";
		int quantidadeDePlanetasEsperado = 0;

		ResultadoConsultaPlanetaDTO resultadoConsultaPlaneta = restTemplate
				.getForEntity(ContratoRest.URL_COMPLETA_PLANETAS_POR_NOME.replace(ContratoRest.URL_PARAMETRO_NOME, nome), ResultadoConsultaPlanetaDTO.class).getBody();

		Assert.assertEquals(resultadoConsultaPlaneta.getPlanetasDto().size(), quantidadeDePlanetasEsperado);
		Assert.assertTrue(resultadoConsultaPlaneta.getErros().contains(erro));
	}
	
	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_todos_os_planetas_por_id_entao_retorna_a_listagem_de_planetas() {
		String id = "1000";
		int quantidadeDePlanetasEsperado = 1;

		ResultadoConsultaPlanetaDTO resultadoConsultaPlaneta = restTemplate
				.getForEntity(ContratoRest.URL_COMPLETA_PLANETAS_POR_ID.replace(ContratoRest.URL_PARAMETRO_ID, id), ResultadoConsultaPlanetaDTO.class).getBody();

		Assert.assertEquals(resultadoConsultaPlaneta.getPlanetasDto().size(), quantidadeDePlanetasEsperado);
	}
	
	@Test
	public void dado_um_cliente_quando_o_cliente_consultar_todos_os_planetas_por_id_inexistente_entao_retorna_a_listagem_vazia_de_planetas() {
		String erro = "Nenhum planeta foi encontrado com esse id.";
		String id = "9999999";
		int quantidadeDePlanetasEsperado = 0;

		ResultadoConsultaPlanetaDTO resultadoConsultaPlaneta = restTemplate
				.getForEntity(ContratoRest.URL_COMPLETA_PLANETAS_POR_ID.replace(ContratoRest.URL_PARAMETRO_ID, id), ResultadoConsultaPlanetaDTO.class).getBody();

		Assert.assertEquals(resultadoConsultaPlaneta.getPlanetasDto().size(), quantidadeDePlanetasEsperado);
		Assert.assertTrue(resultadoConsultaPlaneta.getErros().contains(erro));
	}

	@Test
	public void dado_um_cliente_quando_o_cliente_adicionar_um_novo_planeta_entao_retorna_o_novo_planeta() {
		String nome = "Yavin IV";
		String clima = "temperate, tropical";
		String terreno = "jungle, rainforests";

		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome(nome);
		novoPlanetaDto.setClima(clima);
		novoPlanetaDto.setTerreno(terreno);
		
		ResultadoEdicaoPlanetaDTO resultadoEdicaoPlanetaDTO = restTemplate
				.postForEntity(ContratoRest.URL_COMPLETA_PLANETAS, new HttpEntity<PlanetaDTO>(novoPlanetaDto), ResultadoEdicaoPlanetaDTO.class).getBody();

		Assert.assertTrue(resultadoEdicaoPlanetaDTO.isSucesso());
		Assert.assertFalse(resultadoEdicaoPlanetaDTO.getPlanetaDto().getId().toString().isEmpty());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getPlanetaDto().getNome(), novoPlanetaDto.getNome());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getPlanetaDto().getClima(), novoPlanetaDto.getClima());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getPlanetaDto().getTerreno(), novoPlanetaDto.getTerreno());		
	}

	@Test
	public void dado_um_cliente_quando_o_cliente_adicionar_um_planeta_invalido_entao_retorna_erros() {
		PlanetaDTO novoPlanetaDto = new PlanetaDTO();

		novoPlanetaDto.setNome("");
		novoPlanetaDto.setClima("");
		novoPlanetaDto.setTerreno("");
		
		ResultadoEdicaoPlanetaDTO resultadoEdicaoPlanetaDTO = restTemplate
				.postForEntity(ContratoRest.URL_COMPLETA_PLANETAS, new HttpEntity<PlanetaDTO>(novoPlanetaDto), ResultadoEdicaoPlanetaDTO.class).getBody();

		Assert.assertFalse(resultadoEdicaoPlanetaDTO.isSucesso());
		Assert.assertEquals(resultadoEdicaoPlanetaDTO.getErros().size(), 3);
	}

}
