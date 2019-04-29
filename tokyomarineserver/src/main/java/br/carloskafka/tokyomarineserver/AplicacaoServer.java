package br.carloskafka.tokyomarineserver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.carloskafka.tokyomarinecommons.dtos.ApoliceDTO;
import br.carloskafka.tokyomarinecommons.dtos.ClienteDTO;
import br.carloskafka.tokyomarinecommons.dtos.ResultadoCadastroClienteDTO;
import br.carloskafka.tokyomarineserver.service.utils.HttpUtils;

@SpringBootApplication
public class AplicacaoServer implements CommandLineRunner {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(AplicacaoServer.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ClienteDTO clienteDto = new ClienteDTO();

		clienteDto.setNomeCliente("Nome cliente");
		clienteDto.setCpfCliente("12345678911");

		ApoliceDTO apoliceClienteDto = new ApoliceDTO();

		apoliceClienteDto.setCodProduto("1234");

		List<ApoliceDTO> apolicesDto = new ArrayList<>();
		
		for (int i = 0; i < 2; i++) {
			apolicesDto.add(apoliceClienteDto);
		}
		
		clienteDto.setApolicesClienteDto(apolicesDto);

		for (int i = 0; i <= 10; i++) {
			new RestTemplate().postForEntity("http://localhost:8080/api/customer", HttpUtils.criar(clienteDto),
					ResultadoCadastroClienteDTO.class);
		}
		
//		List<ClienteDTO> clientesDto = new RestTemplate().exchange("http://localhost:8080/api/customers",
//				HttpMethod.GET, null, new ParameterizedTypeReference<List<ClienteDTO>>() {
//		}).getBody();
//		
//		for (ClienteDTO clienteDtoObtido : clientesDto) {
//			System.out.println(clienteDtoObtido); 
//		}

	}

}