package com.teste.backend_attornatus.controller;

import com.teste.backend_attornatus.model.dao.Pessoa;
import com.teste.backend_attornatus.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<String> criarPessoa(@Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaCriada = pessoaService.save(pessoa);
        return ResponseEntity.ok().body("Pessoa  Adicionada  Com  Sucesso!");
    }


    @PutMapping(value = "/{id}/atualizar")
    public ResponseEntity<Pessoa> atualizarPessoa( @PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
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
    //Tratamento de erros
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage(); // Obtém a mensagem de erro padrão do Spring Validation
            String fieldName = ((FieldError) error).getField(); // Obtém o nome do campo com erro
            errors.append(fieldName).append(": ").append(errorMessage).append("\n");
        });
        return ResponseEntity.badRequest().body(errors.toString());
    }

}
