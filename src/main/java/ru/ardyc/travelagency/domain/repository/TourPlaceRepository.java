package ru.ardyc.travelagency.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ardyc.travelagency.domain.model.TourPlaceEntity;

import java.util.UUID;

@Repository
public interface TourPlaceRepository extends JpaRepository<TourPlaceEntity, UUID> {
}
