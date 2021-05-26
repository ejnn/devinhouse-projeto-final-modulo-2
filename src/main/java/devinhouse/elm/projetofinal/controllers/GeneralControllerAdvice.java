package devinhouse.elm.projetofinal.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<String> handlePropertyValueException(PropertyValueException exception) {
        return new ResponseEntity<>("Objeto inexistente!", NOT_ACCEPTABLE);
    }
}
