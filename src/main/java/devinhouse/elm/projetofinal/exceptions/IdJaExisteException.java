package devinhouse.elm.projetofinal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IdJaExisteException extends ResponseStatusException {

    public IdJaExisteException() {
        super(HttpStatus.BAD_REQUEST, "Id já existente");
    }
}
