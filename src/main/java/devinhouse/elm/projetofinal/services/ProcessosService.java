package devinhouse.elm.projetofinal.services;

import org.springframework.stereotype.Service;

import devinhouse.elm.projetofinal.repositories.ProcessosRepository;
import devinhouse.elm.projetofinal.model.Processo;

import devinhouse.elm.projetofinal.exceptions.*;

import java.time.Year;


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
}
