package ru.ardyc.travelagency.service;

import ru.ardyc.travelagency.dto.request.TourPlaceCreateRequest;
import ru.ardyc.travelagency.dto.response.TourPlaceResponse;

import java.util.List;
import java.util.UUID;

public interface TourPlaceService {

    List<TourPlaceResponse> getTourPlaces();

    TourPlaceResponse getTourPlace(UUID tourPlaceId);

    TourPlaceResponse createTourPlace(TourPlaceCreateRequest request);

    TourPlaceResponse removeTourPlace(UUID tourPlaceId);
}
