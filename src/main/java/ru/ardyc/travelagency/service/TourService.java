package ru.ardyc.travelagency.service;

import org.springframework.security.core.userdetails.User;
import ru.ardyc.travelagency.dto.request.TourCreateRequest;
import ru.ardyc.travelagency.dto.response.TourResponse;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface TourService {
    List<TourResponse> findTours(OffsetDateTime startDate, UUID tourPlaceId);

    TourResponse getTour(UUID tourId);

    TourResponse createTour(TourCreateRequest request);

    TourResponse removeTour(UUID tourId, User user);

    TourResponse bookTour(UUID tourId, User user);

    TourResponse cancelTour(UUID tourId, User user);
}
