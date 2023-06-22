package br.pucrioaps.livrarialdw.dto;

import br.pucrioaps.livrarialdw.model.entity.Livro;

import java.math.BigDecimal;

public record DetalheDeLivroDTO(
        Long id,
        String isbn,
        String titulo,
        String autoria,
        String editora,
        String categoria,
        BigDecimal precoVenda) {

    public DetalheDeLivroDTO(Livro livro){
        this(livro.getId(), livro.getIsbn(), livro.getTitulo(), livro.getAutoria(),
                livro.getEditora(), livro.getCategoria().toString(), livro.getPrecoVenda());
    }
}
