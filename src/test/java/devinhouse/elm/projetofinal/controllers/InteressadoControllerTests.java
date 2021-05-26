package devinhouse.elm.projetofinal.controllers;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import devinhouse.elm.projetofinal.model.Interessado;
import devinhouse.elm.projetofinal.services.InteressadoService;

@WebMvcTest(InteressadoController.class)
public class InteressadoControllerTests {

    private static WebTestClient webClient;

    @MockBean
    private InteressadoService service;

    @BeforeAll
    public static void configuration(@Autowired MockMvc mvc) {
        webClient = MockMvcWebTestClient.bindTo(mvc).build();
    }

    @Test
    public void post() {

        var interessado = new Interessado();
        when(service.cadastrar(interessado)).thenReturn(interessado);

        webClient.post().uri("/interessado").contentType(MediaType.APPLICATION_JSON).bodyValue(interessado).exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON).expectStatus().isCreated()
                .expectBody(Interessado.class).isEqualTo(interessado);
    }

    // @Test // TODO: não estou conseguindo fazer rodar esse teste, se acharem
    // interessante, podem me dar uma manzinha, se não deixo ele de lado.
    // public void deveDarFalhaAoCadastrarComIdentificacaoExistente() {
    // var interessado = new Interessado();
    // when(service.cadastrar(interessado)).thenThrow(IdentificacaoJaExisteException.class);

    // webClient.post().uri("/interessado").contentType(MediaType.APPLICATION_JSON).bodyValue(interessado).exchange()
    // .expectStatus().isBadRequest();
    // }

    @Test
    public void pegarPorId() {

        var id = 1L;
        var interessadoEsperado = new Interessado();
        when(service.obterPorId(id)).thenReturn(Optional.of(interessadoEsperado));

        webClient.get().uri("/interessado/" + id).exchange().expectStatus().isOk().expectBody(Interessado.class)
                .isEqualTo(interessadoEsperado);
    }

    @Test
    public void deveDarFalhaAoPegarPorIdQueNaoExiste() {

        var id = 1L;
        when(service.obterPorId(id)).thenReturn(Optional.empty());

        webClient.get().uri("/interessado/" + id).exchange().expectStatus().isNotFound();
    }

    @Test
    public void pegarPorIdentificacao() {

        var identificacao = "254.258.960-60";
        var interessadoEsperado = new Interessado();
        when(service.obterPorIdentificacao(identificacao)).thenReturn(Optional.of(interessadoEsperado));

        webClient.get().uri("/interessado/identificacao?identificacao=" + identificacao).exchange().expectStatus()
                .isOk().expectBody(Interessado.class).isEqualTo(interessadoEsperado);
    }

    @Test
    public void deveDarFalhaAoPegarPorIdentificacaoQueNaoExiste() {

        var identificacao = "254.258.960-60";
        when(service.obterPorIdentificacao(identificacao)).thenReturn(Optional.empty());

        webClient.get().uri("/interessado/identificacao?identificacao=" + identificacao).exchange().expectStatus()
                .isNotFound();
    }
}
