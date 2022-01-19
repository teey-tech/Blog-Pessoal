package org.generation.blogPessoal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.generation.blogPessoal.models.Postagem;
import org.generation.blogPessoal.repositories.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
			return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping("/save")
	public  ResponseEntity<Postagem> savePostagem(@RequestBody Postagem newPostagem) {
		return  ResponseEntity.status(201).body(repository.save(newPostagem));
	}
	

	@PutMapping(value="/update/{id}")
	public  ResponseEntity<Postagem> updatePostagem(@PathVariable("id") long id, @RequestBody Postagem updatePostagem) {
		return repository.findById(id).map(record -> {
			return  ResponseEntity.status(201).body(repository.save(updatePostagem));
		}).orElseGet(() -> {
			return ResponseEntity.status(404).build();
		});
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Postagem> updatePostagem(@PathVariable("id") long id) {
		repository.deleteById(id);
		return  ResponseEntity.status(201).build();
		
			
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getId(@PathVariable Long id){
		return repository.findById(id).map(resp -> {
			return ResponseEntity.status(200).body(resp);
		}).orElseGet(() -> {
			return ResponseEntity.status(404).build();
		});
	}
	
	
	
	@RequestMapping(value = "/titulo/{titulo}", method = RequestMethod.GET)
	public ResponseEntity<Postagem> getByTitle(@RequestParam(value="titulo") String titulo){
		return repository.getByTitulo(titulo).map(resp -> {
			return ResponseEntity.status(200).body(resp);
		}).orElseGet(() -> {
			return ResponseEntity.status(404).build();
		});
	}
	
	
	
}
