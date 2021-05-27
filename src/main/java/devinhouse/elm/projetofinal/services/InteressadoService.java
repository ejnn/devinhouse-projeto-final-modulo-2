package devinhouse.elm.projetofinal.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import devinhouse.elm.projetofinal.exceptions.IdJaExisteException;
import devinhouse.elm.projetofinal.exceptions.IdentificacaoInvalidaException;
import devinhouse.elm.projetofinal.exceptions.IdentificacaoJaExisteException;
import devinhouse.elm.projetofinal.model.Interessado;
import devinhouse.elm.projetofinal.repositories.InteressadoRepository;
import devinhouse.elm.projetofinal.validator.IdentificacaoValidator;

@Service
public class InteressadoService {

    private final InteressadoRepository repository;
    private final IdentificacaoValidator validator;

    public InteressadoService(InteressadoRepository repository, IdentificacaoValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Interessado cadastrar(Interessado interessado) throws IdJaExisteException, IdentificacaoJaExisteException {
        if (repository.existsById(interessado.getId()))
            throw new IdJaExisteException();

        if (repository.existsByIdentificacao(interessado.getIdentificacao()))
            throw new IdentificacaoJaExisteException();

        if (validator.isValid(interessado.getIdentificacao()) == false)
            throw new IdentificacaoInvalidaException();

        return repository.save(interessado);
    }

    public Optional<Interessado> obterPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Interessado> obterPorIdentificacao(String identificacao) {
        return repository.findByIdentificacao(identificacao);
    }
}
