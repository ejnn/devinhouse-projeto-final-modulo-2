package devinhouse.elm.projetofinal.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import devinhouse.elm.projetofinal.exceptions.IdJaExisteException;
import devinhouse.elm.projetofinal.exceptions.IdentificacaoInvalidaException;
import devinhouse.elm.projetofinal.exceptions.IdentificacaoJaExisteException;

import org.hibernate.PropertyValueException;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.HttpStatus.*;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GeneralControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception) {
        return new ResponseEntity<>("Recurso inexistente!", NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIdentificacaoInvalidaException(IdentificacaoInvalidaException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIdJaExisteException(IdJaExisteException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIdentificacaoJaExisteException(IdentificacaoJaExisteException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<String> handlePropertyValueException(PropertyValueException exception) {
        return new ResponseEntity<>(
                "Erro na propriedade \"" + exception.getPropertyName() + "\" (" + exception.getCause() + ").",
                BAD_REQUEST);
    }
}
