package br.com.ithappens.service;

import br.com.ithappens.mapper.*;
import br.com.ithappens.model.*;
import br.com.ithappens.model.enums.TipoPedido;
import br.com.ithappens.model.exceptions.FilialNaoEncontradaException;
import br.com.ithappens.model.exceptions.ProdutoInexistenteException;
import br.com.ithappens.model.exceptions.QuantidadeInvalidaException;
import br.com.ithappens.model.exceptions.SemItensPedidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    PedidoMapper pedidoMapper;
    FilialMapper filialMapper;
    ItemPedidoMapper itemPedidoMapper;
    EstoqueMapper estoqueMapper;
    ProdutoMapper produtoMapper;

    @Autowired
    PedidoService(PedidoMapper pedidoMapper, FilialMapper filialMapper,
                  ItemPedidoMapper itemPedidoMapper, EstoqueMapper estoqueMapper,
                  ProdutoMapper produtoMapper){
        this.pedidoMapper = pedidoMapper;
        this.filialMapper = filialMapper;
        this.itemPedidoMapper = itemPedidoMapper;
        this.estoqueMapper = estoqueMapper;
        this.produtoMapper = produtoMapper;
    }

    public List<Pedido> findAll(){ return pedidoMapper.findAll(); }

    public Pedido findOne(Long id){
        return pedidoMapper.findOne(id);
    }

    public List<Pedido> findBy(TipoPedido tipoPedido, Long idFilial){
        return pedidoMapper.findBy(tipoPedido, idFilial);
    }

    public Boolean insertPedidoEntrada(List<ItemPedido> itensPedido, Pedido pedido, Filial filial){
        for (ItemPedido itemPedido : itensPedido) {
            Estoque estoque = estoqueMapper.findOne(pedido.getFilial().getId(), itemPedido.getIdProduto());
            if (estoque != null) {
                estoque.setQuantidade(estoque.getQuantidade() + itemPedido.getQuantidade());
                estoqueMapper.update(estoque);
            } else {
                Produto produtoEstoque = produtoMapper.findOne(itemPedido.getIdProduto());
                Estoque novoEstoque = Estoque.builder().filial(filial)
                        .produto(produtoEstoque)
                        .quantidade(itemPedido.getQuantidade())
                        .build();
                estoqueMapper.insert(novoEstoque);
                return false;
            }
        }
        return true;
    }

    public Boolean insertPedidoSaida(List<ItemPedido> itensPedido, Pedido pedido){
        for (ItemPedido itemPedido: itensPedido){
            Estoque estoque = estoqueMapper.findOne(pedido.getFilial().getId(), itemPedido.getIdProduto());
            if(estoque!=null && itemPedido.getQuantidade()<=estoque.getQuantidade()){
                    estoque.setQuantidade(estoque.getQuantidade() - itemPedido.getQuantidade());
                    estoqueMapper.update(estoque);
            }else{
                return false;
            }
        }
        return true;
    }

    public Boolean insert(Pedido pedido){
        verificarPedido(pedido);
        try{
            Filial filial = filialMapper.findOne(pedido.getFilial().getId());
            pedidoMapper.insert(pedido);

            List<ItemPedido> itensPedido = pedido.getItensPedido();

            itemPedidoMapper.insertItensPedido(pedido.getId(), itensPedido);
            if(pedido.getTipoPedido()==TipoPedido.ENTRADA) {
                return insertPedidoEntrada(itensPedido, pedido, filial);
            }else{
                return insertPedidoSaida(itensPedido, pedido);
            }

        }catch(Exception e){ // depois mudar para exceção personalizada
            e.printStackTrace();
            return false;
        }
    }

    public void delete(Long id) {
        pedidoMapper.delete(id);
    }

    public void verificarPedido(Pedido pedido){
        if(pedido.getFilial()==null) throw new FilialNaoEncontradaException("Filial precisa ser informada.");

        Filial filial = filialMapper.findOne(pedido.getFilial().getId());
        if(filial==null) throw new FilialNaoEncontradaException("Filial do pedido não encontrada.");
        if(pedido.getItensPedido().isEmpty() || pedido.getItensPedido()==null)
            throw new SemItensPedidoException("Lista de itens do pedido não pode ser vazia.");
        if(!(pedido.getTipoPedido().equals(TipoPedido.ENTRADA) || pedido.getTipoPedido().equals(TipoPedido.SAIDA)))
            throw new FilialNaoEncontradaException("Tipo de pedido inválido");

        List<ItemPedido> itensPedido = pedido.getItensPedido();
        verificarItensPedido(itensPedido);
    }

    public void verificarItensPedido(List<ItemPedido> itensPedido) {
        for (ItemPedido itemPedido : itensPedido) {
            if(itemPedido.getIdProduto() == null) throw new ProdutoInexistenteException("O id do produto precisa ser informado");
            Produto prod = produtoMapper.findOne(itemPedido.getIdProduto());
            if(prod == null) throw new ProdutoInexistenteException("Há produtos não cadastrados.");
            if(itemPedido.getQuantidade() == null || itemPedido.getQuantidade()<0) throw new QuantidadeInvalidaException("Há produtos com quantidades inválidas");
        }
    }
}
