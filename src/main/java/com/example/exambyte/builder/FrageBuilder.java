package com.example.exambyte.builder;

import com.example.exambyte.data.AntwortMoeglichkeit;
import com.example.exambyte.data.FRAGENTYP;
import com.example.exambyte.data.Frage;

import java.util.ArrayList;
import java.util.List;

public class FrageBuilder {
    FRAGENTYP frageTyp;
    String name;
    String fragestellung;
    Integer maxPunktzahl;
    List<AntwortMoeglichkeit> antwortMoeglichkeiten = new ArrayList<AntwortMoeglichkeit>();
    String antwort;

    public FrageBuilder() {
        frageTyp = FRAGENTYP.FRAGENTYP_FREITEXT;
        name = "";
        fragestellung = "";
        antwort = "";
        maxPunktzahl = 0;
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

    public FrageBuilder addAntwortmoeglichkeiten(List<AntwortMoeglichkeit> antwortMoeglichkeiten){
        this.antwortMoeglichkeiten.addAll(antwortMoeglichkeiten);
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
