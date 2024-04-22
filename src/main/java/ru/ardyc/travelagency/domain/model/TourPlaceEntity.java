package ru.ardyc.travelagency.domain.model;


import jakarta.persistence.*;
import lombok.*;
import ru.ardyc.travelagency.dto.response.TourPlaceResponse;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "tour_place")
public class TourPlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;
    private String description;
    private String city;

    @OneToMany(mappedBy = "tourPlace", fetch = FetchType.LAZY)
    private List<TourEntity> tours;

    public TourPlaceResponse toResponse() {
        return TourPlaceResponse.builder()
                .id(id)
                .name(name)
                .description(description)
                .city(city)
                .build();
    }
}
