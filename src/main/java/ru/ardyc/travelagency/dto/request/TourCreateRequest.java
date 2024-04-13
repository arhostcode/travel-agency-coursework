package ru.ardyc.travelagency.dto.request;

import java.time.OffsetDateTime;

public record TourCreateRequest(
        String name,
        String description,
        String tourPlaceId,
        OffsetDateTime startDate,
        OffsetDateTime finishDate,
        String hotelId,
        String flightId,
        double price
) {
}
