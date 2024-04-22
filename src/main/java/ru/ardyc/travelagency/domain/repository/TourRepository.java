package ru.ardyc.travelagency.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ardyc.travelagency.domain.model.TourEntity;

import java.util.UUID;

@Repository
public interface TourRepository extends JpaRepository<TourEntity, UUID> {
}
