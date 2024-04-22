package ru.ardyc.travelagency.dto.response;

import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
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
