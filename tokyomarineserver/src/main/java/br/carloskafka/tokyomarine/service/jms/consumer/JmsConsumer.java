package br.carloskafka.tokyomarine.service.jms.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.carloskafka.tokyomarine.dtos.ClienteDTO;
import br.carloskafka.tokyomarine.service.ServicoCliente;

@Component
public class JmsConsumer {
	@Autowired
	private ServicoCliente servicoCliente;
	
	@JmsListener(destination = "${gkz.activemq.queue}", containerFactory="jsaFactory")
	public void receive(ClienteDTO clienteDto){
		System.out.println("Recieved Message: " + clienteDto);
		servicoCliente.add(clienteDto);
	}
}