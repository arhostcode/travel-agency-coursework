package ru.ardyc.travelagency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ardyc.travelagency.dto.request.TourPlaceCreateRequest;
import ru.ardyc.travelagency.dto.response.TourPlaceResponse;
import ru.ardyc.travelagency.service.TourPlaceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tour-place")
@RequiredArgsConstructor
public class TourPlaceController {
    private final TourPlaceService tourPlaceService;

    @GetMapping("/list")
    public List<TourPlaceResponse> getTourPlaces() {
        return tourPlaceService.getTourPlaces();
    }


    @GetMapping("/{tour_place_id}")
    public TourPlaceResponse getTourPlace(
            @PathVariable(name = "tour_place_id") String tourPlaceId
    ) {
        return tourPlaceService.getTourPlace(UUID.fromString(tourPlaceId));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public TourPlaceResponse createTourPlace(
            @RequestBody TourPlaceCreateRequest request
    ) {
        return tourPlaceService.createTourPlace(request);
    }

    @DeleteMapping("/remove/{tour_place_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TourPlaceResponse createTourPlace(
            @PathVariable(name = "tour_place_id") String tourPlaceId
    ) {
        return tourPlaceService.removeTourPlace(UUID.fromString(tourPlaceId));
    }

}
