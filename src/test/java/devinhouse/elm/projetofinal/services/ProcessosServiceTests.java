package devinhouse.elm.projetofinal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import devinhouse.elm.projetofinal.services.ProcessosService;
import devinhouse.elm.projetofinal.repositories.ProcessosRepository;
import devinhouse.elm.projetofinal.model.Processo;

import devinhouse.elm.projetofinal.exceptions.*;


@SpringBootTest
public class ProcessosServiceTests {

    @Configuration
    static class Config {

	@Bean
	public ProcessosService processosService() {
	    return new ProcessosService(processosRepository());
	}

	@Bean ProcessosRepository processosRepository() {
	    return mock(ProcessosRepository.class);
	}
    }

    @Autowired private ProcessosService service;
    @Autowired private ProcessosRepository repository;

    @AfterEach
    public void resetMocks() {
	reset(repository);
    }


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
}
