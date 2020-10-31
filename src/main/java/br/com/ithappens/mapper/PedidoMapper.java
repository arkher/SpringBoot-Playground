package br.com.ithappens.mapper;

import br.com.ithappens.model.Pedido;
import br.com.ithappens.model.enums.TipoPedido;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PedidoMapper {

  List<Pedido> findAll();

  Pedido findOne(@Param("id") Long id);

  List<Pedido> findBy(@Param("tipoPedido") TipoPedido tipoPedido, @Param("idFilial") Long idFilial);

  void insert(@Param("pedido") Pedido pedido);

  void delete(@Param("id") Long id);

}