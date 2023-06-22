package br.pucrioaps.livrarialdw.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PrecoVendaLivroDTO(
        @NotNull
        Long id,
        @NotNull
                @Positive(message = "preço de venda deve ser positivo")
        BigDecimal precoVenda
        ) {
}
