package ru.ardyc.travelagency.dto.request;

import java.time.OffsetDateTime;

public record FlightCreateRequest(
        String from,
        String to,
        OffsetDateTime startDate,
        OffsetDateTime finishDate,
        double price
) {
}
