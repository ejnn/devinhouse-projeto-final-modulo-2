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
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import devinhouse.elm.projetofinal.controllers.ProcessosController;
import devinhouse.elm.projetofinal.controllers.GeneralControllerAdvice;
import devinhouse.elm.projetofinal.services.ProcessosService;
import devinhouse.elm.projetofinal.model.Processo;
import devinhouse.elm.projetofinal.model.Assunto;

import devinhouse.elm.projetofinal.exceptions.*;

import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;


@WebMvcTest(ProcessosController.class)
@Import(GeneralConfiguration.class)
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
	    .bodyValue(processoEsperado)
	    .exchange();

	resposta.expectStatus().isCreated();
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

    @Test
    public void getPorChave() {

	var chave = "AAA";
	var listaEsperada = List.of(new Processo());
	when(service.buscarPorChave(chave)).thenReturn(listaEsperada);

	var resposta = webClient.get()
	    .uri("/processos?chave=" + chave)
	    .exchange();

	resposta
	    .expectStatus().isOk()
	    .expectBody(new ParameterizedTypeReference<List<Processo>>() {}).isEqualTo(listaEsperada);
	    
    }

    @Test
    public void getPorAssuntoId() {

    	var assuntoId = 1l;
    	var listaEsperada = List.of(new Processo());
    	when(service.buscarPorAssuntoId(assuntoId)).thenReturn(listaEsperada);

    	var resposta = webClient.get()
    	    .uri("/processos?assuntoId=" + assuntoId)
    	    .exchange();

    	resposta
    	    .expectStatus().isOk()
    	    .expectBody(new ParameterizedTypeReference<List<Processo>>() {}).isEqualTo(listaEsperada);
    }

    @Test
    public void getPorInteressadoId() {

    	var interessadoId = 1l;
    	var listaEsperada = List.of(new Processo());
    	when(service.buscarPorInteressadoId(interessadoId)).thenReturn(listaEsperada);

    	var resposta = webClient.get()
    	    .uri("/processos?interessadoId=" + interessadoId)
    	    .exchange();

    	resposta
    	    .expectStatus().isOk()
    	    .expectBody(new ParameterizedTypeReference<List<Processo>>() {}).isEqualTo(listaEsperada);
    }

    @Test
    public void deletePorId() {

	var processoId = 1;

	var resposta = webClient.delete()
	    .uri("/processos/" + processoId)
	    .exchange();

	resposta.expectStatus().isNoContent();
    }

    @Test
    public void putPorId() {

	var processoId = 1;
	var processo = new Processo();

	var resposta = webClient.put()
	    .uri("/processos/" + processoId)
	    .bodyValue(processo)
	    .exchange();

	resposta.expectStatus().isOk();
    }
}
