package com.example.exception;

public class AlreadyExistsException extends RuntimeException {
      
    public AlreadyExistsException() {
        super("Conflict");
    }
}
