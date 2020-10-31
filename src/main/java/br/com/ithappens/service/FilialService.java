package br.com.ithappens.service;

import br.com.ithappens.mapper.FilialMapper;
import br.com.ithappens.model.Filial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilialService {

    FilialMapper filialMapper;

    @Autowired
    FilialService(FilialMapper filialMapper){
        this.filialMapper = filialMapper;
    }

    public List<Filial> findAll(){
        return filialMapper.findAll();
    }

    public Filial findOne(Long id){
        return filialMapper.findOne(id);
    }

    public List<Filial> findByDescricao(String descricao){
        return filialMapper.findByDescricao(descricao);
    }

    public void insert(Filial filial){
        filialMapper.insert(filial);
    }

    public void update(Filial filial){
        filialMapper.update(filial);
    }

    public void delete(Long id){
        filialMapper.delete(id);
    }

}
