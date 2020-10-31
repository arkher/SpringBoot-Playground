package br.com.ithappens.mapper;

import br.com.ithappens.model.Filial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FilialMapper {

  List<Filial> findAll();

  Filial findOne(@Param("id") Long id);

  List<Filial> findByDescricao(@Param("descricao") String descricao);

  void insert(@Param("filial") Filial filial);

  void update(@Param("filial") Filial filial);

  void delete(@Param("id") Long id);

}