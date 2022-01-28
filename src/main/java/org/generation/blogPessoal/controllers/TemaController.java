package org.generation.blogPessoal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.models.Tema;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Criando o Controller de Tema.
 * Creating Controller Tema.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * 
 */

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class TemaController {

	@Autowired
	private TemaRepository repository;

	/**
	 * Seleciona todas as informações de Tema no banco de dados.
	 * Select All information from the database Tema.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * 
	 */

	@GetMapping
	public ResponseEntity<List<Tema>> getAll() {
		List<Tema> list = repository.findAll();

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
	public ResponseEntity<Tema> getById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> {
			return ResponseEntity.status(200).body(resp);
		}).orElseGet(() -> {
			return ResponseEntity.status(404).build();
		});
	}

	/**
	 * Seleciona Todas as informações baseado no nome da Descrição.
	 * Get all information based on the name of the Description.
	 * 
	 * @author Thiago Batista
	 * @since 28/01/2022
	 * @version 1.0
	 * 
	 */

	@GetMapping("/desc/{descricao}")
	public ResponseEntity<List<Tema>> getByDesc(@PathVariable String descricao) {
		List<Tema> titulo = repository.findAllByDescricaoContainingIgnoreCase(descricao);

		if (titulo.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(titulo);
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
	public ResponseEntity<Tema> savePost(@Valid @RequestBody Tema tema) {
		return ResponseEntity.status(201).body(repository.save(tema));
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
	public ResponseEntity<Tema> updatePost(@Valid @RequestBody Tema tema) {
		return repository.findById(tema.getId()).map(record -> {
			return ResponseEntity.status(201).body(repository.save(tema));
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
		Optional<Tema> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).build();

		} else {
			return ResponseEntity.status(404).build();
		}
	}
}
