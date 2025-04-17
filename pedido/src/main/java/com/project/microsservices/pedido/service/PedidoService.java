package com.project.microsservices.pedido.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.microsservices.pedido.model.ItemPedido;
import com.project.microsservices.pedido.model.Pedido;
import com.project.microsservices.pedido.model.domain.PedidoDTO;
import com.project.microsservices.pedido.model.exception.BadRequestException;
import com.project.microsservices.pedido.repository.PedidoRepository;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
	public PedidoDTO salvar(PedidoDTO pedidoDto) {
		
		ModelMapper mapper = new ModelMapper();
		
		Pedido pedido = mapper.map(pedidoDto, Pedido.class);
		
		
		
		if(pedido.getDescricao() == null || pedido.getDescricao().trim().isEmpty()) {
			throw new BadRequestException("Descrição não pode ser vazia!");
		}
		
		if(pedido.getItens() != null) {
			for(ItemPedido item : pedido.getItens()) {
				if(item.getQuantidade() > 0 && item.getNome() != null && !item.getNome().trim().isEmpty()) {
				    item.setPedido(pedido);
				    pedido = pedidoRepository.save(pedido);
				} else {
				    throw new BadRequestException("Item do pedido inválido: nome ou quantidade incorretos.");					
				}
			}
		}
		pedidoDto.setId(pedido.getId());
		
		return pedidoDto;
		
	}
	
	public List<PedidoDTO> listarPedidos() {
		
		List<Pedido> pedidos = pedidoRepository.findAll();
		
		return pedidos.stream()
				.map(pedido -> new ModelMapper().map(pedido, PedidoDTO.class))
				.collect(Collectors.toList());
		
	}
}
