package com.example.exambyte.unitTests;

import com.example.exambyte.data.AntwortMoeglichkeit;
import com.example.exambyte.data.FRAGENTYP;
import com.example.exambyte.data.Frage;

import java.util.ArrayList;
import java.util.List;

public class FrageTestBuilder {
    FRAGENTYP frageTyp;
    String name;
    String fragestellung;
    Integer maxPunktzahl;
    List<AntwortMoeglichkeit> antwortMoeglichkeiten = new ArrayList<AntwortMoeglichkeit>();
    String antwort;

    public FrageTestBuilder() {
        frageTyp = FRAGENTYP.FRAGENTYP_FREITEXT;
        name = "";
        fragestellung = "";
        antwort = "";
        maxPunktzahl = 0;
    }

    public FrageTestBuilder addFragetyp(FRAGENTYP frageTyp) {
        this.frageTyp = frageTyp;
        return this;
    }

    public FrageTestBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public FrageTestBuilder addFragestellung(String fragestellung) {
        this.fragestellung = fragestellung;
        return this;
    }

    public FrageTestBuilder addAntwort(String antwort) {
        this.antwort = antwort;
        return this;
    }

    public FrageTestBuilder addAntwortmoeglichkeiten(List<AntwortMoeglichkeit> antwortMoeglichkeiten){
        this.antwortMoeglichkeiten.addAll(antwortMoeglichkeiten);
        return this;
    }

    public FrageTestBuilder addMaxPunktzahl(Integer maxPunktzahl) {
        this.maxPunktzahl = maxPunktzahl;
        return this;
    }

    public Frage build() {
        return new Frage(name, fragestellung, maxPunktzahl, antwortMoeglichkeiten, frageTyp);
    }
}
