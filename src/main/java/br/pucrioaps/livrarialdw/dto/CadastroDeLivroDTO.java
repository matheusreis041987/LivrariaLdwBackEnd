package br.pucrioaps.livrarialdw.dto;

import br.pucrioaps.livrarialdw.model.entity.Categoria;
import br.pucrioaps.livrarialdw.model.entity.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastroDeLivroDTO(
        String isbn,
        @NotBlank(message = "título é obrigatório")
        String titulo,
        @NotBlank(message = "autoria é obrigatória")
        String autoria,
        @NotBlank(message = "editora é obrigatória")
        String editora,
        @NotBlank(message = "informar categoria válida")
        String categoria,
        @NotNull(message = "preço de venda obrigatório")
        BigDecimal precoVenda
) {
    public Livro toLivro() {
        return new Livro(isbn(), titulo(), autoria(), editora(),
                Categoria.valueOf(categoria()), precoVenda());
    }
}
