package ru.ardyc.travelagency.dto.response;

import java.util.UUID;

public record TourPlaceResponse(
        UUID id,
        String name,
        String description,
        String city
) {
}
