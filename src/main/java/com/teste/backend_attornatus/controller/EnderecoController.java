package com.teste.backend_attornatus.controller;


import com.teste.backend_attornatus.model.dao.Endereco;
import com.teste.backend_attornatus.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/endereco", produces = "application/json")

public class EnderecoController {
    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping(value = "/adicionar")
    public ResponseEntity<String> criarEndereco(@Valid @RequestBody Endereco endereco) {
        Endereco enderecoCriado = (enderecoService.save(endereco));
        enderecoService.marcarEnderecoPrincipal(endereco.getPessoa().getId(), enderecoCriado.getId());
        return ResponseEntity.ok("Endereço adicionado com sucesso!");
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Endereco>> getEnderecoPorNome(@RequestParam("nome") String nome) {
        List<Endereco> enderecos = enderecoService.findByNomeEndereco(nome);
        if (!enderecos.isEmpty()) {
            return new ResponseEntity<>(enderecos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listarEnderecoPrincipal")
    public ResponseEntity<Endereco> getEnderecoPrincipal(@RequestParam("nome") String nome) {
        List<Endereco> enderecos = enderecoService.findByEnderecoPrincipal(nome);
        if (!enderecos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            String fieldName = ((FieldError) error).getField(); // Obtém o nome do campo com erro
            errors.append(fieldName).append(": ").append(errorMessage).append("\n");
        });
        return ResponseEntity.badRequest().body(errors.toString());
    }
}

