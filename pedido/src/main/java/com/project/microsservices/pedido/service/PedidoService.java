package com.project.microsservices.pedido.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.microsservices.pedido.model.ItemPedido;
import com.project.microsservices.pedido.model.Pedido;
import com.project.microsservices.pedido.repository.PedidoRepository;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
	
	public Pedido salvar(Pedido pedido) {
		if(pedido.getItens() != null) {
			for(ItemPedido item : pedido.getItens()) {
				item.setPedido(pedido);
			}
		}
		
		return pedidoRepository.save(pedido);
		
	}
	
	public List<Pedido> listarPedidos() {
		return pedidoRepository.findAll();
	}
}
