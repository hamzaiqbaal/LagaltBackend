package com.example.lagaltcaseapplication.enums;

public enum Industry {
    WEBDEVELOPMENT(1, "Web Development"),
    GRAPHICDESIGN(2, "Graphic Design"),
    GAMEDEVELOPMENT(3, "Game Development"),
    MUSIC(4, "Music"),
    FILM(5, "Film");

    private final int id;
    private final String name;

    Industry(int id, String name) {
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
