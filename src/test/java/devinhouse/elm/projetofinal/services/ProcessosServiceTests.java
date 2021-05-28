package devinhouse.elm.projetofinal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;

import javax.persistence.EntityManager;
import devinhouse.elm.projetofinal.services.ProcessosService;
import devinhouse.elm.projetofinal.repositories.ProcessosRepository;
import devinhouse.elm.projetofinal.model.Processo;
import devinhouse.elm.projetofinal.model.Assunto;
import devinhouse.elm.projetofinal.model.Interessado;

import devinhouse.elm.projetofinal.exceptions.*;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ProcessosServiceTests {

    @InjectMocks private ProcessosService service;
    @Mock private ProcessosRepository repository;
    @Mock private EntityManager _entityManager;


    @Test
    public void cadastrar() {

	var processo = new Processo();
	var processoEsperado = new Processo();
	processoEsperado.setAssunto(new Assunto());

	when(repository.save(processo)).thenReturn(processoEsperado);

	var processoRecebido = service.cadastrar(processo);

	verify(repository).save(processo);
	assertEquals(processoEsperado, processoRecebido);
    }

    @Test
    public void cadastrarComIdExistenteFalha() {

	when(repository.existsById(any())).thenReturn(true);

	var processo = new Processo();

	assertThrows(IdJaExisteException.class, () -> service.cadastrar(processo));
    }

    @Test
    public void cadastrarComChaveExistenteFalha() {

	when(repository.existsByChave(any())).thenReturn(true);

	var processo = new Processo();

	assertThrows(ChaveJaExisteException.class, () -> service.cadastrar(processo));
    }

    @Test
    public void cadastrarComAssuntoInativoFalha() {

	var processo = new Processo();
	var assunto = new Assunto();
	assunto.setAtivo(false);
	processo.setAssunto(assunto);
	when(repository.save(processo)).thenReturn(processo);

	assertThrows(AssuntoInativoException.class, () -> service.cadastrar(processo));
    }

    @Test
    public void cadastrarComInteressadoInativoFalha() {

	var processo = new Processo();
	var interessado = new Interessado();
	interessado.setAtivo(false);
	processo.setInteressado(interessado);
	when(repository.save(processo)).thenReturn(processo);

	assertThrows(InteressadoInativoException.class, () -> service.cadastrar(processo));
    }

    @Test
    public void listarTodos() {

	var listaEsperada = List.of(new Processo());
	when(repository.findAll()).thenReturn(listaEsperada);

	var listaRecebida = service.listarTodos();

	assertEquals(listaEsperada, listaRecebida);
    }

    @Test
    public void buscarPorId() {

	var processoEsperado = Optional.of(new Processo());
	var id = 1;
	when(repository.findById(id)).thenReturn(processoEsperado);

	var processoRecebido = service.buscarPorId(id);

	assertEquals(processoEsperado, processoRecebido);
    }

    @Test
    public void buscarPorChave() {

	var chave = "AAA";

	service.buscarPorChave(chave);

	verify(repository).findByChaveContains(chave);
    }

    @Test
    public void buscarPorAssuntoId() {

	var assuntoId = 1l;
	var listaEsperada = List.of(new Processo());
	when(repository.findByAssuntoId(assuntoId)).thenReturn(listaEsperada);

	var listaRecebida = service.buscarPorAssuntoId(assuntoId);

	assertEquals(listaEsperada, listaRecebida);
    }
}
