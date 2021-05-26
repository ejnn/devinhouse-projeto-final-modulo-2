package devinhouse.elm.projetofinal.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Test
    public void obterPorId() {
        var interessadoEsperado = mock(Interessado.class);
        when(repository.findById(interessadoEsperado.getId())).thenReturn(Optional.of(interessadoEsperado));

        var interessadoRetorno = service.obterPorId(interessadoEsperado.getId());

        assertSame(interessadoEsperado, interessadoRetorno.get());
    }

    @Test
    public void deveDarFalhaAoObterPorIdQueNaoExiste() {
        var interessado = mock(Interessado.class);
        when(repository.findById(interessado.getId())).thenReturn(Optional.empty());

        var interessadoRetorno = service.obterPorId(interessado.getId());

        assertThrows(NoSuchElementException.class, () -> interessadoRetorno.get());
    }

    @Test
    public void obterPorIdentificacao() {
        var interessadoEsperado = mock(Interessado.class);
        when(repository.findByIdentificacao(interessadoEsperado.getIdentificacao()))
                .thenReturn(Optional.of(interessadoEsperado));

        var interessadoRetorno = service.obterPorIdentificacao(interessadoEsperado.getIdentificacao());

        assertSame(interessadoEsperado, interessadoRetorno.get());
    }

    @Test
    public void deveDarFalhaAoObterPorIdentificacaoQueNaoExiste() {
        var interessadoEsperado = mock(Interessado.class);
        when(repository.findByIdentificacao(interessadoEsperado.getIdentificacao())).thenReturn(Optional.empty());

        var interessadoRetorno = service.obterPorIdentificacao(interessadoEsperado.getIdentificacao());

        assertThrows(NoSuchElementException.class, () -> interessadoRetorno.get());
    }
}
