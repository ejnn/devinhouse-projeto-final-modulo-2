package devinhouse.elm.projetofinal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.*;
import static org.springframework.http.HttpStatus.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import devinhouse.elm.projetofinal.model.Interessado;
import devinhouse.elm.projetofinal.services.InteressadoService;

@RestController
@RequestMapping("/interessado")
public class InteressadoController {

    private final InteressadoService service;

    public InteressadoController(InteressadoService service) {
        this.service = service;
    }

    @ResponseStatus(CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Interessado post(@RequestBody Interessado interessado) {
        return service.cadastrar(interessado);
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public Interessado getPorId(@PathVariable Long id) throws NoSuchElementException {
        Optional<Interessado> interessado = service.obterPorId(id);
        return interessado.get();
    }

    @ResponseStatus(OK)
    @GetMapping("/identificacao")
    public Interessado getPorIdentificacao(@RequestParam String identificacao) throws NoSuchElementException {
        Optional<Interessado> interessado = service.obterPorIdentificacao(identificacao);
        return interessado.get();
    }

}
