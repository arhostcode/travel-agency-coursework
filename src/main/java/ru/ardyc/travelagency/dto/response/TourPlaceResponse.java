package ru.ardyc.travelagency.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record TourPlaceResponse(
        UUID id,
        String name,
        String description,
        String city
) {
}
