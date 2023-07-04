package br.pucrioaps.livrarialdw.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
    @SequenceGenerator(name = "pk_sequence", sequenceName = "livro_identificador", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
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

    @Positive
    private Integer quantidade;

    private byte[] capa;

    public Livro(String isbn, String titulo, String autoria, String editora,
                 Categoria categoria, BigDecimal precoVenda, Integer quantidade,
                 byte[] capa) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autoria = autoria;
        this.editora = editora;
        this.categoria = categoria;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
        this.capa = capa;
    }

    public Livro(String isbn, String titulo, String autoria, String editora,
                 Categoria categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autoria = autoria;
        this.editora = editora;
        this.categoria = categoria;
    }

    public Livro(String isbn, String titulo, String autoria, String editora) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autoria = autoria;
        this.editora = editora;
    }
}
