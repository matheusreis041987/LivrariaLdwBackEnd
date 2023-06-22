package br.pucrioaps.livrarialdw.controller;

import br.pucrioaps.livrarialdw.dto.CabecalhoLivroDTO;
import br.pucrioaps.livrarialdw.infra.exception.TratadorDeErros;
import br.pucrioaps.livrarialdw.service.LivroService;
import br.pucrioaps.livrarialdw.dto.DetalheDeLivroDTO;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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

    @DisplayName("Teste de detalhamento de livro para Id inexistente na API")
    @Test
    public void test_nao_deve_detalhar_livro_para_id_invalido() throws Exception {
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
        this.mockMvc = MockMvcBuilders.standaloneSetup(
                this.mockMvc).setControllerAdvice(
                TratadorDeErros.class).build();

        // Act
        this.mockMvc.perform(get("/livros/2"))

                // Assert
                .andExpect(status().isNotFound());


    }

    @DisplayName("Teste de listagem de livros para repositorio com apenas um")
    @Test
    public void test_deve_listar_unico_livro_em_repositorio() throws Exception {
        // Arrange
        when(service.listar()).thenReturn(
                List.of(new CabecalhoLivroDTO(
                        1L,
                        "Engenharia de Software Moderna",
                        "Marco Tulio Valente",
                        "INFORMATICA",
                        new BigDecimal("100.90")
                        ))
        );


        // Act
        this.mockMvc.perform(get("/livros"))
                // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id",
                        Matchers.is(1)))
                .andExpect(jsonPath("$[0].titulo",
                        Matchers.is("Engenharia de Software Moderna")))
                .andExpect(jsonPath("$[0].autoria",
                        Matchers.is("Marco Tulio Valente")))
                .andExpect(jsonPath("$[0].categoria",
                        Matchers.is("INFORMATICA")))
                .andExpect(jsonPath("$[0].precoVenda",
                        Matchers.is(100.90)));

    }

    @DisplayName("Teste de listagem de livros para repositorio vazio")
    @Test
    public void test_deve_retornar_lista_vazia_se_nao_ha_livro_em_repositorio() throws Exception {
        // Arrange
        when(service.listar()).thenReturn(
                List.of()
        );


        // Act
        this.mockMvc.perform(get("/livros"))
                // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.hasSize(0)));

    }

    @DisplayName("Teste de listagem de livros para repositorio com mais de um")
    @Test
    public void test_deve_listar_livros_em_repositorio() throws Exception {
        // Arrange
        when(service.listar()).thenReturn(
                List.of(new CabecalhoLivroDTO(
                        1L,
                        "Engenharia de Software Moderna",
                        "Marco Tulio Valente",
                        "INFORMATICA",
                        new BigDecimal("100.90")
                ),
                        new CabecalhoLivroDTO(
                                2L,
                                "Entrega Contínua",
                                "Jez Humble, David Farley",
                                "INFORMATICA",
                                new BigDecimal("208.00")
                        ))
        );


        // Act
        this.mockMvc.perform(get("/livros"))
                // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id",
                        Matchers.is(1)))
                .andExpect(jsonPath("$[0].titulo",
                        Matchers.is("Engenharia de Software Moderna")))
                .andExpect(jsonPath("$[0].autoria",
                        Matchers.is("Marco Tulio Valente")))
                .andExpect(jsonPath("$[0].categoria",
                        Matchers.is("INFORMATICA")))
                .andExpect(jsonPath("$[0].precoVenda",
                        Matchers.is(100.90)))
                .andExpect(jsonPath("$[1].id",
                        Matchers.is(2)))
                .andExpect(jsonPath("$[1].titulo",
                        Matchers.is("Entrega Contínua")))
                .andExpect(jsonPath("$[1].autoria",
                        Matchers.is("Jez Humble, David Farley")))
                .andExpect(jsonPath("$[1].categoria",
                        Matchers.is("INFORMATICA")))
                .andExpect(jsonPath("$[1].precoVenda",
                        Matchers.is(208.00)));

    }
}
