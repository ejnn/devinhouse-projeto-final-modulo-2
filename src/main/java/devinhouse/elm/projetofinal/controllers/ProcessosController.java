package devinhouse.elm.projetofinal.controllers;

import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

import devinhouse.elm.projetofinal.services.ProcessosService;
import devinhouse.elm.projetofinal.model.Processo;


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
}
