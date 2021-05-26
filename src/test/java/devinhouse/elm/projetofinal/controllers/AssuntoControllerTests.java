package devinhouse.elm.projetofinal.controllers;

import java.util.ArrayList;
import java.util.Optional;

import devinhouse.elm.projetofinal.services.AssuntoService;
import devinhouse.elm.projetofinal.model.Assunto;

import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@WebMvcTest(AssuntoController.class)
public class AssuntoControllerTests {

	@SpyBean AssuntoController controller;
	private static WebTestClient webClient;

    @BeforeAll
    public static void setupWebClient(@Autowired MockMvc mvc) {
    // @AutoConfigureWebTestClient is WebFlux exclusive atm...
    webClient = MockMvcWebTestClient.bindTo(mvc).build();
    }

    @MockBean private AssuntoService service;

    @AfterEach
    public void resetMocks() {
    	reset(service);
    }


	@Test
	public void post(){

		var assunto = new Assunto();
		when(service.cadastrar(assunto)).thenReturn(assunto);

		webClient.post()
		.uri("/assuntos")
		.contentType(APPLICATION_JSON)
		.bodyValue(assunto)
		.exchange()
		.expectHeader().contentType(APPLICATION_JSON)
		.expectBody(Assunto.class)
		.isEqualTo(assunto);
		
	}

	@Test
	public void postReturnBAD_REQUEST(){

		var assunto = new Assunto();
		when(controller.post(assunto)).thenThrow(PropertyValueException.class);

		webClient.post()
		.uri("/assuntos")
		.contentType(APPLICATION_JSON)
		.bodyValue(assunto)
		.exchange()
		.expectStatus().isBadRequest();
	}

	@Test
	public void get(){

		var listaEsperada = new ArrayList<Assunto>();
		when(service.buscarTodos()).thenReturn(listaEsperada);

		webClient.get()
		.uri("/assuntos")
		.exchange()
		.expectBodyList(Assunto.class)
		.isEqualTo(listaEsperada);
	}

	@Test
	public void getPorId() {

		var id = 1L;
		var assuntoEsperado = new Assunto();
		when(service.buscarPorId(id)).thenReturn(Optional.of(assuntoEsperado));

		webClient.get()
		    .uri("/assuntos/" + id)
		    .exchange()
		    .expectBody(Assunto.class)
            .isEqualTo(assuntoEsperado);
	}

	@Test
	public void postReturnCREATED(){
		var assunto = new Assunto();

		webClient.post()
		.uri("/assuntos")
		.bodyValue(assunto)
		.exchange()
		.expectStatus().isCreated();
	}

	@Test
	public void getReturnACCEPTED(){

		webClient.get()
		.uri("/assuntos")
		.exchange()
		.expectStatus().isAccepted();
	}

	@Test
	public void getPorIdReturnOK(){

		var id = 1L;
		var assunto = new Assunto();
		when(service.buscarPorId(id)).thenReturn(Optional.of(assunto));

		webClient.get()
		.uri("/assuntos/" + id)
		.exchange()
		.expectStatus().isOk();
	}
}