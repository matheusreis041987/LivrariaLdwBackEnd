package br.pucrioaps.livrarialdw.service;

import br.pucrioaps.livrarialdw.dto.*;
import br.pucrioaps.livrarialdw.model.entity.Livro;
import br.pucrioaps.livrarialdw.model.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;
    public DetalheDeLivroDTO detalhar(Long id) {
        Livro livro = repository.getReferenceById(id);
        return new DetalheDeLivroDTO(livro);
    }

    public List<CabecalhoLivroDTO> listar(PesquisaLivroDTO pesquisaLivroDTO) {
        Livro livro = pesquisaLivroDTO.toLivro();
        Example<Livro> example = Example.of(livro);
        List<Livro> livros = repository.findAll(example);
        return livros.stream().map(
                CabecalhoLivroDTO::new
        ).toList();
    }

    public DetalheDeLivroDTO salvar(CadastroDeLivroDTO dados) {
        Livro livroASalvar = dados.toLivro();
        Livro livroSalvo = this.repository.save(livroASalvar);
        return new DetalheDeLivroDTO(livroSalvo);
    }

    public CabecalhoLivroDTO atualizar(PrecoVendaLivroDTO dto) {
        Livro livroAAtualizar = repository.getReferenceById(dto.id());
        livroAAtualizar.setPrecoVenda(dto.precoVenda());
        repository.save(livroAAtualizar);
        return new CabecalhoLivroDTO(livroAAtualizar);
    }
}
