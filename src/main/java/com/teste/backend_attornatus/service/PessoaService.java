package com.teste.backend_attornatus.service;

import com.teste.backend_attornatus.model.dao.Pessoa;
import com.teste.backend_attornatus.model.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }


    public Pessoa save(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }


    public Pessoa findById(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        return pessoaOptional.orElse(null);
    }
    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        Pessoa pessoaExistente = findById(id);
        if(pessoaExistente != null) {
            pessoaExistente.setNome(pessoa.getNome());
            pessoaExistente.setDataNascimento(pessoa.getDataNascimento());
            return pessoaRepository.save(pessoa);
        } else {
            return null;
        }

    }
    public List<Pessoa> findByNome(String nome) {
        return pessoaRepository.findByNome(nome);
    }
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }
}
