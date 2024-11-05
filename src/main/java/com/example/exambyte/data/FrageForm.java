package com.example.exambyte.data;

import java.util.List;

public record FrageForm(FRAGENTYP fragentyp,
                        String name,
                        String fragestellung,
                        Integer maxPunktzahl,
                        List<AntwortMoeglichkeit> antwortMoeglichkeiten) {
}
