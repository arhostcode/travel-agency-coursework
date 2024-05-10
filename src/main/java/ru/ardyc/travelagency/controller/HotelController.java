package ru.ardyc.travelagency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ardyc.travelagency.dto.request.HotelCreateRequest;
import ru.ardyc.travelagency.dto.response.HotelResponse;
import ru.ardyc.travelagency.service.HotelService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/hotel")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/list")
    public List<HotelResponse> getHotels() {
        return hotelService.getHotels();
    }

    @GetMapping("/{hotel_id}")
    public HotelResponse getHotel(
            @PathVariable(name = "hotel_id") String hotelId
    ) {
        return hotelService.getHotel(UUID.fromString(hotelId));
    }

    @PostMapping("/book/{hotel_id}")
    @PreAuthorize("hasRole('USER')")
    public HotelResponse bookHotel(
            @PathVariable(name = "hotel_id") String hotelId,
            @RequestHeader(name = "Authorization") String userToken
    ) {
        return hotelService.bookHotel(UUID.fromString(hotelId), userToken.split(" ")[1]);
    }

    @DeleteMapping("/cancel/{hotel_id}")
    @PreAuthorize("hasRole('USER')")
    public HotelResponse cancelHotel(
            @PathVariable(name = "hotel_id") String hotelId,
            @RequestHeader(name = "Authorization") String userToken
    ) {
        return hotelService.cancelHotel(UUID.fromString(hotelId), userToken.split(" ")[1]);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public HotelResponse createHotel(
            @RequestBody HotelCreateRequest request
    ) {
        return hotelService.createHotel(request);
    }

    @DeleteMapping("/remove/{hotel_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public HotelResponse removeHotel(
            @PathVariable(name = "hotel_id") String hotelId
    ) {
        return hotelService.removeHotel(UUID.fromString(hotelId));
    }
}
