package ru.ardyc.travelagency.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

public record FlightResponse(
        UUID id,
        OffsetDateTime startDate,
        OffsetDateTime finishDate,
        double price
) {
}
