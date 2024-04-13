package ru.ardyc.travelagency.dto.response;

import java.util.UUID;

public record HotelResponse(
        UUID id,
        String name,
        String description,
        String city,
        String location,
        double price
) {
}
