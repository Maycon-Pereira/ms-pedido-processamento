package com.project.microsservices.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.microsservices.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
