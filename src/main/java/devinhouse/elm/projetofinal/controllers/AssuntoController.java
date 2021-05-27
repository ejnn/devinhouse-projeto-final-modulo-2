package devinhouse.elm.projetofinal.controllers;

import java.util.Optional;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.PropertyValueException;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

import devinhouse.elm.projetofinal.services.AssuntoService;

import devinhouse.elm.projetofinal.model.Assunto;
import devinhouse.elm.projetofinal.dtos.AssuntoCadastroDto;

import org.modelmapper.ModelMapper;

import java.time.LocalDate;


@RestController
@RequestMapping("/assuntos")
public class AssuntoController {

    private final AssuntoService service;
    private final ModelMapper mapper;

    public AssuntoController(AssuntoService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Assunto post(@RequestBody AssuntoCadastroDto assunto) {

	var novoAssunto = new Assunto();
	novoAssunto.setDataCadastro(LocalDate.now());
	mapper.map(assunto, novoAssunto);

        return service.cadastrar(novoAssunto);
    }

    @GetMapping
    @ResponseStatus(ACCEPTED)
    public List<Assunto> get() {
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Assunto getPorId(@PathVariable long id) throws NoSuchElementException {
        Optional<Assunto> assunto = service.buscarPorId(id);
        return assunto.get();
    }

}
