package com.vsm.gerenciador_pessoa_cidade.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // 404
                .body(exception.getMessage());
    }

    @ExceptionHandler({DuplicateResourceException.class})
    public ResponseEntity<Object> handleDuplicateResourceException(DuplicateResourceException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409
                .body(exception.getMessage());
    }

    @ExceptionHandler({FieldNotEmptyException.class})
    public ResponseEntity<Object> handleFieldNotEmptyException(FieldNotEmptyException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body(exception.getMessage());
    }

    @ExceptionHandler(ResourceInUseException.class)
    public ResponseEntity<Object> handleCidadeInUseException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body("Ocorreu um erro inesperado! " + exception.getMessage());
    }
}