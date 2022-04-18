package com.example.demo.repository;

import com.example.demo.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    ProductModel findByName(String name);
    ProductModel findByAtivo(Boolean valor);
}
