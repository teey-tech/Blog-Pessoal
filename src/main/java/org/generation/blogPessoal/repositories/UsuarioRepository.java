package org.generation.blogPessoal.repositories;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Creating Repository Usuario.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * @see UsuarioModel
 * @see UsuarioController
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

  /**
   * Method responsible for find user by email
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @param usuario
   * @return Optional<UsuarioModel>
   */
  public Optional<UsuarioModel> findByUsuario(String usuario);

  /**
   * Method responsible for find user by nome
   * 
   * @author Thiago Batista
   * @since 06/02/2022
   * @version 1.0
   * @param nome
   * @return List<UsuarioModel>
   */
  public List<UsuarioModel> findAllByNomeContainingIgnoreCase(String nome);
}
