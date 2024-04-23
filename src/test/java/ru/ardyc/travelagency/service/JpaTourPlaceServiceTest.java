package ru.ardyc.travelagency.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.ardyc.travelagency.domain.repository.TourPlaceRepository;
import ru.ardyc.travelagency.dto.request.TourPlaceCreateRequest;
import ru.ardyc.travelagency.service.jpa.JpaTourPlaceService;

import java.util.UUID;

class JpaTourPlaceServiceTest {

    @Test
    void getTourPlaces() {
        var tourPlaceRepository = Mockito.mock(TourPlaceRepository.class);
        var service = new JpaTourPlaceService(tourPlaceRepository);
        service.getTourPlaces();
        Mockito.verify(tourPlaceRepository).findAll();
    }

    @Test
    void getTourPlace() {
        var tourPlaceRepository = Mockito.mock(TourPlaceRepository.class);
        var service = new JpaTourPlaceService(tourPlaceRepository);
        service.getTourPlace(new UUID(1, 1));
        Mockito.verify(tourPlaceRepository).findById(new UUID(1, 1));
    }

    @Test
    void createTourPlace() {
        var tourPlaceRepository = Mockito.mock(TourPlaceRepository.class);
        var service = new JpaTourPlaceService(tourPlaceRepository);
        service.createTourPlace(new TourPlaceCreateRequest(
                "Moscow",
                "London",
                null
        ));
        Mockito.verify(tourPlaceRepository).save(Mockito.any());
    }

    @Test
    void removeTourPlace() {
        var tourPlaceRepository = Mockito.mock(TourPlaceRepository.class);
        var service = new JpaTourPlaceService(tourPlaceRepository);
        service.removeTourPlace(new UUID(1, 1));
        Mockito.verify(tourPlaceRepository).deleteById(new UUID(1, 1));
    }
}