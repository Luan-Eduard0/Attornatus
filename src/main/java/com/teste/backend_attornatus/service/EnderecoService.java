package com.teste.backend_attornatus.service;

import com.teste.backend_attornatus.model.dao.Endereco;
import com.teste.backend_attornatus.model.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
    public List<Endereco> findByNomeEndereco(String nome) {
        return enderecoRepository.findByNomeEndereco(nome);
    }
    public List<Endereco> findByEnderecoPrincipal(String nome) {
        return enderecoRepository.findByEnderecoPrincipal(nome);
    }
    //Metodo para Houver apenas um endereço principal
    @Transactional
    public void marcarEnderecoPrincipal(Long pessoaId, Long enderecoId) {
        Optional<Endereco> novoEnderecoOptional = enderecoRepository.findById(enderecoId);

        novoEnderecoOptional.ifPresent(novoEndereco -> {
            if (novoEndereco.getEnderecoPrincipal()) {
                List<Endereco> enderecos = enderecoRepository.findEnderecosByPessoaId(pessoaId);

                for (Endereco endereco : enderecos) {
                    if (!endereco.equals(novoEndereco)) {
                        endereco.setEnderecoPrincipal(false);
                    }
                }

                enderecoRepository.saveAll(enderecos);
            }
        });
    }


}
