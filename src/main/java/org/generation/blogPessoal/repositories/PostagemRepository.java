package org.generation.blogPessoal.repositories;

import java.util.List;

import org.generation.blogPessoal.models.PostagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Creating Repository Postagem.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * @see PostagemModel
 * @see PostagemController
 */
@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {

	/**
	 * Method responsible for find user by titulo
	 * 
	 * @author Thiago Batista
	 * @since 06/02/2022
	 * @version 1.0
	 * @param titulo
	 * @return List<PostagemModel>
	 */
	public List<PostagemModel> findAllByTituloContainingIgnoreCase(String titulo);
}
