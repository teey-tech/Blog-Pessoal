package org.generation.blogPessoal.seguranca;

import java.util.Collection;
import java.util.List;

import org.generation.blogPessoal.models.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Class responsible for charge user details in security spring
 * 
 * @author Thiago Batista
 * @since 06/02/2022
 * @version 1.0
 * @see UserDetails
 * @see UsuarioModel
 * @see UserDetailsImplements
 * @see UserDetailsServiceImplement
 */
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1l;

  private String userName;
  private String password;
  private List<GrantedAuthority> authorities;

  public UserDetailsImpl(UsuarioModel user) {
    this.userName = user.getUsuario();
    this.password = user.getSenha();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
