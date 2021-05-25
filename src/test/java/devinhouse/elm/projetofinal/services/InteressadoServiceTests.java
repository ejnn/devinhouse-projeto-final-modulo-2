package devinhouse.elm.projetofinal.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import devinhouse.elm.projetofinal.exceptions.IdJaExisteException;
import devinhouse.elm.projetofinal.model.Interessado;
import devinhouse.elm.projetofinal.repositories.InteressadoRepository;

@SpringBootTest
public class InteressadoServiceTests {

    @MockBean
    private InteressadoRepository repository;

    @Autowired
    private InteressadoService service;

    @Test
    public void deveDarFalhaAoCadastrarComIdExistente() {
        var interessado = mock(Interessado.class);
        when(repository.existsById(any())).thenReturn(true);

        assertThrows(IdJaExisteException.class, () -> service.cadastrar(interessado));
    }

}
