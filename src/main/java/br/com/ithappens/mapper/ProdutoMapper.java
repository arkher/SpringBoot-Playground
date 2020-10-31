package br.com.ithappens.mapper;

import br.com.ithappens.model.Produto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProdutoMapper {

  List<Produto> findAll();

  Produto findOne(@Param("id") Long id);

  List<Produto> findByDescricao(@Param("descricao") String descricao);

  void insert(@Param("produto") Produto produto);

  void update(@Param("produto") Produto produto);

  void delete(@Param("id") Long id);
}