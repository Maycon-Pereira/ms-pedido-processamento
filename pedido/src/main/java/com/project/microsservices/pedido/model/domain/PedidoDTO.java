package com.project.microsservices.pedido.model.domain;

import java.util.List;

import com.project.microsservices.pedido.model.ItemPedido;

public class PedidoDTO {

	private Long id;
	private String descricao;
	
	private List<ItemPedido> itens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
