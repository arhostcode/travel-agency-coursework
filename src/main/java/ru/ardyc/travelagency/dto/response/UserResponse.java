package ru.ardyc.travelagency.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record UserResponse(
        UUID id,
        String email,
        @JsonProperty("full_name") String fullName,
        @JsonProperty("jwt_token") String jwtToken,
        List<HotelResponse> hotels,
        List<FlightResponse> flights
) {
}
