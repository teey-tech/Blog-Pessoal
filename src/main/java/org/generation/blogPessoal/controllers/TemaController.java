package org.generation.blogPessoal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.models.TemaModel;
import org.generation.blogPessoal.repositories.TemaRepository;
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

/**
 * Creating Controller Tema.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * @see TemaModel
 * @see TemaRepository
 */
@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired
	private TemaRepository repository;

	/**
	 * Select All information from the database Tema.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * 
	 */
	@GetMapping
	public ResponseEntity<List<TemaModel>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	/**
	 * Select a single information from the database containing the ID
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * @param id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TemaModel> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	/**
	 * Get all information based on the name of the Description.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * @param descricao
	 */

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<TemaModel>> getByTitle(@PathVariable String descricao) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	/**
	 * Save information in database.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * @param tema
	 */
	@PostMapping("/cadastrar")
	public ResponseEntity<TemaModel> post(@Valid @RequestBody TemaModel tema) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(tema));
	}

	/**
	 * Update a information on database.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * @param tema
	 */
	@PutMapping
	public ResponseEntity<TemaModel> put(@Valid @RequestBody TemaModel tema) {
		return repository.findById(tema.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	/**
	 * Delete the information based on database that is informed.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * @param id
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id) {
		Optional<TemaModel> tema = repository.findById(id);

		if (tema.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		repository.deleteById(id);
	}
}
