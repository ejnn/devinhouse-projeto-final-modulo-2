package devinhouse.elm.projetofinal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IdentificacaoInvalidaException extends ResponseStatusException {

    public IdentificacaoInvalidaException() {
        super(HttpStatus.BAD_REQUEST, "Identificação inválida");
    }
}
