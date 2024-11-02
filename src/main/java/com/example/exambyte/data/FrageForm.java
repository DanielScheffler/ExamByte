package com.example.exambyte.data;

public record FrageForm(FRAGETYP fragetyp,
                        String name,
                        String fragestellung,
                        Integer maxPunktzahl,
                        String antwortmöglichkeiten) {
}
