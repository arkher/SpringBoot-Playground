package br.com.ithappens.service;

import br.com.ithappens.mapper.ProdutoMapper;
import br.com.ithappens.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    ProdutoMapper produtoMapper;

    @Autowired
    ProdutoService(ProdutoMapper produtoMapper){
        this.produtoMapper = produtoMapper;
    }

    public List<Produto> findAll(){
        return produtoMapper.findAll();
    }

    public Produto findOne(Long id){
        return produtoMapper.findOne(id);
    }

    public List<Produto> findByDescricao(String descricao){
        return produtoMapper.findByDescricao(descricao);
    }

    public void insert(Produto produto){
        produtoMapper.insert(produto);
    }

    public void update(Produto produto) { produtoMapper.update(produto); }

    public void delete(Long id) { produtoMapper.delete(id); }
}
