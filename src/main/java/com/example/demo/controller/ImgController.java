package com.example.demo.controller;

import com.example.demo.model.ImgModel;
import com.example.demo.repository.ImgRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/img")
public class ImgController {
    private final ImgRepository repository;

    public ImgController(ImgRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<ImgModel>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ImgModel salvar(@RequestBody ImgModel img) {
        return repository.save(img);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
