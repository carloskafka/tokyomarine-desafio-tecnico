package br.carloskafka.tokyomarineserver.utilitario;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.carloskafka.tokyomarinecommons.dto.ResultadoConsultaPlanetaDTO;

public class JsonUtils {

	public static String criarJson(ResultadoConsultaPlanetaDTO objeto) {
		String json = "";

		try {
			json = new ObjectMapper().writeValueAsString(objeto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}
}
