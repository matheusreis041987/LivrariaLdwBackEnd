package br.pucrioaps.livrarialdw.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PrecoVendaLivroDTO(
        @NotNull
        Long id,
        @NotNull
        BigDecimal precoVenda
        ) {
}
