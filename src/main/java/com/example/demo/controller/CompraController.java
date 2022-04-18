package com.example.demo.controller;

import com.example.demo.model.CategoriaModel;
import com.example.demo.model.CompraModel;
import com.example.demo.repository.CompraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {
    private final CompraRepository repository;


    public CompraController(CompraRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<CompraModel>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public CompraModel salvar(@RequestBody CompraModel compra) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss-SSSS");
        String cod = agora.format(formatter);

        compra.setCodigo("COD:" + cod);
        return repository.save(compra);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> details(Authentication auth, @RequestParam("valor") String param) {
        CompraModel repo = repository.findByCodigo(param);

        if (repo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra não localizada!");
        }
        return ResponseEntity.ok().body(repo);
    }

}
