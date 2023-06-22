package br.pucrioaps.livrarialdw.controller;

import br.pucrioaps.livrarialdw.service.LivroService;
import br.pucrioaps.livrarialdw.dto.DetalheDeLivroDTO;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@WebMvcTest(LivroController.class)
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService service;

    @DisplayName("Teste de detalhamento de livro para id válido na API")
    @Test
    public void test_deve_detalhar_livro_para_id_valido() throws Exception {
        // Arrange
        when(service.detalhar(1L)).thenReturn(
                new DetalheDeLivroDTO(
                        1L,
                        "9786500019506",
                        "Engenharia de Software Moderna",
                        "Marco Tulio Valente",
                        "Independente",
                        "INFORMATICA",
                        new BigDecimal("100.90")
                )
        );

        // Act
        this.mockMvc.perform(get("/livros/1"))
                // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn",
                        Matchers.is("9786500019506")))
                .andExpect(jsonPath("$.titulo",
                        Matchers.is("Engenharia de Software Moderna")))
                .andExpect(jsonPath("$.autoria",
                        Matchers.is("Marco Tulio Valente")))
                .andExpect(jsonPath("$.editora",
                        Matchers.is("Independente")))
                .andExpect(jsonPath("$.categoria",
                        Matchers.is("INFORMATICA")))
                .andExpect(jsonPath("$.precoVenda",
                        Matchers.is(100.90)));

    }
}
