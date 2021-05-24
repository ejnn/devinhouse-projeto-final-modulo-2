package devinhouse.elm.projetofinal.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import devinhouse.elm.projetofinal.services.AssuntoService;
import devinhouse.elm.projetofinal.model.Assunto;

import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AssuntoControllerTests {

	private static WebTestClient webClient;

	@MockBean private AssuntoService service;
	@Autowired private AssuntoController controller;

	@BeforeAll
	public static void configuration(@Autowired MockMvc mvc) {
		webClient = MockMvcWebTestClient.bindTo(mvc).build();
	}


	@Test
	public void deveRetornarAccepted_QuandoRequisitarGetEmAssuntos() throws Exception {
		mvc.perform(get("/assuntos")).andExpect(status().isAccepted());
	}

	@Test
	public void deveRetornarNotFound_QuandoRequisitarGetPorIdEmAssuntos() throws Exception {
		mvc.perform(get("/assuntos/1")).andExpect(status().isNotFound());
	}

	@Test
	public void deveRetornarUnsupportedMediaType_QuandoRequisitarPostEmAssuntos() throws Exception {
		mvc.perform(post("/assuntos")).andExpect(status().isUnsupportedMediaType());
	}

	@Test
	public void deveRetornarNotFound_QuandoRequisitarPutEmAssuntosParaInvalidar() throws Exception {
		mvc.perform(put("/assuntos/inativar/1")).andExpect(status().isNotFound());
	}

	@Test
	public void deveRetornarSucesso_QuandoRequisitarPostDeUmNovoAssunto() {

		var assunto = mock(Assunto.class);
		when(service.cadastrar(assunto)).thenReturn(assunto);

		controller.post(assunto);

		assertDoesNotThrow(() -> verify(service).cadastrar(assunto));
		verify(service, times(1)).cadastrar(any());
	}

	@Test
	public void getPorId() {

		var id = 1L;
		var assuntoEsperado = Optional.of(mock(Assunto.class));
		when(service.buscarPorId(id)).thenReturn(assuntoEsperado);

		webClient.get().uri("/assuntos/" + id).exchange();
		
		verify(service).buscarPorId(id);
	}

	@Test
	public void deveRetornarSucesso_QuandoRequisitarGetDeAssunto() {

		List<Assunto> lista = new ArrayList<Assunto>();
		when(service.buscarTodos()).thenReturn(lista);

		controller.get();

		assertDoesNotThrow(() -> verify(service).buscarTodos());
		verify(service, times(1)).buscarTodos();
	}
}
