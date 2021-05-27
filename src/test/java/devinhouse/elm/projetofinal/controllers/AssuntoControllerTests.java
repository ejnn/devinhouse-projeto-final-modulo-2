package devinhouse.elm.projetofinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import devinhouse.elm.projetofinal.config.GeneralConfiguration;

import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.hibernate.PropertyValueException;

import devinhouse.elm.projetofinal.services.AssuntoService;
import devinhouse.elm.projetofinal.model.Assunto;
import devinhouse.elm.projetofinal.dtos.AssuntoCadastroDto;

import java.util.ArrayList;
import java.util.Optional;


@WebMvcTest(AssuntoController.class)
@Import(GeneralConfiguration.class)
public class AssuntoControllerTests {

	private static WebTestClient webClient;
	
	@BeforeAll
    public static void setupWebClient(@Autowired MockMvc mvc) {
		// @AutoConfigureWebTestClient is WebFlux exclusive atm...
		webClient = MockMvcWebTestClient.bindTo(mvc).build();
    }
	
    @MockBean private AssuntoService service;
	@SpyBean private AssuntoController controller;
	
    @AfterEach
    public void resetMocks() {
    	reset(service);
		reset(controller);
    }


    @Test
    public void post(){

	var assuntoEsperado = new Assunto();
	when(service.cadastrar(any())).thenReturn(assuntoEsperado);

	var resposta = webClient.post()
	    .uri("/assuntos")
	    .contentType(APPLICATION_JSON)
	    .bodyValue(new AssuntoCadastroDto())
	    .exchange();

	resposta
	    .expectHeader().contentType(APPLICATION_JSON)
	    .expectStatus().isCreated()
	    .expectBody(Assunto.class).isEqualTo(assuntoEsperado);
		
    }

	@Test
	public void controllerTrataViolacaoDeRestricoes(){

	    var assunto = new AssuntoCadastroDto();
	    when(controller.post(assunto)).thenThrow(PropertyValueException.class);

	    var resposta = webClient.post()
		.uri("/assuntos")
		.contentType(APPLICATION_JSON)
		.bodyValue(assunto)
		.exchange();

	    resposta.expectStatus().isBadRequest();
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
