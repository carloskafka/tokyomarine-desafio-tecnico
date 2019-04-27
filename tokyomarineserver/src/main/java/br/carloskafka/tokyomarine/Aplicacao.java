package br.carloskafka.tokyomarine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.carloskafka.tokyomarine.service.ServicoCliente;

@SpringBootApplication
public class Aplicacao {

	@Autowired
	ServicoCliente servicoCliente;
	
	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(Aplicacao.class, args);
	}

}
