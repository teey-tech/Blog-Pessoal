package org.generation.blogPessoal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Criando o Model Usuario.
 * Creating User Model.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * 
 */

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Size(min = 2, max = 100)
  private String nome;

  @NotNull
  @Size(min = 5, max = 100)
  private String usuario;

  @NotNull
  @Size(min = 5, max = 100)
  private String senha;

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getUsuario() {
    return this.usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

}
