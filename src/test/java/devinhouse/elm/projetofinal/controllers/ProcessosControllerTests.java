package devinhouse.elm.projetofinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
//import org.springframework.context.annotation.Import;

import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import devinhouse.elm.projetofinal.controllers.ProcessosController;
import devinhouse.elm.projetofinal.controllers.GeneralControllerAdvice;
import devinhouse.elm.projetofinal.services.ProcessosService;
import devinhouse.elm.projetofinal.model.Processo;

import devinhouse.elm.projetofinal.exceptions.*;

import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;


@WebMvcTest(ProcessosController.class)
public class ProcessosControllerTests {

    private static WebTestClient webClient;

    @BeforeAll
    public static void setupWebClient(@Autowired MockMvc mvc) {
	// @AutoConfigureWebTestClient is WebFlux exclusive atm...
	webClient = MockMvcWebTestClient.bindTo(mvc).build();
    }


    @MockBean private ProcessosService service;

    @AfterEach
    public void resetMocks() {
	reset(controller);
	reset(service);
    }


    @Test
    public void post() {

	var processoEsperado = new Processo();
	when(service.cadastrar(any())).thenReturn(processoEsperado);

	var resposta = webClient.post()
	    .uri("/processos")
	    .contentType(APPLICATION_JSON)
	    .bodyValue(processoEsperado)
	    .exchange();

	resposta.expectStatus().isCreated();
	resposta.expectHeader().contentType(APPLICATION_JSON);
	resposta.expectBody(Processo.class).isEqualTo(processoEsperado);
    }

    @Test
    public void get() {

	var listaEsperada = List.of(new Processo());
	when(service.listarTodos()).thenReturn(listaEsperada);

	var resposta = webClient.get()
	    .uri("/processos")
	    .exchange();

	resposta
	    .expectStatus().isOk()
	    .expectHeader().contentType(APPLICATION_JSON)
	    .expectBody(new ParameterizedTypeReference<List<Processo>>() {}).isEqualTo(listaEsperada);
    }

    @Test
    public void getPorId() {

	var id = 1;
	var processoEsperado = new Processo();
	when(service.buscarPorId(id)).thenReturn(Optional.of(processoEsperado));

	var resposta = webClient.get()
	    .uri("/processos/" + id)
	    .exchange();

	resposta
	    .expectStatus().isOk()
	    .expectHeader().contentType(APPLICATION_JSON)
	    .expectBody(Processo.class).isEqualTo(processoEsperado);
    }

    @Test
    public void getPorIdInexistenteFalha() {

	var id = 1;
	when(service.buscarPorId(id)).thenReturn(Optional.empty());

	var resposta = webClient.get()
	     .uri("/processos/" + id)
	     .exchange();

	resposta.expectStatus().isNotFound();
    }
}
