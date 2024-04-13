package ru.ardyc.travelagency.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegistrationRequest(
        String email,
        String password,
        @JsonProperty("full_name") String fullName
) {
}
