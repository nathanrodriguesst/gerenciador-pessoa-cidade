package com.vsm.gerenciador_pessoa_cidade.exceptions;

public class FieldNotEmptyException extends RuntimeException {
    public FieldNotEmptyException(String message) {
        super(message);
    }
}
