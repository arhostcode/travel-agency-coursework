package ru.ardyc.travelagency.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ardyc.travelagency.domain.model.TourEntity;
import ru.ardyc.travelagency.domain.repository.*;
import ru.ardyc.travelagency.dto.request.TourCreateRequest;
import ru.ardyc.travelagency.dto.response.TourResponse;
import ru.ardyc.travelagency.service.TourService;
import ru.ardyc.travelagency.service.security.JwtService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JpaTourService implements TourService {
    private final TourRepository tourRepository;
    private final TourPlaceRepository tourPlaceRepository;
    private final FlightRepository flightRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public List<TourResponse> findTours(OffsetDateTime startDate, UUID tourPlaceId) {
        var tours = tourRepository.findAll();
        return tours.stream()
                .filter(tour -> startDate == null || startDate.isBefore(tour.getStartDate()))
                .filter(tour -> tourPlaceId == null || tour.getTourPlace().getId().equals(tourPlaceId))
                .map(TourEntity::toResponse)
                .toList();
    }

    @Override
    public TourResponse getTour(UUID tourId) {
        return tourRepository.findById(tourId).map(TourEntity::toResponse).orElse(null);
    }

    @Override
    public TourResponse createTour(TourCreateRequest request) {
        var tour = TourEntity.builder()
                .name(request.name())
                .description(request.description())
                .startDate(request.startDate())
                .finishDate(request.finishDate())
                .tourPlace(tourPlaceRepository.findById(UUID.fromString(request.tourPlaceId())).orElse(null))
                .hotel(hotelRepository.findById(UUID.fromString(request.hotelId())).orElse(null))
                .flight(flightRepository.findById(UUID.fromString(request.flightId())).orElse(null))
                .build();
        tourRepository.save(tour);
        return tour.toResponse();
    }

    @Override
    public TourResponse removeTour(UUID tourId) {
        var tour = tourRepository.findById(tourId).orElseThrow();
        tourRepository.delete(tour);
        return tour.toResponse();
    }

    @Override
    public TourResponse bookTour(UUID tourId, String userToken) {
        var tour = tourRepository.findById(tourId).orElseThrow();
        var user = userRepository.findById(jwtService.extractUserId(userToken)).orElseThrow();
        user.bookTour(tour);
        userRepository.save(user);
        return tour.toResponse();
    }

    @Override
    public TourResponse cancelTour(UUID tourId, String userToken) {
        var tour = tourRepository.findById(tourId).orElseThrow();
        var user = userRepository.findById(jwtService.extractUserId(userToken)).orElseThrow();
        user.cancelTour(tour);
        userRepository.save(user);
        return tour.toResponse();
    }
}
