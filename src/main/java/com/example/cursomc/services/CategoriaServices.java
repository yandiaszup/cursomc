package com.example.cursomc.services;

import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.servicesexceptions.DataIntegrityException;
import com.example.cursomc.servicesexceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.example.cursomc.domain.Categoria;
import java.util.*;

@Service
public class CategoriaServices {

    @Autowired
    private CategoriaRepository repo;


    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id) { // Apenas deleta Categorias que nao tem produtos
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Nao e possivel excluir categoria que possui produtos");
        }
    }

    public List<Categoria> findall() {
        return repo.findAll();
    }
}
