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
import devinhouse.elm.projetofinal.dtos.InteressadoCadastroDto;
import devinhouse.elm.projetofinal.services.InteressadoService;

import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/interessado")
public class InteressadoController {

    private final InteressadoService service;
    private final ModelMapper mapper;

    public InteressadoController(InteressadoService service,
				 ModelMapper mapper) {
        this.service = service;
	this.mapper = mapper;
    }

    @ResponseStatus(CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Interessado post(@RequestBody InteressadoCadastroDto interessado) {
        return service.cadastrar(mapper.map(interessado, Interessado.class));
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
