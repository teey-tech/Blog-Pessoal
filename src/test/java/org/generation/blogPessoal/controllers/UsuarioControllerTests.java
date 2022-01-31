package org.generation.blogPessoal.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.generation.blogPessoal.models.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControllerTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuarioService usuarioService;

	@Test
	@Order(1)
	@DisplayName("Cadastrar um Usuario")
	public void deveCriarUmUsuario() {

		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(
				new Usuario("Thiago Batista", "thiago@teste.com", "123456"));

		ResponseEntity<Usuario> resposta = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
		assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
	}

	@Test
	@Order(2)
	@DisplayName("Negar duplicação de usuario")
	public void naoDeveDuplicarUsuario() {

		usuarioService.CadastrarUsuario(new Usuario("Thiago Batista", "thiago@teste.com", "1345677"));

		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(
				new Usuario("Thiago Batista", "thiago@teste.com", "1345677"));

		ResponseEntity<Usuario> resposta = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);

		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
	}

	@Test
	@Order(3)
	@DisplayName("Mostra todos usuarios")
	public void deveMostrarTodosUsuarios() {

		usuarioService.CadastrarUsuario(new Usuario("Thiago Batista", "thiago@teste.com", "123456"));
		usuarioService.CadastrarUsuario(new Usuario("Bruno Batista", "bruno@teste.com", "123456"));

		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("thiago@teste.com", "123456")
				.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
}
