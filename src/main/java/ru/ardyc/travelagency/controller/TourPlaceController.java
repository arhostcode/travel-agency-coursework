package ru.ardyc.travelagency.controller;

import org.springframework.web.bind.annotation.*;
import ru.ardyc.travelagency.dto.request.TourPlaceCreateRequest;
import ru.ardyc.travelagency.dto.response.TourPlaceResponse;

import java.util.List;

@RestController
@RequestMapping("/tour-place")
public class TourPlaceController {

    @GetMapping("/list")
    public List<TourPlaceResponse> getTourPlaces() {
        return List.of();
    }


    @GetMapping("/{tour_place_id}")
    public TourPlaceResponse getTourPlace(
            @PathVariable(name = "tour_place_id") String tourPlaceId
    ) {
        return null;
    }

    @PostMapping("/create")
    public TourPlaceResponse createTourPlace(
            @RequestBody TourPlaceCreateRequest request
    ) {
        return null;
    }

    @DeleteMapping("/remove/{tour_place_id}")
    public TourPlaceResponse createTourPlace(
            @PathVariable(name = "tour_place_id") String tourPlaceId
    ) {
        return null;
    }

}
