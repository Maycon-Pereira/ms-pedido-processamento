package com.project.microsservices.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.microsservices.pedido.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
