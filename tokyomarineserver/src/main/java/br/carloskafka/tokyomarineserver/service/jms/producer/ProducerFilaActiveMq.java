package br.carloskafka.tokyomarineserver.service.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.carloskafka.tokyomarinecommons.dtos.ClienteDTO;

@Component
public class ProducerFilaActiveMq {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${gkz.activemq.queue}")
	private String nomeFila;
	
	public void send(ClienteDTO clienteDto){
		jmsTemplate.convertAndSend(nomeFila, clienteDto);
	}
}