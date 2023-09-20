package br.edu.ifrn.vendasestoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifrn.vendasestoque.domain.categoria.Categoria;
import br.edu.ifrn.vendasestoque.repository.CategoriaRepository;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @PostMapping
    public ResponseEntity cadastrarCategoria(@RequestBody Categoria categoria, 
                                            UriComponentsBuilder uriBuilder){
        Categoria categoriaLocal = repository.save(categoria);
        var uri = uriBuilder.path("/categorias/{id}").
                  buildAndExpand(categoriaLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var categoria = repository.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar(){
        List<Categoria> categorias = repository.findAll();
        return ResponseEntity.ok(categorias);
    }
    
}
