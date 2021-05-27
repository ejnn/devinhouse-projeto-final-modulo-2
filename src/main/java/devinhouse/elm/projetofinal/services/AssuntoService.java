package devinhouse.elm.projetofinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import devinhouse.elm.projetofinal.repositories.AssuntoRepository;
import devinhouse.elm.projetofinal.model.Assunto;


@Service
public class AssuntoService {

    private final AssuntoRepository repository;

    public AssuntoService(AssuntoRepository repository) {
        this.repository = repository;
    }


    public Assunto cadastrar(Assunto assunto) {
        return repository.save(assunto);
    }

    public Optional<Assunto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Assunto> buscarTodos() {
        return repository.findAll();
    }

}
