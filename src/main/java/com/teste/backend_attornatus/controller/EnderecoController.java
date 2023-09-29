package com.teste.backend_attornatus.controller;


import com.teste.backend_attornatus.model.dao.Endereco;
import com.teste.backend_attornatus.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
        Endereco enderecoCriado = (enderecoService.save(endereco));
        enderecoService.marcarEnderecoPrincipal(endereco.getPessoa().getId(), enderecoCriado.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
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
}

