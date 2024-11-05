package com.example.exambyte.data;

import java.time.LocalDateTime;

public record TestForm(
        String name,
        LocalDateTime startTime,
        LocalDateTime endTime) {
}
