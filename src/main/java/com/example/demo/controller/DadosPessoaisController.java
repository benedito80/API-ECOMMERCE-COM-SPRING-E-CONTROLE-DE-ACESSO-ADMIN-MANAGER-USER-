package com.example.demo.controller;

import com.example.demo.model.DadosPessoaisModel;
import com.example.demo.repository.DadosPessoaisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dadosPessoais")
public class DadosPessoaisController {
    private final DadosPessoaisRepository repository;

    public DadosPessoaisController(DadosPessoaisRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<DadosPessoaisModel>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public DadosPessoaisModel salvar(@RequestBody DadosPessoaisModel dadosPessoais) {
        return repository.save(dadosPessoais);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
