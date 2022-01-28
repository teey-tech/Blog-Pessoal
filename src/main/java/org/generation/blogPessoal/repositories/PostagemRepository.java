package org.generation.blogPessoal.repositories;

import java.util.List;

import org.generation.blogPessoal.models.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Criando o Repositorio de Postagem.
 * Creating Repository Postagem.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * 
 */

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

	public List<Postagem> findAll();
}
