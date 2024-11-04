package com.example.exambyte.data;

import java.util.List;

public record Frage(String name, String fragestellung, Integer maxPunktzahl, List<AntwortMoeglichkeit> antwortMoeglichkeiten, FRAGENTYP fragentyp) {
}
