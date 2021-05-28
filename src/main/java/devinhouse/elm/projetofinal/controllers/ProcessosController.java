package devinhouse.elm.projetofinal.controllers;

import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

import devinhouse.elm.projetofinal.services.ProcessosService;
import devinhouse.elm.projetofinal.dtos.ProcessoCadastroDto;
import devinhouse.elm.projetofinal.model.Processo;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/processos")
public class ProcessosController {

    private final ProcessosService service;
    private final ModelMapper mapper;

    public ProcessosController(ProcessosService service, ModelMapper mapper) {
	this.service = service;
	this.mapper = mapper;
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public Processo post(@RequestBody ProcessoCadastroDto processo) {
	return service.cadastrar(mapper.map(processo, Processo.class));
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Processo> get() {
	return service.listarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Processo getPorId(@PathVariable int id) throws NoSuchElementException {
	Optional<Processo> processo = service.buscarPorId(id);
	return processo.get();
    }

    @GetMapping(params = "chave")
    @ResponseStatus(OK)
    public List<Processo> getPorChave(@RequestParam String chave) {
	return service.buscarPorChave(chave);
    }

    @GetMapping(params = "assuntoId")
    @ResponseStatus(OK)
    public List<Processo> getPorAssuntoId(@RequestParam long assuntoId) {
	return service.buscarPorAssuntoId(assuntoId);
    }

    @GetMapping(params = "interessadoId")
    @ResponseStatus(OK)
    public List<Processo> getPorInteressadoId(@RequestParam long interessadoId) {
	return service.buscarPorInteressadoId(interessadoId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletePorId(@PathVariable int id) {
	service.excluirPorId(id);
    }
}
