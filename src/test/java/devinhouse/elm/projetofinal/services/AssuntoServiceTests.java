package devinhouse.elm.projetofinal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import devinhouse.elm.projetofinal.model.Assunto;
import devinhouse.elm.projetofinal.repositories.AssuntoRepository;


@ExtendWith(MockitoExtension.class)
public class AssuntoServiceTests {

	@Mock private AssuntoRepository repository;
	@InjectMocks private AssuntoService service;


	@Test
    public void cadastrar() {

		var assunto = new Assunto();

		service.cadastrar(assunto);

		verify(repository).save(any(Assunto.class));
    }

	@Test
	public void buscarPorId(){

		var id = 1L;
		var assuntoEsperado = Optional.of(new Assunto());
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
