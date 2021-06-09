package com.example.app.dao;

public class UnknownCarException extends RuntimeException {
    public UnknownCarException(Long id) {
        super("The car with id=" + id + " doesn't exist.");
    }
}
