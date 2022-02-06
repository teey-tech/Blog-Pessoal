package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.dtos.UsuarioLoginDTO;
import org.generation.blogPessoal.models.UsuarioModel;
import org.generation.blogPessoal.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Class responsible for the user services..
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * @see UsuarioModel
 */

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  /**
   * Private static method, used to encrypt with BCryptPasswordEncoder format a
   * string passed as a parameter.
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @see BCryptPasswordEncoder
   * @param senha
   * @return String
   * 
   */
  private String criptografarSenha(String senha) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String senhaEncoder = encoder.encode(senha);
    return senhaEncoder;
  }

  /**
   * Private boolean method, used to compare the typed password and the password
   * stored in data base if matches the user can login into the system.
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @see Base64
   * @param senhaDigitada
   * @param senhaBanco
   * @return boolean
   * 
   */
  private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.matches(senhaDigitada, senhaBanco);
  }

  /**
   * Private static method, used to generate basic token.
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @see Base64
   * @param email
   * @param password
   * @return String
   * 
   */
  private String generatorBasicToken(String email, String password) {
    String structure = email + ":" + password;
    byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
    return "Basic " + new String(structureBase64);
  }

  /**
   * Public method used to register a user in the system's database. This method
   * returns a BAD_REQUEST if the intention to register already has an email
   * registered in the system, to avoid duplication.
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @param usuario
   * @return ResponseEntity<UsuarioModel>
   * 
   */
  public Optional<UsuarioModel> cadastrarUsuario(UsuarioModel usuario) {
    if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
    usuario.setSenha(criptografarSenha(usuario.getSenha()));
    return Optional.of(usuarioRepository.save(usuario));
  }

  /**
   * Public method used to update a user in the system's database. This method
   * returns a BAD_REQUEST if the user tries to update a diferent user
   * registered in the system, to avoid errors.
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @param usuario
   * @return ResponseEntity<UsuarioModel>
   * 
   */
  public Optional<UsuarioModel> atualizarUsuario(UsuarioModel usuario) {
    if (usuarioRepository.findById(usuario.getId()).isPresent()) {
      Optional<UsuarioModel> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
      if (buscaUsuario.isPresent()) {
        if (buscaUsuario.get().getId() != usuario.getId())
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
      }
      usuario.setSenha(criptografarSenha(usuario.getSenha()));
      return Optional.of(usuarioRepository.save(usuario));
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
  }

  /**
   * Public method to login user into the system.
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @param usuarioLogin
   * @return Optional<UsuarioLoginDTO>
   * 
   * 
   */
  public Optional<UsuarioLoginDTO> logarUsuario(Optional<UsuarioLoginDTO> usuarioLogin) {
    Optional<UsuarioModel> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
    if (usuario.isPresent()) {
      if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
        usuarioLogin.get().setId(usuario.get().getId());
        usuarioLogin.get().setNome(usuario.get().getNome());
        usuarioLogin.get().setFoto(usuario.get().getFoto());
        usuarioLogin.get()
            .setToken(generatorBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
        usuarioLogin.get().setSenha(usuario.get().getSenha());
        return usuarioLogin;
      }
    }
    throw new ResponseStatusException(
        HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
  }
}
