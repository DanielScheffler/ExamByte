package com.example.exambyte.data;

import java.time.LocalDateTime;

public record TestForm(
        String name,
        LocalDateTime startDate,
        LocalDateTime endDate) {
}
