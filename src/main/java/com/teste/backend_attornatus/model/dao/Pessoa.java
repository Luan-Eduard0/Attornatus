package com.teste.backend_attornatus.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dataNasc")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    @JsonIgnore // Para evitar recursividade
    private Set<Endereco> enderecos = new HashSet<>();
}
