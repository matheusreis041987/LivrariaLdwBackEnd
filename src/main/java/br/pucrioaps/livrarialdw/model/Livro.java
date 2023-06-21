package br.pucrioaps.livrarialdw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name="livro")
@Entity(name = "Livro")
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String titulo;
    private String autoria;
    private String editora;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Column(name = "in_referencia")
    private BigDecimal precoVenda;
}
