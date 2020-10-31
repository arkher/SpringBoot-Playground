package br.com.ithappens.mapper;

import br.com.ithappens.model.ItemPedido;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemPedidoMapper {

  List<ItemPedido> findItensPedido(@Param("idPedido") Long idPedido);

  void insertItensPedido(@Param("idPedido") Long idPedido, @Param("itensPedido") List<ItemPedido> itensPedido);

}