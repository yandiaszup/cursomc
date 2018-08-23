package com.example.cursomc.repositories;

import com.example.cursomc.domain.ItemPedido;
import com.example.cursomc.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
