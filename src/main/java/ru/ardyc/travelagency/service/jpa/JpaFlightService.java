package ru.ardyc.travelagency.service.jpa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ardyc.travelagency.domain.model.FlightEntity;
import ru.ardyc.travelagency.domain.repository.FlightRepository;
import ru.ardyc.travelagency.domain.repository.UserRepository;
import ru.ardyc.travelagency.dto.request.FlightCreateRequest;
import ru.ardyc.travelagency.dto.response.FlightResponse;
import ru.ardyc.travelagency.service.FlightService;
import ru.ardyc.travelagency.service.security.JwtService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class JpaFlightService implements FlightService {
    private final FlightRepository flightRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;


    @Override
    public List<FlightResponse> findFlights(OffsetDateTime date, String from, String to) {
        var flights = flightRepository.findAll();
        return flights.stream()
                .filter(f -> from == null || f.getFrom().equals(from))
                .filter(f -> to == null || f.getTo().equals(to))
                .filter(f -> date == null || f.getStartDate().isAfter(date))
                .map(FlightEntity::toResponse)
                .toList();
    }

    @Override
    public FlightResponse getFlight(UUID flightId) {
        return flightRepository.findById(flightId)
                .map(FlightEntity::toResponse)
                .orElse(null);
    }

    @Override
    public FlightResponse createFlight(FlightCreateRequest request) {
        var flight = FlightEntity
                .builder()
                .from(request.from())
                .to(request.to())
                .startDate(request.startDate())
                .finishDate(request.finishDate())
                .price(request.price())
                .build();
        flightRepository.save(flight);
        return flight.toResponse();
    }

    @Override
    public FlightResponse removeFlight(UUID flightId) {
        var flight = flightRepository.findById(flightId).map(FlightEntity::toResponse).orElse(null);
        flightRepository.deleteById(flightId);
        return flight;
    }

    @Override
    public FlightResponse bookFlight(UUID flightId, String userToken) {
        var userId = jwtService.extractUserId(userToken);
        var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        user.bookFlight(flight);
        userRepository.save(user);
        return flight.toResponse();
    }

    @Override
    public FlightResponse cancelFlight(UUID flightId, String userToken) {
        var userId = jwtService.extractUserId(userToken);
        var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        user.cancelFlight(flight);
        userRepository.save(user);
        return flight.toResponse();
    }
}
