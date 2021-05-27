package devinhouse.elm.projetofinal.services;

import org.springframework.stereotype.Service;

import devinhouse.elm.projetofinal.repositories.ProcessosRepository;
import devinhouse.elm.projetofinal.model.Processo;

import devinhouse.elm.projetofinal.exceptions.*;

import java.time.Year;
import java.util.List;
import java.util.Optional;


@Service
public class ProcessosService {

    private ProcessosRepository repository;

    public ProcessosService(ProcessosRepository repository) {
	this.repository = repository;
    }


    public Processo cadastrar(Processo processo) {

	if (repository.existsById(processo.getId())) throw new IdJaExisteException();

	var sigla = processo.getSigla();
	var numero = repository.countBySiglaEquals(sigla) + 1;
	var ano = Year.now();
	var chave = sigla + " " + numero + "/" + ano;

	if (repository.existsByChave(chave)) throw new ChaveJaExisteException();

	processo.setNumero(numero);
	processo.setAno(ano);
	processo.setChave(chave);

	return repository.save(processo);
    }

    public List<Processo> listarTodos() {
	return repository.findAll();
    }

    public Optional<Processo> buscarPorId(int id) {
	return repository.findById(id);
    }

    public List<Processo> buscarPorChave(String chave) {
	return repository.findByChaveContains(chave);
    }
}
