package com.example.exambyte.builder;

import com.example.exambyte.data.FRAGETYP;
import com.example.exambyte.data.Frage;

public class FrageBuilder {
    FRAGETYP frageTyp;
    String name;
    String fragestellung;
    Integer maxPunktzahl;
    String antwortmöglichkeiten;
    String antwort;

    public FrageBuilder() {
        frageTyp = FRAGETYP.FRAGETYP_FREITEXT;
        name = "dummyName";
        fragestellung = "dummyFragestellung";
        antwort = "dummyAntwort";
        maxPunktzahl = 1;
        antwortmöglichkeiten = "dummyAntwortmöglichkeiten";
    }

    public FrageBuilder addFragetyp(FRAGETYP frageTyp) {
        this.frageTyp = frageTyp;
        return this;
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

    public FrageBuilder addAntwortmöglichkeiten(String antwortmöglichkeiten){
        this.antwortmöglichkeiten = antwortmöglichkeiten;
        return this;
    }

    public FrageBuilder addMaxPunktzahl(Integer maxPunktzahl) {
        this.maxPunktzahl = maxPunktzahl;
        return this;
    }

    public Frage build() {
        return new Frage(frageTyp, name, fragestellung, maxPunktzahl, antwortmöglichkeiten, antwort);
    }
}
