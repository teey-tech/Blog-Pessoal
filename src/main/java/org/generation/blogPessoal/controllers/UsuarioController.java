package org.generation.blogPessoal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.dtos.UsuarioLoginDTO;
import org.generation.blogPessoal.models.UsuarioModel;
import org.generation.blogPessoal.repositories.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Creating Controller Usuario.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * @see UsuarioService
 * @see UsuarioModel
 * @see UsuarioRepository
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

  @Autowired
  private UsuarioService service;

  @Autowired
  private UsuarioRepository repository;

  /**
   * Get all user informations stored on data base.
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @see UsuariosModel
   */
  @GetMapping("/all")
  public ResponseEntity<List<UsuarioModel>> getAll() {
    return ResponseEntity.ok(repository.findAll());
  }

  /**
   * Get a user based on the id.
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @see UsuarioModel
   * @param id
   */
  @GetMapping("/{id}")
  public ResponseEntity<UsuarioModel> getById(@PathVariable long id) {
    return repository.findById(id)
        .map(resp -> ResponseEntity.ok(resp))
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Verify user identification and login into the system.
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @see UsuarioService
   * @see UsuarioModel
   * @param usuario
   */
  @PostMapping("/logar")
  public ResponseEntity<UsuarioLoginDTO> autenticationUsuario(@RequestBody Optional<UsuarioLoginDTO> usuario) {
    return service.logarUsuario(usuario)
        .map(resp -> ResponseEntity.ok(resp))
        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
  }

  /**
   * Create a new user on database.
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @see UsuarioModel
   * @see UsuarioService
   * @param usuario
   */
  @PostMapping("/cadastrar")
  public ResponseEntity<UsuarioModel> postUsuario(@Valid @RequestBody UsuarioModel usuario) {
    return service.cadastrarUsuario(usuario)
        .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
        .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }

  /**
   * Update user on database.
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @see UsuarioModel
   * @see UsuarioService
   * @param usuario
   */
  @PutMapping("/atualizar")
  public ResponseEntity<UsuarioModel> putUsuario(@Valid @RequestBody UsuarioModel usuario) {
    return service.atualizarUsuario(usuario)
        .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
        .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }

}
