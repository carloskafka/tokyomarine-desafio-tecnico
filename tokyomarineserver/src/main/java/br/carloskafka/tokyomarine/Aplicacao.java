package br.carloskafka.tokyomarine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication
public class Aplicacao {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(Aplicacao.class, args);
	}

}