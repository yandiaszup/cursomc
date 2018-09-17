package com.example.cursomc.resources;

import com.example.cursomc.DTO.CategoriaDTO;
import com.example.cursomc.DTO.ProdutoDTO;
import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Pedido;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.resources.utils.URL;
import com.example.cursomc.services.PedidoServices;
import com.example.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Produto obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findpage(@RequestParam(value="nome", defaultValue="0") String nome,
                                                     @RequestParam(value="categorias", defaultValue="0") String categorias,
                                                     @RequestParam(value="page", defaultValue="0") Integer page,
                                                     @RequestParam(value="linesperpage", defaultValue = "24") Integer linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        List<Integer> ids = URL.decodeIntList(categorias);
        String nomeDecoded = URL.decodeParam(nome);
        Page<Produto> lista = service.search(nomeDecoded,ids,page,linesPerPage,orderBy,direction);
        Page<ProdutoDTO> listDto = lista.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

}
