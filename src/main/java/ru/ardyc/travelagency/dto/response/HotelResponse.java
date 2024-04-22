package ru.ardyc.travelagency.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record HotelResponse(
        UUID id,
        String name,
        String description,
        String city,
        String location,
        String image,
        double price
) {
}
