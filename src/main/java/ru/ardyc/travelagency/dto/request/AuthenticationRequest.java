package ru.ardyc.travelagency.dto.request;

public record AuthenticationRequest(
        String email,
        String password
) {
}
