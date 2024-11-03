package com.example.exambyte.data;

import java.util.List;

public record FrageForm(FRAGETYP fragetyp,
                        String name,
                        String fragestellung,
                        Integer maxPunktzahl,
                        List<String> antwortmöglichkeiten) {
}
