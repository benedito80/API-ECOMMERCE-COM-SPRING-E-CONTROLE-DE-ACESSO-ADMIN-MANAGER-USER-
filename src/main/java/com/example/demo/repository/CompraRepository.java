package com.example.demo.repository;

import com.example.demo.model.CategoriaModel;
import com.example.demo.model.CompraModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<CompraModel, Integer> {
    CompraModel findByCodigo(String codigo);

}
