package br.carloskafka.tokyomarine.service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.carloskafka.tokyomarine.domain.Cliente;
import br.carloskafka.tokyomarine.dtos.ClienteDTO;
import br.carloskafka.tokyomarine.service.ServicoCliente;
import br.carloskafka.tokyomarine.service.jms.producer.ProducerFilaActiveMq;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ControladorCliente {

	@Autowired
	private ProducerFilaActiveMq producerFilaActiveMq;

	@Autowired
	private ServicoCliente servicoCliente;

	@ApiOperation(value = "Adicionar um cliente", response = Cliente.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Adicionou um novo cliente com sucesso.") })
	@PostMapping(value = ContratoRest.CLIENTE_API)
	public ClienteDTO postCustomer(@RequestBody ClienteDTO clienteDto) {
		producerFilaActiveMq.send(clienteDto);
		return clienteDto;
	}

	@ApiOperation(value = "Obter todos os clientes", response = Cliente.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Relação de clientes obtida com sucesso.") })
	@GetMapping(value = ContratoRest.CLIENTES_API)
	public List<ClienteDTO> getAll() {
		return servicoCliente.getAll();
	}

}
