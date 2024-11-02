package com.example.exambyte.builder;

import com.example.exambyte.data.Frage;

public class FrageBuilder {
    String name;
    String fragestellung;
    String antwort;
    Integer maxPunktzahl;

    public FrageBuilder() {
        name = "dummyName";
        fragestellung = "dummyFragestellung";
        antwort = "dummyAntwort";
        maxPunktzahl = 1;
    }

    public FrageBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public FrageBuilder addFragestellung(String fragestellung) {
        this.fragestellung = fragestellung;
        return this;
    }

    public FrageBuilder addAntwort(String antwort) {
        this.antwort = antwort;
        return this;
    }

    public FrageBuilder addMaxPunktzahl(Integer maxPunktzahl) {
        this.maxPunktzahl = maxPunktzahl;
        return this;
    }

    public Frage build() {
        return new Frage(name, fragestellung, maxPunktzahl, antwort);
    }
}
