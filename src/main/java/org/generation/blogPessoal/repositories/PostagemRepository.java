package org.generation.blogPessoal.repositories;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.models.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);

	public List<Postagem> findAll();

//	public Optional<Postagem> getByTitulo(String titulo);

	

}
