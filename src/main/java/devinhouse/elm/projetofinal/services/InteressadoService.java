package devinhouse.elm.projetofinal.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import devinhouse.elm.projetofinal.exceptions.IdJaExisteException;
import devinhouse.elm.projetofinal.exceptions.IdentificacaoJaExisteException;
import devinhouse.elm.projetofinal.model.Interessado;
import devinhouse.elm.projetofinal.repositories.InteressadoRepository;

@Service
public class InteressadoService {

    private final InteressadoRepository repository;

    public InteressadoService(InteressadoRepository repository) {
        this.repository = repository;
    }

    public Interessado cadastrar(Interessado interessado) throws IdJaExisteException, IdentificacaoJaExisteException {
        if (repository.existsById(interessado.getId()))
            throw new IdJaExisteException();

        if (repository.existsByIdentificacao(interessado.getIdentificacao()))
            throw new IdentificacaoJaExisteException();

        return repository.save(interessado);
    }

    public Optional<Interessado> obterPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Interessado> obterPorIdentificacao(String identificacao) {
        return repository.findByIdentificacao(identificacao);
    }
}
