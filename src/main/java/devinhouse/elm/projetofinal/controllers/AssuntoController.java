package devinhouse.elm.projetofinal.controllers;

import java.util.Optional;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

import devinhouse.elm.projetofinal.services.AssuntoService;

import devinhouse.elm.projetofinal.model.Assunto;

@RestController
@RequestMapping("/assuntos")
public class AssuntoController {

    private final AssuntoService service;

    public AssuntoController(AssuntoService service) {
        this.service = service;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Assunto post(@RequestBody Assunto assunto) {
        return service.cadastrar(assunto);
    }

    @GetMapping
    @ResponseStatus(ACCEPTED)
    public List<Assunto> get() {
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(ACCEPTED)
    public Assunto getPorId(@PathVariable long id) throws NoSuchElementException {
        Optional<Assunto> assunto = service.buscarPorId(id);
        return assunto.get();
    }

}
