package br.pucrioaps.livrarialdw.model.repository;

import br.pucrioaps.livrarialdw.model.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
