package br.pucrioaps.livrarialdw.controller;

import br.pucrioaps.livrarialdw.dto.CadastroDeLivroDTO;
import br.pucrioaps.livrarialdw.dto.PrecoVendaLivroDTO;
import br.pucrioaps.livrarialdw.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping("/livros/{id}")
    public ResponseEntity detalhar(
            @PathVariable Long id
    ){
        var dto = this.service.detalhar(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/livros")
    public ResponseEntity listar(){
        var dto = this.service.listar();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cadastrar_livro")
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody @Valid CadastroDeLivroDTO dto,
            UriComponentsBuilder uriBuilder
            ) {
        var resposta = this.service.salvar(dto);
        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(resposta.id()).toUri();
        return ResponseEntity.created(uri).body(resposta);
    }

    @PutMapping("/atualizar_preco_venda")
    @Transactional
    public ResponseEntity atualizar(
            @RequestBody @Valid PrecoVendaLivroDTO dto
            ){
        var resposta = this.service.atualizar(dto);
        return ResponseEntity.ok(resposta);

    }
}
