package br.pucrioaps.livrarialdw.dto;

import br.pucrioaps.livrarialdw.model.entity.Categoria;
import br.pucrioaps.livrarialdw.model.entity.Livro;

import java.math.BigDecimal;

public record CadastroDeLivroDTO(
        String isbn,
        String titulo,
        String autoria,
        String editora,
        String categoria,
        BigDecimal precoVenda
) {
    public Livro toLivro() {
        return new Livro(isbn(), titulo(), autoria(), editora(),
                Categoria.valueOf(categoria()), precoVenda());
    }
}
