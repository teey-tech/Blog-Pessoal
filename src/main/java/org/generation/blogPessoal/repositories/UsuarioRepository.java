package org.generation.blogPessoal.repositories;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Criando o Repositorio de Usuario.
 * Creating Repository Usuario.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * 
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  public Optional<Usuario> findByUsuario(String usuario);

  public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
}
