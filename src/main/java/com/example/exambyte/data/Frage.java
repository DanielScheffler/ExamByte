package com.example.exambyte.data;

import java.util.List;

public record Frage(FRAGETYP fragetyp,
                    String name,
                    String fragestellung,
                    Integer maxPunktzahl,
                    List<String> antwortmöglichkeiten,
                    String antwort) {
}
