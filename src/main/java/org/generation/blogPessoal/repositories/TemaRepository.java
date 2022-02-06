package org.generation.blogPessoal.repositories;

import java.util.List;

import org.generation.blogPessoal.models.TemaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Creating Repository Tema.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * @see TemaModel
 * @see TemaController
 */
@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long> {

	/**
	 * Method responsible for find user by descricao
	 * 
	 * @author Thiago Batista
	 * @since 06/02/2022
	 * @version 1.0
	 * @param descricao
	 * @return List<PostagemModel>
	 */
	public List<TemaModel> findAllByDescricaoContainingIgnoreCase(String descricao);

}
