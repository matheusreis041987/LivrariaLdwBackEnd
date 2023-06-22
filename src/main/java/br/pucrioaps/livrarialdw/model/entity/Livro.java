package br.pucrioaps.livrarialdw.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name="livro")
@Entity(name = "Livro")
@Getter
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
    @Column(name = "preco_venda")
    @Setter
    @Positive
    private BigDecimal precoVenda;

    public Livro(String isbn, String titulo, String autoria, String editora,
                 Categoria categoria, BigDecimal precoVenda) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autoria = autoria;
        this.editora = editora;
        this.categoria = categoria;
        this.precoVenda = precoVenda;

    }
}
