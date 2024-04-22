package ru.ardyc.travelagency.dto.response;

import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record FlightResponse(
        UUID id,
        String name,
        String from,
        String to,
        OffsetDateTime startDate,
        OffsetDateTime finishDate,
        double price
) {
}
