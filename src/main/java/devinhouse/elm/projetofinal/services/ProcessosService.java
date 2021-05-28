package devinhouse.elm.projetofinal.services;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import devinhouse.elm.projetofinal.repositories.ProcessosRepository;
import devinhouse.elm.projetofinal.model.Processo;
import devinhouse.elm.projetofinal.dtos.ProcessoAtualizacaoDto;

import devinhouse.elm.projetofinal.exceptions.*;

import javax.persistence.EntityManager;
import org.modelmapper.ModelMapper;
    
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;


@Service
@Transactional
public class ProcessosService {

    private ProcessosRepository repository;
    private ModelMapper mapper;
    private EntityManager entityManager;

    public ProcessosService(ProcessosRepository repository,
			    ModelMapper mapper,
			    EntityManager entityManager) {
	this.repository = repository;
	this.entityManager = entityManager;
	this.mapper = mapper;
    }

    private void gerarCampos(Processo processo) {

	var sigla = processo.getSigla();
	var numero = repository.countDistinctBySiglaEquals(sigla) + 1;
	var ano = Year.now();
	var chave = sigla + " " + numero + "/" + ano;

	processo.setNumero(numero);
	processo.setAno(ano);
	processo.setChave(chave);
    }

    public Processo cadastrar(Processo processo) {

	if (repository.existsById(processo.getId())) throw new IdJaExisteException();

	gerarCampos(processo);
	
	if (repository.existsByChave(processo.getChave())) throw new ChaveJaExisteException();

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

    public Processo atualizar(Processo atualizacao) throws NoSuchElementException {
	
	var processo = repository.findById(atualizacao.getId()).get();
	
	processo.setDescricao(atualizacao.getDescricao());
	processo.setAssunto(atualizacao.getAssunto());
	processo.setInteressado(atualizacao.getInteressado());
	
	processo = repository.save(processo);
	entityManager.refresh(processo);
	
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

    public List<Processo> buscarPorInteressadoId(long id) {
	return repository.findByInteressadoId(id);
    }

    public void excluirPorId(int id) {
	repository.deleteById(id);
    }
}
