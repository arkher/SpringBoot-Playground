package br.com.ithappens.controller;

import br.com.ithappens.model.Pedido;
import br.com.ithappens.model.enums.TipoPedido;
import br.com.ithappens.service.FilialService;
import br.com.ithappens.service.PedidoService;
import br.com.ithappens.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    PedidoService pedidoService;
    FilialService filialService;

    @Autowired
    PedidoController(PedidoService pedidoService, FilialService filialService){
        this.pedidoService = pedidoService;
        this.filialService = filialService;
    }

    @GetMapping()
    public ResponseEntity<List<Pedido>> getPedidos(){
        List<Pedido> listaPedidos = this.pedidoService.findAll();
        return ResponseEntity.ok(listaPedidos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable("id") Long id){
        Pedido pedido = this.pedidoService.findOne(id);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping(value = "/busca")
    public ResponseEntity<List<Pedido>> getPedidosBy(@RequestParam(value = "tipoPedido", required = false) TipoPedido tipoPedido,
                                                     @RequestParam(value = "idFilial", required = false) Long idFilial){
        List<Pedido> listaPedidos = this.pedidoService.findBy(tipoPedido, idFilial);
        return ResponseEntity.ok(listaPedidos);
    }

    @PostMapping()
    public ResponseEntity<Response> savePedido(@RequestBody Pedido pedido){
        boolean inserido = pedidoService.insert(pedido);
        if(inserido){
            return ResponseEntity.ok(new Response("Salvo com sucesso"));
        }
        return ResponseEntity.ok(new Response("Erro ao salvar os dados"));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deletePedido(@PathVariable("id") Long id){
        pedidoService.delete(id);
        return ResponseEntity.ok(new Response("Deletado com sucesso"));
    }

}
