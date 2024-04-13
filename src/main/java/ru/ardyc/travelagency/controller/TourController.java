package ru.ardyc.travelagency.controller;


import org.springframework.web.bind.annotation.*;
import ru.ardyc.travelagency.dto.response.TourResponse;

import java.time.OffsetTime;
import java.util.List;

@RestController
@RequestMapping("/tour")
public class TourController {

    @GetMapping("/list")
    public List<TourResponse> getTours(
            @RequestParam(name = "start_date", required = false) OffsetTime startDate,
            @RequestParam(name = "tour_place", required = false) String tourPlace
    ) {
        return List.of();
    }

    @GetMapping("/{tour_id}")
    public TourResponse getTour(
            @PathVariable(name = "tour_id") String tourId
    ) {
        return null;
    }

    @GetMapping("/book/{tour_id}")
    public TourResponse bookTour(@PathVariable(name = "tour_id") String tourId) {
        return null;
    }


    @DeleteMapping("/cancel/{tour_id}")
    public TourResponse cancelTour(@PathVariable(name = "tour_id") String tourId) {
        return null;
    }

}
