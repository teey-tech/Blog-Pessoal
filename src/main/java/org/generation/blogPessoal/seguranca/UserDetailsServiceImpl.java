package org.generation.blogPessoal.seguranca;

import java.util.Optional;

import org.generation.blogPessoal.models.UsuarioModel;
import org.generation.blogPessoal.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class responsible for service loadUserByUsername.
 * 
 * @author Thiago Batista
 * @since 06/02/2022
 * @version 1.0
 * @see UserDetailsService
 * @see UsuarioRepository
 * @see UsuarioModel
 * @see UserDetailsImplement
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<UsuarioModel> optional = repository.findByUsuario(username);

    if (optional.isPresent()) {
      return new UserDetailsImpl(optional.get());
    } else {
      throw new UsernameNotFoundException("Usuario não existe");
    }
  }
}