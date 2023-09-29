package com.teste.backend_attornatus.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @Column(name = "dataNasc")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @PastOrPresent(message = "A data deve ser anterior ou igual a data atual")
    @NotNull(message = "Data de nascimento não pode ser vazio")
    private Date dataNascimento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    @JsonIgnore // Para evitar recursividade
    private Set<Endereco> enderecos = new HashSet<>();
}
