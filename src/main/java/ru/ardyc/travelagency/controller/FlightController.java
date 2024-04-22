package ru.ardyc.travelagency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ardyc.travelagency.dto.request.FlightCreateRequest;
import ru.ardyc.travelagency.dto.response.FlightResponse;
import ru.ardyc.travelagency.service.FlightService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/flight")
public class FlightController {
    private final FlightService flightService;

    @GetMapping("/list")
    public List<FlightResponse> getFlights(
            @RequestParam(name = "date", required = false) OffsetDateTime date,
            @RequestParam(name = "from", required = false) String from,
            @RequestParam(name = "to", required = false) String to
    ) {
        return flightService.findFlights(date, from, to);
    }


    @GetMapping("/{flight_id}")
    public FlightResponse getFlight(
            @PathVariable(name = "flight_id") String flightId
    ) {
        return flightService.getFlight(UUID.fromString(flightId));
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public FlightResponse createFlight(
            @RequestBody FlightCreateRequest request
    ) {
        return flightService.createFlight(request);
    }


    @DeleteMapping("/remove/{flight_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public FlightResponse removeFlight(
            @PathVariable(name = "flight_id") String flightId
    ) {
        return flightService.removeFlight(UUID.fromString(flightId));
    }


    @PostMapping("/book/{flight_id}")
    @PreAuthorize("hasRole('USER')")
    public FlightResponse bookFlight(
            @PathVariable(name = "flight_id") String flightId,
            @RequestHeader(name = "Authorization") String userToken
    ) {
        return flightService.bookFlight(UUID.fromString(flightId), userToken.split(" ")[1]);
    }


    @DeleteMapping("/cancel/{flight_id}")
    @PreAuthorize("hasRole('USER')")
    public FlightResponse cancelFlight(
            @PathVariable(name = "flight_id") String flightId,
            @RequestHeader(name = "Authorization") String userToken
    ) {
        return flightService.cancelFlight(UUID.fromString(flightId), userToken.split(" ")[1]);
    }

}
