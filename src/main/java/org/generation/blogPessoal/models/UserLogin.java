package org.generation.blogPessoal.models;

/**
 * Criando o Model UserLogin.
 * Que sera usado para checar as informações de Login.
 * 
 * Creating model UserLogin.
 * Is going to be used to check the information on a login try.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * 
 */

public class UserLogin {

  private String nome;

  private String usuario;

  private String senha;

  private String token;

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

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}
