package ru.ardyc.travelagency.service.jpa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ardyc.travelagency.domain.model.TourPlaceEntity;
import ru.ardyc.travelagency.domain.repository.TourPlaceRepository;
import ru.ardyc.travelagency.dto.request.TourPlaceCreateRequest;
import ru.ardyc.travelagency.dto.response.TourPlaceResponse;
import ru.ardyc.travelagency.service.TourPlaceService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaTourPlaceService implements TourPlaceService {

    private final TourPlaceRepository tourPlaceRepository;

    @Override
    public List<TourPlaceResponse> getTourPlaces() {
        return tourPlaceRepository.findAll()
                .stream()
                .map(TourPlaceEntity::toResponse)
                .toList();
    }

    @Override
    public TourPlaceResponse getTourPlace(UUID tourPlaceId) {
        return tourPlaceRepository.findById(tourPlaceId).map(TourPlaceEntity::toResponse).orElse(null);
    }

    @Override
    public TourPlaceResponse createTourPlace(TourPlaceCreateRequest request) {
        var tourPlace = TourPlaceEntity
                .builder()
                .name(request.name())
                .description(request.description())
                .city(request.city())
                .build();
        tourPlaceRepository.save(tourPlace);
        return tourPlace.toResponse();
    }

    @Override
    public TourPlaceResponse removeTourPlace(UUID tourPlaceId) {
        var tourPlace = tourPlaceRepository.findById(tourPlaceId).map(TourPlaceEntity::toResponse).orElse(null);
        tourPlaceRepository.deleteById(tourPlaceId);
        return tourPlace;
    }
}
