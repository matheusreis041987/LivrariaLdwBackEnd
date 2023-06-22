package br.pucrioaps.livrarialdw.dto;

import br.pucrioaps.livrarialdw.model.entity.Categoria;
import br.pucrioaps.livrarialdw.model.entity.Livro;

public record PesquisaLivroDTO(
        String isbn,
        String titulo,
        String autoria,
        String editora,
        String categoria) {

    public Livro toLivro() {
        Livro livro;
        if (categoria() != null) {
            livro = new Livro(isbn(), titulo(), autoria(), editora(), Categoria.valueOf(categoria()));
        } else {
            livro = new Livro(isbn(), titulo(), autoria(), editora());
        }
        return livro;
    }
}
