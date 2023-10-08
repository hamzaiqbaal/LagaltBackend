package com.example.lagaltcaseapplication.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super("Project not found: " + id);
    }
}

