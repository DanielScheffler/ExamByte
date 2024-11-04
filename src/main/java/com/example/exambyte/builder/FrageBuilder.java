package com.example.exambyte.builder;

import com.example.exambyte.data.AntwortMoeglichkeit;
import com.example.exambyte.data.FRAGENTYP;
import com.example.exambyte.data.Frage;

import java.util.List;

public class FrageBuilder {
    FRAGENTYP frageTyp;
    String name;
    String fragestellung;
    Integer maxPunktzahl;
    List<AntwortMoeglichkeit> antwortMoeglichkeiten;
    String antwort;

    public FrageBuilder() {
        frageTyp = FRAGENTYP.FRAGENTYP_FREITEXT;
        name = "dummyName";
        fragestellung = "dummyFragestellung";
        antwort = "dummyAntwort";
        maxPunktzahl = 1;
        antwortMoeglichkeiten = List.of(
                new AntwortMoeglichkeit("dummyAntwortmöglichkeit1", true),
                new AntwortMoeglichkeit("dummyAntwortmöglichkeit2", false));
    }

    public FrageBuilder addFragetyp(FRAGENTYP frageTyp) {
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

    public FrageBuilder addAntwortmoeglichkeiten(List<AntwortMoeglichkeit> antwortmöglichkeiten){
        this.antwortMoeglichkeiten = antwortmöglichkeiten;
        return this;
    }

    public FrageBuilder addMaxPunktzahl(Integer maxPunktzahl) {
        this.maxPunktzahl = maxPunktzahl;
        return this;
    }

    public Frage build() {
        return new Frage(name, fragestellung, maxPunktzahl, antwortMoeglichkeiten, frageTyp);
    }
}
