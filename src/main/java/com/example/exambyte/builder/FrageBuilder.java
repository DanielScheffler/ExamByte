package com.example.exambyte.builder;

import com.example.exambyte.data.Antwortmoeglichkeit;
import com.example.exambyte.data.FRAGENTYP;
import com.example.exambyte.data.Frage;

import java.util.List;

public class FrageBuilder {
    FRAGENTYP frageTyp;
    String name;
    String fragestellung;
    Integer maxPunktzahl;
    List<Antwortmoeglichkeit> antwortmöglichkeiten;
    String antwort;

    public FrageBuilder() {
        frageTyp = FRAGENTYP.FRAGENTYP_FREITEXT;
        name = "dummyName";
        fragestellung = "dummyFragestellung";
        antwort = "dummyAntwort";
        maxPunktzahl = 1;
        antwortmöglichkeiten = List.of(
                new Antwortmoeglichkeit("dummyAntwortmöglichkeit1", true),
                new Antwortmoeglichkeit("dummyAntwortmöglichkeit2", false));
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

    public FrageBuilder addAntwortmoeglichkeiten(List<Antwortmoeglichkeit> antwortmöglichkeiten){
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
