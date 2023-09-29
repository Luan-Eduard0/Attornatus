package com.teste.backend_attornatus.model.repository;

import com.teste.backend_attornatus.model.dao.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT p FROM Pessoa p JOIN p.enderecos e WHERE p.nome = :nome")
    List<Pessoa> findByNome(@Param("nome") String nome);

}
