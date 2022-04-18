package com.example.demo.model;

import com.example.demo.util.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "compra")
public class CompraModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String codigo;//não informe...(gerado automaticamente)

    @OneToOne
    @JoinColumn(name = "username_id")
    private UserModel username;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data = LocalDate.now();

    @ManyToMany
    @JoinTable(name = "compra_itens",
            joinColumns = @JoinColumn(name = "compra_id"))
    private List<ItemModel> itens;

    @Column(name = "funcao")
    @Enumerated(value = EnumType.STRING)
    public StatusEnum status;

    private Number desconto;

    private Number total;
}
