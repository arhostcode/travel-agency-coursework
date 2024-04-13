package ru.ardyc.travelagency.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

public record FlightResponse(
        UUID id,
        String from,
        String to,
        OffsetDateTime startDate,
        OffsetDateTime finishDate,
        double price
) {
}
