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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.models.PostagemModel;
import org.generation.blogPessoal.repositories.PostagemRepository;

/**
 * Creating Controller Postagem.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * @see PostagemModel
 * @see PostagemRepository
 */
@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	/**
	 * Select All information from the database Postagem.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * 
	 */
	@GetMapping
	public ResponseEntity<List<PostagemModel>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	/**
	 * Select a single information from the database containing the ID.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * @param id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PostagemModel> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	/**
	 * Get all information based on the name of the title.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * @param titulo
	 */
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<PostagemModel>> getByTitle(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}

	/**
	 * Save information on database.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * @param postagem
	 */
	@PostMapping
	public ResponseEntity<PostagemModel> post(@Valid @RequestBody PostagemModel postagem) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(postagem));
	}

	/**
	 * Update a information on database.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * @param postagem
	 */
	@PutMapping
	public ResponseEntity<PostagemModel> put(@Valid @RequestBody PostagemModel postagem) {
		return repository.findById(postagem.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	/**
	 * Delete the information based on ID that is informed.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * @param id
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		Optional<PostagemModel> post = repository.findById(id);

		if (post.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		repository.deleteById(id);
	}

}
