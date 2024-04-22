package ru.ardyc.travelagency.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ardyc.travelagency.dto.request.TourCreateRequest;
import ru.ardyc.travelagency.dto.response.TourResponse;
import ru.ardyc.travelagency.service.TourService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tour")
@CrossOrigin
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;

    @GetMapping("/list")
    public List<TourResponse> getTours(
            @RequestParam(name = "start_date", required = false) OffsetDateTime startDate,
            @RequestParam(name = "tour_place", required = false) String tourPlace
    ) {
        return tourService.findTours(startDate, tourPlace == null ? null : UUID.fromString(tourPlace));
    }

    @GetMapping("/{tour_id}")
    public TourResponse getTour(
            @PathVariable(name = "tour_id") String tourId
    ) {
        return tourService.getTour(UUID.fromString(tourId));
    }

    @PostMapping("/book/{tour_id}")
    @PreAuthorize("hasRole('USER')")
    public TourResponse bookTour(
            @PathVariable(name = "tour_id") String tourId,
            @RequestHeader(name = "Authorization") String userToken
    ) {
        return tourService.bookTour(UUID.fromString(tourId), userToken.split(" ")[1]);
    }


    @DeleteMapping("/cancel/{tour_id}")
    @PreAuthorize("hasRole('USER')")
    public TourResponse cancelTour(
            @PathVariable(name = "tour_id") String tourId,
            @RequestHeader(name = "Authorization") String userToken
    ) {
        return tourService.cancelTour(UUID.fromString(tourId), userToken.split(" ")[1]);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public TourResponse createTour(
            @RequestBody TourCreateRequest request
    ) {
        return tourService.createTour(request);
    }


    @DeleteMapping("/remove/{tour_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TourResponse removeTour(
            @PathVariable(name = "tour_id") String tourId
    ) {
        return tourService.removeTour(UUID.fromString(tourId));
    }
}
