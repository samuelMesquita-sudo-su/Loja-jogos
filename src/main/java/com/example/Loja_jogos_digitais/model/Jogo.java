package com.example.Loja_jogos_digitais.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "E necessario ter um nome")
    private String nome;

    @NotNull(message = "E necessario ter preco")
    @Min(0)
    private Double preco;

    @NotBlank(message = "E necessario ter uma categoria")
    private String categoria;

    @NotBlank(message = "E necessario ter um codigo de ativacao")
    @Column(unique = true)
    private String codigo_ativacao;

}
