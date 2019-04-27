package br.carloskafka.tokyomarine.service.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.carloskafka.tokyomarine.dtos.ClienteDTO;

@Component
public class JmsProducer {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${gkz.activemq.queue}")
	private String queue;
	
	public void send(ClienteDTO clienteDto){
		jmsTemplate.convertAndSend(queue, clienteDto);
	}
}