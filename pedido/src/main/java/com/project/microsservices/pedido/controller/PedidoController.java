package com.project.microsservices.pedido.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.microsservices.pedido.model.domain.PedidoDTO;
import com.project.microsservices.pedido.model.domain.PedidoRequest;
import com.project.microsservices.pedido.model.domain.PedidoResponse;
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
	public ResponseEntity<PedidoResponse> criarPedido(@RequestBody PedidoRequest pedidoReq) {
		
		ModelMapper mapper = new ModelMapper();
		
		PedidoDTO pedidoDto = mapper.map(pedidoReq, PedidoDTO.class);
		
		pedidoDto = pedidoService.salvar(pedidoDto);
		rabbitTemplate.convertAndSend("", routinKey , pedidoDto);
		
		return new ResponseEntity<>(mapper.map(pedidoDto, PedidoResponse.class), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<PedidoResponse>> listarPedido() {
		List<PedidoDTO> pedido = pedidoService.listarPedidos();
		
		ModelMapper mapper = new ModelMapper();
		
		List<PedidoResponse> resposta = pedido.stream()
				.map(pedidoDto -> mapper.map(pedidoDto, PedidoResponse.class))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(resposta, HttpStatus.OK);
	}
	
}
