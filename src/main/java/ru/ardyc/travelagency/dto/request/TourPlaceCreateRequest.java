package ru.ardyc.travelagency.dto.request;

public record TourPlaceCreateRequest(
        String name,
        String description,
        String city
) {
}
