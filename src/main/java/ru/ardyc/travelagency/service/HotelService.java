package ru.ardyc.travelagency.service;

import ru.ardyc.travelagency.dto.request.HotelCreateRequest;
import ru.ardyc.travelagency.dto.response.HotelResponse;

import java.util.List;
import java.util.UUID;

public interface HotelService {

    List<HotelResponse> getHotels();

    HotelResponse getHotel(UUID hotelId);

    HotelResponse createHotel(HotelCreateRequest request);

    HotelResponse removeHotel(UUID hotelId);

    HotelResponse bookHotel(UUID hotelId, String userToken);

    HotelResponse cancelHotel(UUID hotelId, String userToken);

}
