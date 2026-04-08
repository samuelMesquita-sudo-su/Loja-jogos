package com.example.Loja_jogos_digitais.controller;

import com.example.Loja_jogos_digitais.model.Jogo;
import com.example.Loja_jogos_digitais.service.JogoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogo")
public class JogoController {
    private final JogoService service;

    public JogoController(JogoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Jogo> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Jogo listarPorId(@PathVariable Long id){
        return service.buscar(id);
    }

    @GetMapping("/nome")
    public List<Jogo> buscarPorNome(@RequestParam String nome){
        return service.buscarPorNome(nome);
    }

    @GetMapping("/preco/menor")
    public List<Jogo> buscarPorPrecoMenor(@RequestParam Double preco){
        return service.buscarPorPrecoMenor(preco);
    }

    @GetMapping("/preco/maior")
    public List<Jogo> buscarPorPrecoMaior(@RequestParam Double preco){
        return service.buscarPorPrecoMaior(preco);
    }

    @GetMapping("/categoria")
    public List<Jogo> buscarPorCategoria(@RequestParam String categoria){
        return service.buscarPorCategoria(categoria);
    }

    @PostMapping
    public Jogo cadastrar (@RequestBody @Valid Jogo jogo){
        return service.cadastrar(jogo);
    }

    @PutMapping("/{id}")
    public Jogo atualizar (@PathVariable Long id, @RequestBody @Valid Jogo jogo){
        jogo.setId(id);
        return service.cadastrar(jogo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
