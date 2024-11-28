package com.example.exambyte.domain.model;

import java.util.List;

public record Frage(String name, String fragestellung, Integer maxPunktzahl, List<AntwortMoeglichkeit> antwortMoeglichkeiten, FRAGENTYP fragentyp) {
}
