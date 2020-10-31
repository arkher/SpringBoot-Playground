package br.com.ithappens.mapper;

import br.com.ithappens.model.Estoque;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EstoqueMapper {

  List<Estoque> findAll();

  List<Estoque> findBy(@Param("idFilial") Long idFilial, @Param("idProduto") Long idProduto);

  Estoque findOne(@Param("idFilial") Long idFilial, @Param("idProduto") Long idProduto);

  void insert(@Param("estoque") Estoque estoque);

  void update(@Param("estoque") Estoque estoque);

}