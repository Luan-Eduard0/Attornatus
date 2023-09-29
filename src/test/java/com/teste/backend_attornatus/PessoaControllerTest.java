package com.teste.backend_attornatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.backend_attornatus.controller.PessoaController;
import com.teste.backend_attornatus.model.dao.Pessoa;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDate;

@WebMvcTest(PessoaController.class)
@AutoConfigureMockMvc
public class PessoaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testInserirPessoa() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setDataNascimento
                (Date.valueOf(LocalDate.of(1990, 1, 1)));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pessoa/adicionar")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Pessoa criada com sucesso!"));
    }
    @Test
    public void testEditarPessoa() throws Exception {
        // Crie um objeto Pessoa existente com um ID válido
        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setId(1L);
        pessoaExistente.setNome("Maria");
        pessoaExistente
                .setDataNascimento(Date.valueOf(LocalDate.of(1985, 8, 20)));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/pessoa/{id}/atualizar")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(pessoaExistente)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Pessoa editada com sucesso!"));
    }
    @Test
    public void testListarPessoas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoa/listar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()")
                        .value(1));

    }

}