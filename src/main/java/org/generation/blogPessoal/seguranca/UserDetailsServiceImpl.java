package org.generation.blogPessoal.seguranca;

import java.util.Optional;

import org.generation.blogPessoal.models.Usuario;
import org.generation.blogPessoal.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Criando o Arquivo que cuidara dos detalhes do serviço.
 * Creating the archive that is going to handle the details service.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * 
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Optional<Usuario> user = userRepository.findByUsuario(userName);
    user.orElseThrow(() -> new UsernameNotFoundException(userName + "not found."));

    return user.map(UserDetailsImpl::new).get();
  }
}