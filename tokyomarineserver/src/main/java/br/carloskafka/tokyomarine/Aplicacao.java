package br.carloskafka.tokyomarine;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.carloskafka.tokyomarine.dtos.ApoliceDTO;
import br.carloskafka.tokyomarine.dtos.ClienteDTO;
import br.carloskafka.tokyomarine.dtos.ResultadoCadastroClienteDTO;
import br.carloskafka.tokyomarine.service.ServicoCliente;

@SpringBootApplication
public class Aplicacao implements CommandLineRunner{

	@Autowired
	ServicoCliente servicoCliente;
	
	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(Aplicacao.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ClienteDTO clienteDTO = new ClienteDTO();
		
		clienteDTO.setNomeCliente("Carlos");
		clienteDTO.setCpfCliente("123456789");
		
		ApoliceDTO apoliceDTO = new ApoliceDTO();
		
		apoliceDTO.setCodProduto("12");
		
		clienteDTO.setApolicesClienteDto(Arrays.asList(apoliceDTO));
		
		ResultadoCadastroClienteDTO resultadoCadastroClienteDTO = servicoCliente.add(clienteDTO);
		
		System.out.println(new ObjectMapper().writeValueAsString(resultadoCadastroClienteDTO.getClienteDto()));
	}
}
