package devinhouse.elm.projetofinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import devinhouse.elm.projetofinal.controllers.ProcessosController;
import devinhouse.elm.projetofinal.services.ProcessosService;
import devinhouse.elm.projetofinal.model.Processo;

import devinhouse.elm.projetofinal.exceptions.*;

import org.springframework.core.ParameterizedTypeReference;

import java.util.List;


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

	resposta.expectStatus().isOk();
	resposta.expectHeader().contentType(APPLICATION_JSON);
	resposta.expectBody(new ParameterizedTypeReference<List<Processo>>() {} ).isEqualTo(listaEsperada);
    }
}
