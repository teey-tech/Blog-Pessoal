package org.generation.blogPessoal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.models.Postagem;
import org.generation.blogPessoal.repositories.PostagemRepository;

/**
 * Criando o Controller de Postagem.
 * Creating Controller Postagem.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * 
 */

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	/**
	 * Seleciona todas as informações de Postagem no banco de dados.
	 * Select All information from the database Postagem.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * 
	 */

	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		List<Postagem> list = repository.findAll();

		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(201).body(list);
		}

	}

	/**
	 * Seleciona todas as informações que contem esse ID.
	 * Select a single information from the database containing the ID
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * 
	 */

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getId(@PathVariable Long id) {
		return repository.findById(id).map(resp -> {
			return ResponseEntity.status(200).body(resp);
		}).orElseGet(() -> {
			return ResponseEntity.status(404).build();
		});
	}

	/**
	 * Seleciona Todas as informações baseado no nome do titulo.
	 * Get all information based on the name of the title.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * 
	 */

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByName(@PathVariable String titulo) {
		List<Postagem> title = repository.findAllByTituloContainingIgnoreCase(titulo);

		if (title.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(title);
		}
	}

	/**
	 * Insere informação no Banco de dados.
	 * Insert information in DB.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * 
	 */

	@PostMapping("/save")
	public ResponseEntity<Postagem> savePost(@Valid @RequestBody Postagem newPost) {
		return ResponseEntity.status(201).body(repository.save(newPost));
	}

	/**
	 * Atualiza informação no banco de dados.
	 * Update a information on DB by ID.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * 
	 */

	@PutMapping("/update")
	public ResponseEntity<Postagem> updatePost(@Valid @RequestBody Postagem updatePost) {
		return repository.findById(updatePost.getId()).map(record -> {
			return ResponseEntity.status(201).body(repository.save(updatePost));
		}).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
		});
	}

	/**
	 * Deleta a informação baseado no ID informado.
	 * delete the information based on ID that is informed.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * 
	 */

	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity deletePost(@PathVariable("id") long id) {
		Optional<Postagem> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).build();

		} else {
			return ResponseEntity.status(404).build();
		}
	}

}
