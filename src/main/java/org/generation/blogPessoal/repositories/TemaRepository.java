package org.generation.blogPessoal.repositories;

import java.util.List;

import org.generation.blogPessoal.models.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Criando o Repositorio de Tema.
 * Creating Repository Tema.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * 
 */

public interface TemaRepository extends JpaRepository<Tema, Long> {

	public List<Tema> findAllByNomeTemaContainingIgnoreCase(String nomeTema);

}
