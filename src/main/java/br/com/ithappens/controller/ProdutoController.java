package br.com.ithappens.controller;

import br.com.ithappens.model.Produto;
import br.com.ithappens.service.ProdutoService;
import br.com.ithappens.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    ProdutoService produtoService;

    @Autowired
    ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping()
    public ResponseEntity<List<Produto>> getProdutos(){
        List<Produto> listaProdutos = this.produtoService.findAll();
        return ResponseEntity.ok(listaProdutos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable("id") Long id){
        Produto produto = this.produtoService.findOne(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping(value = "/busca")
    public ResponseEntity<List<Produto>> getProdutosByDescricao(@RequestParam("descricao") String descricao){
        List<Produto> listaProdutos = this.produtoService.findByDescricao(descricao);
        return ResponseEntity.ok(listaProdutos);
    }

    @PostMapping()
    public ResponseEntity<Response> saveProduto(@RequestBody Produto produto){
        produtoService.insert(produto);
        return ResponseEntity.ok(new Response("Salvo com sucesso"));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateProduto(@PathVariable("id") Long id, @RequestBody Produto produto){
        produto.setId(id);
        produtoService.update(produto);
        return ResponseEntity.ok(new Response("Atualizado com sucesso"));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteProduto(@PathVariable("id") Long id){
        produtoService.delete(id);
        return ResponseEntity.ok(new Response("Deletado com sucesso"));
    }

}
