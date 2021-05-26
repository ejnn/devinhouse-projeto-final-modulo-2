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


@WebMvcTest(ProcessosController.class)
public class ProcessosControllerTests {

    private static WebTestClient webClient;

    // @Autowired private MockMvc mvc;
    @MockBean private ProcessosService service;


    @BeforeAll
    public static void setupWebClient(@Autowired MockMvc mvc) {
	// @AutoConfigureWebTestClient is WebFlux exclusive atm...
	webClient = MockMvcWebTestClient.bindTo(mvc).build();
    }

    @AfterEach
    public void resetMocks() {
	reset(service);
    }


    @Test
    public void post() {

	var processoEsperado = new Processo();
	when(service.cadastrar(any())).thenReturn(processoEsperado);

	var response = webClient.post()
	    .uri("/processos")
	    .contentType(APPLICATION_JSON)
	    .bodyValue(processoEsperado)
	    .exchange();

	response.expectBody(Processo.class).isEqualTo(processoEsperado);
	response.expectStatus().isCreated();
    }
}
