package ru.ardyc.travelagency.dto.request;

public record HotelCreateRequest(
        String name,
        String description,
        String city,
        String location,
        String image,
        double price
) {
}
