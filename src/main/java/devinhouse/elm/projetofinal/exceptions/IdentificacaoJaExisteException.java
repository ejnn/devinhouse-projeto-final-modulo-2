package devinhouse.elm.projetofinal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IdentificacaoJaExisteException extends ResponseStatusException {

    public IdentificacaoJaExisteException() {
        super(HttpStatus.BAD_REQUEST, "Identificação já existente");
    }
}
