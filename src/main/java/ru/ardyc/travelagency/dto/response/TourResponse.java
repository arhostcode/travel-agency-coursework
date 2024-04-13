package ru.ardyc.travelagency.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

public record TourResponse(
        UUID id,
        String name,
        String description,
        OffsetDateTime startDate,
        OffsetDateTime finishDate,
        TourPlaceResponse tourPlace,
        HotelResponse hotel,
        FlightResponse flight
) {
}
