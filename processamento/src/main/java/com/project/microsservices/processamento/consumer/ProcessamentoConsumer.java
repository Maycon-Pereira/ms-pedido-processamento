package com.project.microsservices.processamento.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.project.microsservices.processamento.dto.PedidoDTO;

@Component
public class ProcessamentoConsumer {

	@RabbitListener(queues = "${broker.queue.processamento.name}")
	public void listnerProcessamentoQueue(PedidoDTO pedidoDto) {
		System.out.println(pedidoDto.getDescricao());
	}
}
