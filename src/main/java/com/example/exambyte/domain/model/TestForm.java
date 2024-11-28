package com.example.exambyte.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TestForm(
        @NotBlank(message = "darf nicht leer sein") String name,
        @NotNull(message = "darf nicht leer sein") LocalDateTime startTime,
        @NotNull(message = "darf nicht leer sein") LocalDateTime endTime) {
}
