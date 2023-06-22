package br.pucrioaps.livrarialdw.dto;

import br.pucrioaps.livrarialdw.model.entity.Livro;

import java.math.BigDecimal;

public record CabecalhoLivroDTO(long id, String titulo, String autoria,
                                String categoria, BigDecimal precoVenda){
    public CabecalhoLivroDTO(Livro livro){
        this(livro.getId(), livro.getTitulo(),
                livro.getAutoria(), livro.getCategoria().toString(),
                livro.getPrecoVenda());
    }
}
