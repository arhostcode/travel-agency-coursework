package ru.ardyc.travelagency.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.ardyc.travelagency.domain.model.*;
import ru.ardyc.travelagency.domain.repository.*;
import ru.ardyc.travelagency.dto.request.TourCreateRequest;
import ru.ardyc.travelagency.service.jpa.JpaTourService;
import ru.ardyc.travelagency.service.security.JwtService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

class JpaTourServiceTest {

    @Test
    void findTours() {
        var tourRepository = Mockito.mock(TourRepository.class);
        var tourPlaceRepository = Mockito.mock(TourPlaceRepository.class);
        var flightRepository = Mockito.mock(FlightRepository.class);
        var hotelRepository = Mockito.mock(HotelRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaTourService(
                tourRepository,
                tourPlaceRepository,
                flightRepository,
                hotelRepository,
                userRepository,
                jwtService
        );
        service.findTours(null, new UUID(1, 1));
        Mockito.verify(tourRepository).findAll();
    }

    @Test
    void getTour() {
        var tourRepository = Mockito.mock(TourRepository.class);
        var tourPlaceRepository = Mockito.mock(TourPlaceRepository.class);
        var flightRepository = Mockito.mock(FlightRepository.class);
        var hotelRepository = Mockito.mock(HotelRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaTourService(
                tourRepository,
                tourPlaceRepository,
                flightRepository,
                hotelRepository,
                userRepository,
                jwtService
        );
        service.getTour(new UUID(1, 1));
        Mockito.verify(tourRepository).findById(new UUID(1, 1));
    }

    @Test
    void createTour() {
        var tourRepository = Mockito.mock(TourRepository.class);
        var tourPlaceRepository = Mockito.mock(TourPlaceRepository.class);
        var flightRepository = Mockito.mock(FlightRepository.class);
        var hotelRepository = Mockito.mock(HotelRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var jwtService = Mockito.mock(JwtService.class);
        Mockito.when(tourPlaceRepository.findById(Mockito.any())).thenReturn(
                Optional.ofNullable(TourPlaceEntity.builder().tours(new ArrayList<>()).id(new UUID(1, 1)).build())
        );
        Mockito.when(hotelRepository.findById(Mockito.any())).thenReturn(
                Optional.ofNullable(HotelEntity.builder().users(new ArrayList<>()).tours(new ArrayList<>()).id(new UUID(1, 1)).build())
        );
        Mockito.when(flightRepository.findById(Mockito.any())).thenReturn(
                Optional.ofNullable(FlightEntity.builder().users(new ArrayList<>()).tours(new ArrayList<>()).id(new UUID(1, 1)).build())
        );
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(
                Optional.ofNullable(UserEntity.builder().tours(new ArrayList<>()).flights(new ArrayList<>()).hotels(new ArrayList<>()).id(new UUID(1, 1)).build())
        );
        var service = new JpaTourService(
                tourRepository,
                tourPlaceRepository,
                flightRepository,
                hotelRepository,
                userRepository,
                jwtService
        );
        service.createTour(new TourCreateRequest("Moscow", "London", UUID.randomUUID().toString(), null, null, UUID.randomUUID().toString(), UUID.randomUUID().toString(), 100));
        Mockito.verify(tourRepository).save(Mockito.any());
    }

    @Test
    void removeTour() {
        var tourRepository = Mockito.mock(TourRepository.class);
        var tourPlaceRepository = Mockito.mock(TourPlaceRepository.class);
        var flightRepository = Mockito.mock(FlightRepository.class);
        var hotelRepository = Mockito.mock(HotelRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var jwtService = Mockito.mock(JwtService.class);
        Mockito.when(tourRepository.findById(Mockito.any())).thenReturn(
                Optional.ofNullable(TourEntity.builder().id(new UUID(1, 1))
                        .tourPlace(TourPlaceEntity.builder().tours(new ArrayList<>()).id(new UUID(1, 1)).build())
                        .hotel(HotelEntity.builder().users(new ArrayList<>()).tours(new ArrayList<>()).id(new UUID(1, 1)).build())
                        .flight(FlightEntity.builder().users(new ArrayList<>()).tours(new ArrayList<>()).id(new UUID(1, 1)).build())
                        .build())
        );
        var service = new JpaTourService(
                tourRepository,
                tourPlaceRepository,
                flightRepository,
                hotelRepository,
                userRepository,
                jwtService
        );
        service.removeTour(new UUID(1, 1));
        Mockito.verify(tourRepository).delete(Mockito.any());
    }
}