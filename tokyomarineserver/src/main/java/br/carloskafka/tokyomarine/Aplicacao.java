package br.carloskafka.tokyomarine;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.carloskafka.tokyomarine.dtos.ApoliceDTO;
import br.carloskafka.tokyomarine.dtos.ClienteDTO;
import br.carloskafka.tokyomarine.dtos.ResultadoCadastroClienteDTO;
import br.carloskafka.tokyomarine.service.utils.HttpUtils;

@SpringBootApplication
public class Aplicacao implements CommandLineRunner {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(Aplicacao.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ClienteDTO clienteDto = new ClienteDTO();

		clienteDto.setNomeCliente("Nome cliente");
		clienteDto.setCpfCliente("12345678911");

		ApoliceDTO apoliceClienteDto = new ApoliceDTO();

		apoliceClienteDto.setCodProduto("1234");

		clienteDto.setApolicesClienteDto(Arrays.asList(apoliceClienteDto, apoliceClienteDto));

		for (int i = 0; i <= 10; i++) {
			new RestTemplate().postForEntity("http://localhost:8080/api/customer", HttpUtils.criar(clienteDto),
					ResultadoCadastroClienteDTO.class);
		}
		
		List<ClienteDTO> clientesDto = new RestTemplate().exchange("http://localhost:8080/api/customers",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<ClienteDTO>>() {
		}).getBody();
		
		for (ClienteDTO clienteDtoObtido : clientesDto) {
			System.out.println(clienteDtoObtido); 
		}

	}

}