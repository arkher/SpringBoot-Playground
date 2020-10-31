package br.com.ithappens.service;

import br.com.ithappens.mapper.*;
import br.com.ithappens.model.*;
import br.com.ithappens.model.enums.TipoPedido;
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
            if(estoque!=null){
                if(itemPedido.getQuantidade()<estoque.getQuantidade()){
                    estoque.setQuantidade(estoque.getQuantidade() - itemPedido.getQuantidade());
                    estoqueMapper.update(estoque);
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean insert(Pedido pedido){
        try{
            Filial filial = filialMapper.findOne(pedido.getFilial().getId());
            if(filial!=null) {
                pedidoMapper.insert(pedido);
                List<ItemPedido> itensPedido = pedido.getItensPedido();
                itemPedidoMapper.insertItensPedido(pedido.getId(), itensPedido);

                if(pedido.getTipoPedido()==TipoPedido.ENTRADA) {
                    return insertPedidoEntrada(itensPedido, pedido, filial);
                }else{
                    return insertPedidoSaida(itensPedido, pedido);
                }
            }
        }catch(Exception e){ // depois mudar para exceção personalizada
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void delete(Long id) {
        pedidoMapper.delete(id);
    }

}
