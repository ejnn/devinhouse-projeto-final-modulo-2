package devinhouse.elm.projetofinal.controllers;

import java.util.ArrayList;
import java.util.Optional;

import devinhouse.elm.projetofinal.services.AssuntoService;
import devinhouse.elm.projetofinal.model.Assunto;

import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc
public class AssuntoControllerTests {

	private static WebTestClient webClient;

	@MockBean
	private AssuntoService service;

	@BeforeAll
	public static void configuration(@Autowired MockMvc mvc) {
		webClient = MockMvcWebTestClient.bindTo(mvc).build();
	}

	@Test
	public void post() {

		var assunto = new Assunto();
		when(service.cadastrar(assunto)).thenReturn(assunto);

		webClient.post().uri("/assuntos").contentType(APPLICATION_JSON).bodyValue(assunto).exchange().expectHeader()
				.contentType(APPLICATION_JSON).expectBody(Assunto.class).isEqualTo(assunto);

	}

	@Test
	public void get() {

		var listaEsperada = new ArrayList<Assunto>();
		when(service.buscarTodos()).thenReturn(listaEsperada);

		webClient.get().uri("/assuntos").exchange().expectBodyList(Assunto.class).isEqualTo(listaEsperada);
	}

	@Test
	public void getPorId() {

		var id = 1L;
		var assuntoEsperado = new Assunto();
		when(service.buscarPorId(id)).thenReturn(Optional.of(assuntoEsperado));

		webClient.get().uri("/assuntos/" + id).exchange().expectBody(Assunto.class).isEqualTo(assuntoEsperado);
	}

	@Test
	public void postReturnCREATED() {
		var assunto = new Assunto();

		webClient.post().uri("/assuntos").bodyValue(assunto).exchange().expectStatus().isCreated();
	}

	@Test
	public void getReturnACCEPTED() {

		webClient.get().uri("/assuntos").exchange().expectStatus().isAccepted();
	}

	@Test
	public void getPorIdReturnACCEPTED() {

		var id = 1L;
		var assunto = new Assunto();
		when(service.buscarPorId(id)).thenReturn(Optional.of(assunto));

		webClient.get().uri("/assuntos/" + id).exchange().expectStatus().isAccepted();
	}
}