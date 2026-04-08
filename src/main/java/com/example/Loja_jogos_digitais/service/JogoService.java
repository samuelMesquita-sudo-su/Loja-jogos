package com.example.Loja_jogos_digitais.service;

import com.example.Loja_jogos_digitais.model.Jogo;
import com.example.Loja_jogos_digitais.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {
    private final JogoRepository repository;

    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    public Jogo cadastrar(Jogo jogo){
        return repository.save(jogo);
    }

    public List<Jogo> listar(){
        return repository.findAll();
    }

    public List<Jogo> buscarPorNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Jogo> buscarPorPrecoMenor(Double preco){
        return repository.findByPrecoLessThan(preco);
    }

    public List<Jogo> buscarPorPrecoMaior(Double preco){
        return repository.findByPrecoGreaterThan(preco);
    }

    public List<Jogo> buscarPorCategoria(String categoria){
        return repository.findByCategoriaContainingIgnoreCase(categoria);
    }

    public Jogo buscar(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id nao encontrado"));
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

}
