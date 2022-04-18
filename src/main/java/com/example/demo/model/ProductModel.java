package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 80)
    private String description;

    @Column(nullable = false, length = 80)
    private String volume;

    private Boolean ativo = false;

    private Number price;

    private Number estoque;

    @OneToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaModel categoria;

    @OneToOne
    @JoinColumn(name = "img_id", unique = true)
    private ImgModel img;

}
