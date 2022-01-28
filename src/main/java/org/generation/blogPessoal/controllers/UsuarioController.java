package org.generation.blogPessoal.controllers;

import java.util.Optional;

import org.generation.blogPessoal.models.UserLogin;
import org.generation.blogPessoal.models.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Criando o Controller de Usuario.
 * Creating Controller Usuario.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * 
 */

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @PostMapping("/logar")
  public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
    return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(usuarioService.CadastrarUsuario(usuario));
  }
}
