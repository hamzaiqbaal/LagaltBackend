package com.example.lagaltcaseapplication.enums;

public enum Skills {
    JAVA(1, "Java"),
    PYTHON(2, "Python"),
    JAVASCRIPT(3, "Javascript"),
    PRODUCTION(4, "Production"),
    DESIGN(5, "Design");

    private final int id;
    private final String name;

    Skills(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    }

