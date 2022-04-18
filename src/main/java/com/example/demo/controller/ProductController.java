package com.example.demo.controller;

import com.example.demo.model.ProductModel;
import com.example.demo.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("ativos")
    public ResponseEntity<List<ProductModel>> listarTodosAtivos() {
        List<ProductModel> repo = Collections.singletonList(repository.findByAtivo(true));
        return ResponseEntity.ok(repo);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ProductModel salvar(@RequestBody ProductModel product) {
        return repository.save(product);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> details(Authentication auth, @RequestParam("valor") String param) {
        ProductModel repo = repository.findByName(param);
        if (repo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não localizado!");
        }
        return ResponseEntity.ok().body(repo);
    }

}
