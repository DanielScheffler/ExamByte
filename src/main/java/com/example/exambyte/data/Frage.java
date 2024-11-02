package com.example.exambyte.data;

public record Frage(FRAGETYP fragetyp,
                    String name,
                    String fragestellung,
                    Integer maxPunktzahl,
                    String antwortmöglichkeiten,
                    String antwort) {
}
