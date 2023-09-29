package com.teste.backend_attornatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.teste.backend_attornatus.controller.EnderecoController;
import com.teste.backend_attornatus.model.dao.Endereco;
import com.teste.backend_attornatus.model.dao.Pessoa;
import com.teste.backend_attornatus.model.repository.PessoaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class EnderecoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PessoaRepository pessoaRepository;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        // Initialize the ObjectMapper if not already injected
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new StdDateFormat());
    }

    @BeforeEach
    public void createPessoa() throws Exception {
        // Create a Pessoa
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Irineu");
        pessoa.setDataNascimento(Date.valueOf(LocalDate.of(1990, 1, 1)));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pessoa/adicionar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }




    @Test
    public void testCriarEndereco() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Irineu");
        pessoa.setDataNascimento(Date.valueOf(LocalDate.of(1990, 1, 1)));
        pessoaRepository.save(pessoa);

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Teste");
        endereco.setCep("12345-678");
        endereco.setNumero(123);
        endereco.setCidade("São Paulo");
        endereco.setEnderecoPrincipal(true);
        endereco.setPessoa(pessoa);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/endereco/adicionar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(endereco)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Endereco adicionado com sucesso!"));
    }



    @Test
    public void testListarEnderecoPrincipal() throws Exception {
        String nome = "Irineu";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/endereco/listarEnderecoPrincipal")
                        .param("nome", nome)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void testListarEnderecoPorNome() throws Exception {
        String nome = "Irineu";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/endereco/listar")
                        .param("nome", nome)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()) // Expecting a 404 status
                .andReturn();
    }
}
