package com.example.exambyte.data;

import java.util.List;

public record FrageForm(FRAGENTYP fragetyp,
                        String name,
                        String fragestellung,
                        Integer maxPunktzahl,
                        List<AntwortMoeglichkeit> antwortMoeglichkeiten) {
}
