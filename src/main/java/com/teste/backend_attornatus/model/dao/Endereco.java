package com.teste.backend_attornatus.model.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "endereco_pessoas")
@Getter
@Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Logradouro")
    private String logradouro;

    @Column(name = "Cep")
    private String cep;

    @Column(name = "Numero")
    private Integer numero;

    @Column(name = "Cidade")
    private String cidade;

    @Column(name = "EnderecoPrincipal")
    private Boolean enderecoPrincipal;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;


}
