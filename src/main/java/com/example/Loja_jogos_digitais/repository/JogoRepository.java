package com.example.Loja_jogos_digitais.repository;

import com.example.Loja_jogos_digitais.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
    List<Jogo> findByNomeContainingIgnoreCase(String nome);
    List<Jogo> findByPrecoLessThan(Double preco);
    List<Jogo> findByPrecoGreaterThan(Double preco);
    List<Jogo> findByCategoriaContainingIgnoreCase(String nome);
}
