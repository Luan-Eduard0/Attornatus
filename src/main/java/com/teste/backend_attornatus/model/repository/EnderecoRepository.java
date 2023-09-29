package com.teste.backend_attornatus.model.repository;

import com.teste.backend_attornatus.model.dao.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    @Query("SELECT e FROM Endereco e JOIN e.pessoa p WHERE p.nome = :nome")
    List<Endereco> findByNomeEndereco(@Param("nome") String nome);

    @Query("SELECT e FROM Endereco e JOIN e.pessoa p WHERE e.enderecoPrincipal = true AND p.nome = :nome")
    List<Endereco> findByEnderecoPrincipal(@Param("nome") String nome);

    @Query("SELECT e FROM Endereco e WHERE e.pessoa.id = :pessoaId AND e.enderecoPrincipal = true")
    Optional<Endereco> findEnderecoPrincipalByPessoaId(@Param("pessoaId") Long pessoaId);

    @Query("SELECT e FROM Endereco e WHERE e.pessoa.id = :pessoaId")
    List<Endereco> findEnderecosByPessoaId(Long pessoaId);
}
