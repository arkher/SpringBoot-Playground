package br.com.ithappens.controller;

import br.com.ithappens.model.Filial;
import br.com.ithappens.service.FilialService;
import br.com.ithappens.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filiais")
public class FilialController {

    FilialService filialService;

    @Autowired
    FilialController(FilialService filialService){
        this.filialService = filialService;
    }

    @GetMapping()
    public ResponseEntity<List<Filial>> getFiliais(){
        List<Filial> listaFiliais = this.filialService.findAll();
        return ResponseEntity.ok(listaFiliais);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Filial> getFilial(@PathVariable("id") Long id){
        Filial filial = this.filialService.findOne(id);
        return ResponseEntity.ok(filial);
    }

    @GetMapping(value = "/busca")
    public ResponseEntity<List<Filial>> getFilial(@RequestParam("descricao") String descricao){
        List<Filial> filiais = this.filialService.findByDescricao(descricao);
        return ResponseEntity.ok(filiais);
    }

    @PostMapping()
    public ResponseEntity<Response> saveFilial(@RequestBody Filial filial){
        filialService.insert(filial);
        return ResponseEntity.ok(new Response("Salvo com sucesso"));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateFilial(@PathVariable("id") Long id, @RequestBody Filial filial){
        filial.setId(id);
        filialService.update(filial);
        return ResponseEntity.ok(new Response("Atualizado com sucesso"));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteFilial(@PathVariable("id") Long id){
        filialService.delete(id);
        return ResponseEntity.ok(new Response("Deletado com sucesso"));
    }

}
