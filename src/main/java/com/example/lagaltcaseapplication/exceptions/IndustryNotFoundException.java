package com.example.lagaltcaseapplication.exceptions;

public class IndustryNotFoundException extends RuntimeException {
    public IndustryNotFoundException(Long id) {
        super("Could not find industry with id " + id);
    }
}

