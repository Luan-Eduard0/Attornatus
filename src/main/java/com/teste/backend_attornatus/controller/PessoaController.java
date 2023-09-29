package com.teste.backend_attornatus.controller;

import com.teste.backend_attornatus.model.dao.Pessoa;
import com.teste.backend_attornatus.model.repository.PessoaRepository;
import com.teste.backend_attornatus.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pessoa", produces = "application/json")
public class PessoaController {
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }


    @PostMapping(value = "/adicionar")
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa pessoaCriada = pessoaService.AdicionarPessoa(pessoa);
        return new ResponseEntity<>(pessoaCriada, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/atualizar")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id,  @RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.updatePessoa(id, pessoa);
        if (pessoaAtualizada != null) {
            return new ResponseEntity<>(pessoaAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.findAll();
        if (pessoas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(pessoas, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/buscarPorNome/{nome}")
    public ResponseEntity<List<Pessoa>> buscarPessoasPorNome(@PathVariable("nome") String nome) {
        List<Pessoa> pessoas = pessoaService.findByNome(nome);
        if (pessoas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(pessoas, HttpStatus.OK);
        }
    }
}
