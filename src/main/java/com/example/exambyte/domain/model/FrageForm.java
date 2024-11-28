package com.example.exambyte.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public record FrageForm(@NotNull(message = "darf nicht null sein") FRAGENTYP fragentyp,
                        @NotBlank(message = "darf nicht leer sein") String name,
                        @NotBlank(message = "darf nicht leer sein") String fragestellung,
                        @Min(value = 1 , message = "maxPunktzahl muss größer als 0 sein") @NotNull(message = "darf nicht leer sein") Integer maxPunktzahl,
                        List<AntwortMoeglichkeit> antwortMoeglichkeiten) {
    public FrageForm {
        if (antwortMoeglichkeiten == null) {
            antwortMoeglichkeiten = new ArrayList<AntwortMoeglichkeit>();
        }
    }
}

