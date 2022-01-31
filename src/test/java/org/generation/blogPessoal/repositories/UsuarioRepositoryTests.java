package org.generation.blogPessoal.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.models.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositoryTests {

	@Autowired
	private UsuarioRepository repository;

	@BeforeAll
	void start() {
		repository.save(new Usuario("Thiago Batista", "thiago@email.com", "123456"));
		repository.save(new Usuario("Bruno Batista", "bruno@email.com", "123456"));
		repository.save(new Usuario("Cat Batista", "cat@email.com", "123456"));
	}

	@Test
	@DisplayName("Retorna 1 Usuario")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = repository.findByUsuario("thiago@email.com");
		assertTrue(usuario.get().getUsuario().equals("thiago@email.com"));

	}

	@Test
	@DisplayName("Retorna 3 Usuarios")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuario = repository.findAllByNomeContainingIgnoreCase("Batista");
		assertEquals(3, listaDeUsuario.size());
		assertTrue(listaDeUsuario.get(0).getNome().equals("Thiago Batista"));
		assertTrue(listaDeUsuario.get(1).getNome().equals("Bruno Batista"));
		assertTrue(listaDeUsuario.get(2).getNome().equals("Cat Batista"));
	}
}
