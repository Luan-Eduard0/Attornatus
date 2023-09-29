package com.teste.backend_attornatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.backend_attornatus.controller.EnderecoController;
import com.teste.backend_attornatus.controller.PessoaController;
import com.teste.backend_attornatus.model.dao.Endereco;
import org.junit.Test;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PessoaController.class)
@AutoConfigureMockMvc
public class EnderecoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCriarEndereco() throws Exception {
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Teste");
        endereco.setCep("12345-678");
        endereco.setNumero(123);
        endereco.setCidade("São Paulo");
        endereco.setEnderecoPrincipal(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/endereco/adicionar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(endereco)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Endereço criado com sucesso!"));

    }
    @Test
    public void testListarEnderecoPorNome() throws Exception {
        // Suponha que você deseja listar endereços com base no nome "Irineu".
        String nome = "Irineu";

        // Execute a solicitação GET para listar endereços com base no nome.
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/endereco/listar")
                        .param("nome", nome)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

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

}


