package devinhouse.elm.projetofinal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import devinhouse.elm.projetofinal.exceptions.IdJaExisteException;
import devinhouse.elm.projetofinal.model.Assunto;
import devinhouse.elm.projetofinal.repositories.AssuntoRepository;


@SpringBootTest
public class AssuntoServiceTests {

	@MockBean private AssuntoRepository repository;

	@Autowired private AssuntoService service;


	@Test
    public void cadastrar() {

		var assunto = mock(Assunto.class);

		service.cadastrar(assunto);

		verify(repository).save(any(Assunto.class));
    }

	@Test
    public void cadastrarFalhaComIdExistente() {

		var assunto = mock(Assunto.class);
		when(repository.existsById(any())).thenReturn(true);

		assertThrows(IdJaExisteException.class, () -> service.cadastrar(assunto));
    }

	@Test
	public void buscarPorId(){

		var id = 1L;
		var assuntoEsperado = Optional.of(mock(Assunto.class));
		when(repository.findById(any())).thenReturn(assuntoEsperado);

		var assuntoRecebido = service.buscarPorId(id);
		
		assertEquals(assuntoEsperado, assuntoRecebido);
	}	

	@Test
	public void buscarTodos(){

		List<Assunto> listaEsperada = new ArrayList<Assunto>();
		when(repository.findAll()).thenReturn(listaEsperada);

		var listaRecebida = service.buscarTodos();

		assertEquals(listaEsperada, listaRecebida);
	}	

}
