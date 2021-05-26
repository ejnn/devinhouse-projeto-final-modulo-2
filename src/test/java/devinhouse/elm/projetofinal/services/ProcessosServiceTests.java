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

import devinhouse.elm.projetofinal.services.ProcessosService;
import devinhouse.elm.projetofinal.repositories.ProcessosRepository;
import devinhouse.elm.projetofinal.model.Processo;

import devinhouse.elm.projetofinal.exceptions.*;

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ProcessosServiceTests {

    @InjectMocks private ProcessosService service;
    @Mock private ProcessosRepository repository;


    @Test
    public void cadastrar() {

	var processo = new Processo();

	service.cadastrar(processo);

	verify(repository).save(processo);
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
    public void listarTodos() {

	var listaEsperada = List.of(new Processo());
	when(repository.findAll()).thenReturn(listaEsperada);

	var listaRecebida = service.listarTodos();

	assertEquals(listaEsperada, listaRecebida);
    }
}
