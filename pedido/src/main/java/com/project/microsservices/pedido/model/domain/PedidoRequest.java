package com.project.microsservices.pedido.model.domain;

import java.util.List;

import com.project.microsservices.pedido.model.ItemPedido;

public class PedidoRequest {

	private String descricao;
	private List<ItemPedido> itens;
	
	public PedidoRequest() {
		
	}
	
	public PedidoRequest(String descricao, List<ItemPedido> itens) {
		super();
		this.descricao = descricao;
		this.itens = itens;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	
	
}
