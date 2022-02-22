package org.generation.blogPessoal.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Creating User Model.
 * 
 * @author Thiago Batista
 * @since 28/01/2022
 * @version 1.0
 * @see UsuarioLoginDTO
 * @see UsuarioRepository
 * @see UsuarioController
 */
@Entity
@Table(name = "tb_usuarios")
public class UsuarioModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull(message = "O atributo Nome é Obrigatório!")
  private String nome;

  @Size(max = 5000, message = "o link da foto não pode ser maior do que 5000 caractéres")
  private String foto;

  private String tipo;

  @NotNull(message = "O atributo Usuário é Obrigatório!")
  @Email(message = "O Atributo Usuário deve ser um email válido")
  private String usuario;

  @NotNull(message = "o Atributo Senha é Obrigatório!")
  @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
  private String senha;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties("usuario")
  private List<PostagemModel> postagem;

  public UsuarioModel(long id, String nome, String foto, String tipo, String usuario, String senha) {
    this.id = id;
    this.nome = nome;
    this.foto = foto;
    this.tipo = tipo;
    this.usuario = usuario;
    this.senha = senha;
  }

  public UsuarioModel() {
  }

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

  public List<PostagemModel> getPostagem() {
    return this.postagem;
  }

  public void setPostagem(List<PostagemModel> postagem) {
    this.postagem = postagem;
  }

}
