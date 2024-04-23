package ru.ardyc.travelagency.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.ardyc.travelagency.domain.model.HotelEntity;
import ru.ardyc.travelagency.domain.model.UserEntity;
import ru.ardyc.travelagency.domain.repository.HotelRepository;
import ru.ardyc.travelagency.domain.repository.UserRepository;
import ru.ardyc.travelagency.dto.request.HotelCreateRequest;
import ru.ardyc.travelagency.service.jpa.JpaHotelService;
import ru.ardyc.travelagency.service.security.JwtService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JpaHotelServiceTest {

    @Test
    void getHotels() {
        var hotelRepository = Mockito.mock(HotelRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaHotelService(hotelRepository, userRepository, jwtService);
        service.getHotels();
        Mockito.verify(hotelRepository).findAll();
    }

    @Test
    void getHotel() {
        var hotelRepository = Mockito.mock(HotelRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaHotelService(hotelRepository, userRepository, jwtService);
        service.getHotel(new UUID(1, 1));
        Mockito.verify(hotelRepository).findById(new UUID(1, 1));
    }

    @Test
    void createHotel() {
        var hotelRepository = Mockito.mock(HotelRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaHotelService(hotelRepository, userRepository, jwtService);
        service.createHotel(new HotelCreateRequest(
                "Moscow",
                "London",
                null,
                null,
                null,
                100
        ));
        Mockito.verify(hotelRepository).save(Mockito.any(HotelEntity.class));
    }

    @Test
    void removeHotel() {
        var hotelRepository = Mockito.mock(HotelRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaHotelService(hotelRepository, userRepository, jwtService);
        service.removeHotel(new UUID(1, 1));
        Mockito.verify(hotelRepository).deleteById(new UUID(1, 1));
    }

    @Test
    void bookHotel() {
        var hotelRepository = Mockito.mock(HotelRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(
                Optional.ofNullable(UserEntity.builder().hotels(new ArrayList<>()).id(new UUID(1, 1)).build())
        );
        Mockito.when(hotelRepository.findById(Mockito.any())).thenReturn(
                Optional.ofNullable(HotelEntity.builder().users(new ArrayList<>()).id(new UUID(1, 1)).build())
        );
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaHotelService(hotelRepository, userRepository, jwtService);
        service.bookHotel(new UUID(1, 1), "token");
        Mockito.verify(hotelRepository).findById(new UUID(1, 1));
        Mockito.verify(userRepository).findById(Mockito.any());
    }

    @Test
    void cancelHotel() {
        var hotelRepository = Mockito.mock(HotelRepository.class);
        var userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(
                Optional.ofNullable(UserEntity.builder().hotels(new ArrayList<>()).id(new UUID(1, 1)).build())
        );
        Mockito.when(hotelRepository.findById(Mockito.any())).thenReturn(
                Optional.ofNullable(HotelEntity.builder().users(new ArrayList<>()).id(new UUID(1, 1)).build())
        );
        var jwtService = Mockito.mock(JwtService.class);
        var service = new JpaHotelService(hotelRepository, userRepository, jwtService);
        service.cancelHotel(new UUID(1, 1), "token");
        Mockito.verify(hotelRepository).findById(new UUID(1, 1));
        Mockito.verify(userRepository).findById(Mockito.any());
    }
}