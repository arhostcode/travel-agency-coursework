package ru.ardyc.travelagency.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.ardyc.travelagency.domain.model.FlightEntity;
import ru.ardyc.travelagency.domain.model.UserEntity;
import ru.ardyc.travelagency.domain.repository.FlightRepository;
import ru.ardyc.travelagency.domain.repository.UserRepository;
import ru.ardyc.travelagency.dto.request.FlightCreateRequest;
import ru.ardyc.travelagency.dto.response.FlightResponse;
import ru.ardyc.travelagency.service.jpa.JpaFlightService;
import ru.ardyc.travelagency.service.security.JwtService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class JpaFlightServiceTest {

    @Test
    void findFlightsShowReturnFlightsFromDatabase() {
        var flightRepository = Mockito.mock(FlightRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(flightRepository.findAll()).thenReturn(
                List.of(
                        FlightEntity.builder().name("Moscow - London").from("Moscow").to("London").build(),
                        FlightEntity.builder().name("Moscow - Paris").from("Moscow").to("Paris").build()
                )
        );
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaFlightService(flightRepository, jwtService, userRepository);
        var flights = service.findFlights(null, "Moscow", "London");
        Assertions.assertThat(flights).map(FlightResponse::name).containsExactly("Moscow - London");
        Mockito.verify(flightRepository).findAll();
    }

    @Test
    void createFlightShouldCreateFlightSaveInDatabase() {
        var flightRepository = Mockito.mock(FlightRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaFlightService(flightRepository, jwtService, userRepository);
        service.createFlight(new FlightCreateRequest("Moscow", "London", null, null, 100));
        Mockito.verify(flightRepository).save(Mockito.any(FlightEntity.class));
    }

    @Test
    void getFlightShouldReturnFlight() {
        var flightRepository = Mockito.mock(FlightRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(flightRepository.findById(new UUID(1, 1))).thenReturn(
                Optional.ofNullable(FlightEntity.builder().name("Moscow - London").from("Moscow").to("London").build())
        );
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaFlightService(flightRepository, jwtService, userRepository);
        var flight = service.getFlight(new UUID(1, 1));
        Assertions.assertThat(flight).isNotNull();
        Assertions.assertThat(flight).extracting(FlightResponse::name).isEqualTo("Moscow - London");
        Mockito.verify(flightRepository).findById(new UUID(1, 1));
    }

    @Test
    void removeFlightShouldRemoveFlight() {
        var flightRepository = Mockito.mock(FlightRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaFlightService(flightRepository, jwtService, userRepository);
        service.removeFlight(new UUID(1, 1));
        Mockito.verify(flightRepository).deleteById(new UUID(1, 1));
    }

    @Test
    void bookFlightShouldBookFlight() {
        var flightRepository = Mockito.mock(FlightRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(
                UserEntity.builder().flights(new ArrayList<>()).fullName("user").build()
        ));
        Mockito.when(flightRepository.findById(Mockito.any())).thenReturn(
                Optional.ofNullable(FlightEntity.builder().users(new ArrayList<>()).name("Moscow - London").from("Moscow").to("London").build())
        );
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaFlightService(flightRepository, jwtService, userRepository);
        service.bookFlight(new UUID(1, 1), "token");
        Mockito.verify(flightRepository).findById(new UUID(1, 1));
        Mockito.verify(userRepository).findById(Mockito.any());
    }

    @Test
    void cancelFlightShouldCancelFlight() {
        var flightRepository = Mockito.mock(FlightRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(
                UserEntity.builder().flights(new ArrayList<>()).fullName("user").build()
        ));
        Mockito.when(flightRepository.findById(Mockito.any())).thenReturn(
                Optional.ofNullable(FlightEntity.builder().users(new ArrayList<>()).name("Moscow - London").from("Moscow").to("London").build())
        );
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaFlightService(flightRepository, jwtService, userRepository);
        service.cancelFlight(new UUID(1, 1), "token");
        Mockito.verify(flightRepository).findById(new UUID(1, 1));
        Mockito.verify(userRepository).findById(Mockito.any());
    }
}