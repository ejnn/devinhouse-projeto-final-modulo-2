package devinhouse.elm.projetofinal.services;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import devinhouse.elm.projetofinal.repositories.ProcessosRepository;
import devinhouse.elm.projetofinal.model.Processo;

import devinhouse.elm.projetofinal.exceptions.*;

import javax.persistence.EntityManager;
import java.time.Year;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProcessosService {

    private ProcessosRepository repository;
    private EntityManager entityManager;

    public ProcessosService(ProcessosRepository repository,
			    EntityManager entityManager) {
	this.repository = repository;
	this.entityManager = entityManager;
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

	processo = repository.save(processo);
	entityManager.refresh(processo);

	var assunto = processo.getAssunto();
	if (assunto != null) {
	    if (!assunto.isAtivo()) throw new AssuntoInativoException();
	}

	var interessado = processo.getInteressado();
	if (interessado != null) {
	    if (!interessado.isAtivo()) throw new InteressadoInativoException();
	}

	return processo;
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

    public List<Processo> buscarPorAssuntoId(long id) {
	return repository.findByAssuntoId(id);
    }
}
