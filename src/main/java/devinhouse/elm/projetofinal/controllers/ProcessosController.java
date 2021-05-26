package devinhouse.elm.projetofinal.controllers;

import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

import devinhouse.elm.projetofinal.services.ProcessosService;
import devinhouse.elm.projetofinal.model.Processo;

import java.util.List;


@RestController
@RequestMapping("/processos")
public class ProcessosController {

    private final ProcessosService service;

    public ProcessosController(ProcessosService service) {
	this.service = service;
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public Processo post(@RequestBody Processo processo) {
	return service.cadastrar(processo);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Processo> get() {
	return service.listarTodos();
    }
}
