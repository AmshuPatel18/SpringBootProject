package com.ivoyant.SpringBootFinalProject.exception;


public class NameBlankException extends RuntimeException {
    public NameBlankException(String entity) {
        super(entity + " name cannot be blank.");
    }
}
