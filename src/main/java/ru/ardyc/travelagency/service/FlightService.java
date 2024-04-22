package ru.ardyc.travelagency.service;

import ru.ardyc.travelagency.dto.request.FlightCreateRequest;
import ru.ardyc.travelagency.dto.response.FlightResponse;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface FlightService {

    List<FlightResponse> findFlights(OffsetDateTime date, String from, String to);

    FlightResponse getFlight(UUID flightId);

    FlightResponse createFlight(FlightCreateRequest request);

    FlightResponse removeFlight(UUID flightId);

    FlightResponse bookFlight(UUID flightId, String userToken);

    FlightResponse cancelFlight(UUID flightId, String userToken);
}
