package com.teste.backend_attornatus.model.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Logradouro não pode ser vazio")
    private String logradouro;

    @Column(name = "Cep")
    @NotBlank(message = "Cep não pode ser vazio")
    private String cep;

    @Column(name = "Numero")
    @NotNull(message = "Numero da casa é obrigatório")
    private Integer numero;

    @Column(name = "Cidade")
    @NotBlank(message = "Cidade é obrigatório")
    private String cidade;

    @Column(name = "EnderecoPrincipal")
    @NotNull(message = "Endereço principal deve ser definido como true ou false")
    private Boolean enderecoPrincipal;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;


}
