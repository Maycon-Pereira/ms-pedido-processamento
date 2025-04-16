package com.project.microsservices.pedido.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.microsservices.pedido.model.Pedido;
import com.project.microsservices.pedido.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private final RabbitTemplate rabbitTemplate;
	private final PedidoService pedidoService;
	
	public PedidoController(RabbitTemplate rabbitTemplate, PedidoService pedidoService) {
		this.rabbitTemplate = rabbitTemplate;
		this.pedidoService = pedidoService;
	}
	
	@Value("${broker.queue.processamento.name}")
	private String routinKey;
	
	@PostMapping
	public String criarPedido(@RequestBody Pedido pedido) {
		Pedido pedidoSalvo = pedidoService.salvar(pedido);
		rabbitTemplate.convertAndSend("", routinKey , pedidoSalvo);
		
		return "Pedido salvo e enviado para o processamento" + pedido.getDescricao();
	}
	
	@GetMapping
	public List<Pedido> listarPedido() {
		return pedidoService.listarPedidos();
	}
	
}
