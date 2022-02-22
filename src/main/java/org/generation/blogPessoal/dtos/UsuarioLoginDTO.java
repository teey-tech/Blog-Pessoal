package org.generation.blogPessoal.dtos;

/**
 * Class responsible for DTO Usuariologin.
 * 
 * @author Thiago Batista
 * @since 06/02/2022
 * @version 1.0
 * @see UsuarioModel
 */
public class UsuarioLoginDTO {

  private Long id;

  private String nome;

  private String usuario;

  private String foto;

  private String tipo;

  private String senha;

  private String token;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
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

  public String getFoto() {
    return this.foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getTipo() {
    return this.tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
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
