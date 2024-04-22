package ru.ardyc.travelagency.service.jpa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ardyc.travelagency.domain.model.HotelEntity;
import ru.ardyc.travelagency.domain.repository.HotelRepository;
import ru.ardyc.travelagency.domain.repository.UserRepository;
import ru.ardyc.travelagency.dto.request.HotelCreateRequest;
import ru.ardyc.travelagency.dto.response.HotelResponse;
import ru.ardyc.travelagency.service.HotelService;
import ru.ardyc.travelagency.service.security.JwtService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class JpaHotelService implements HotelService {
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public List<HotelResponse> getHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(HotelEntity::toResponse)
                .toList();
    }

    @Override
    public HotelResponse getHotel(UUID hotelId) {
        return hotelRepository.findById(hotelId).map(HotelEntity::toResponse).orElse(null);
    }

    @Override
    public HotelResponse createHotel(HotelCreateRequest request) {
        var hotel = HotelEntity
                .builder()
                .name(request.name())
                .description(request.description())
                .city(request.city())
                .location(request.location())
                .image(request.image())
                .price(request.price())
                .build();
        hotelRepository.save(hotel);
        return hotel.toResponse();
    }

    @Override
    public HotelResponse removeHotel(UUID hotelId) {
        var hotel = hotelRepository.findById(hotelId).map(HotelEntity::toResponse).orElse(null);
        hotelRepository.deleteById(hotelId);
        return hotel;
    }

    @Override
    public HotelResponse bookHotel(UUID hotelId, String userToken) {
        var userId = jwtService.extractUserId(userToken);
        var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));
        user.bookHotel(hotel);
        userRepository.save(user);
        return hotel.toResponse();
    }

    @Override
    public HotelResponse cancelHotel(UUID hotelId, String userToken) {
        var userId = jwtService.extractUserId(userToken);
        var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));
        user.cancelHotel(hotel);
        userRepository.save(user);
        return hotel.toResponse();
    }
}
