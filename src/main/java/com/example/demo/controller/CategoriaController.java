package com.example.demo.controller;

import com.example.demo.model.CategoriaModel;
import com.example.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired

    private CategoriaRepository repository;

    @GetMapping()
    public ResponseEntity<List<CategoriaModel>> getAllCars() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategoriaById(@PathVariable("id") Integer id) {
        return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public CategoriaModel saver(@RequestBody CategoriaModel newCategoria, Authentication auth) {
        return repository.save(newCategoria);
    }



    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaModel> update(@PathVariable("id") Integer id, @RequestBody CategoriaModel newObj) {
        CategoriaModel repo = repository.findById(id).get();
        if (repo.equals(null))
            try {
                throw new Exception("Objeto não localizado!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        repository.save(newObj);
        return ResponseEntity.ok().body(repository.findById(id).get());
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> details(Authentication auth, @RequestParam("valor") String param) {
        CategoriaModel repo = repository.findByName(param);

        if (repo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não localizada!");
        }
        return ResponseEntity.ok().body(repo);
    }
}

