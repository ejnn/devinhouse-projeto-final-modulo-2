package devinhouse.elm.projetofinal.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import devinhouse.elm.projetofinal.exceptions.IdJaExisteException;
import devinhouse.elm.projetofinal.exceptions.IdentificacaoJaExisteException;
import devinhouse.elm.projetofinal.model.Interessado;
import devinhouse.elm.projetofinal.repositories.InteressadoRepository;

@SpringBootTest
public class InteressadoServiceTests {

    @MockBean
    private InteressadoRepository repository;

    @Autowired
    private InteressadoService service;

    @Test
    public void cadastrar() {
        var interessado = mock(Interessado.class);

        service.cadastrar(interessado);

        verify(repository).existsById(interessado.getId());
        verify(repository).existsByIdentificacao(interessado.getIdentificacao());
        verify(repository).save(interessado);
    }

    @Test
    public void deveDarFalhaAoCadastrarComIdExistente() {
        var interessado = mock(Interessado.class);
        when(repository.existsById(interessado.getId())).thenReturn(true);

        assertThrows(IdJaExisteException.class, () -> service.cadastrar(interessado));
    }

    @Test
    public void deveDarFalhaAoCadastrarComIdentificacaoExistente() {
        var interessado = mock(Interessado.class);
        when(repository.existsByIdentificacao(interessado.getIdentificacao())).thenReturn(true);

        assertThrows(IdentificacaoJaExisteException.class, () -> service.cadastrar(interessado));
    }
}
