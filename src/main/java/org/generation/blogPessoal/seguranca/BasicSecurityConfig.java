package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class of configure security spring boot
 * 
 * @author Thiago Batista
 * @since 06/02/2022
 * @version 1.0
 * @see WebSecurityConfigurerAdapter
 * @see EnableWebSecurity
 * @see PasswordEncoder
 * @see BCryptPasswordEncoder
 * @see SessionCreationPolicy
 * @see HttpSecurity
 * @see AuthenticationManagerBuilder
 * @see HttpMethod
 * @see ResponseStatusException
 * @see HttpStatus
 * @see UsuarioService
 */
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder PasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);

    auth.inMemoryAuthentication()
        .withUser("boaz")
        .password(PasswordEncoder().encode("boaz"))
        .authorities("ROLE_USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/usuarios/cadastrar").permitAll()
        .antMatchers(HttpMethod.POST, "/usuarios/logar").permitAll()
        .anyRequest().authenticated()
        .and().httpBasic()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().cors()
        .and().csrf().disable();
  }
}
