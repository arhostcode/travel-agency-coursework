package ru.ardyc.travelagency.domain.model;


import jakarta.persistence.*;
import lombok.*;
import ru.ardyc.travelagency.dto.response.HotelResponse;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "hotel")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;
    private String description;
    private String city;
    private String location;
    private String image;
    private double price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "hotels")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<TourEntity> tours;

    public HotelResponse toResponse() {
        return HotelResponse.builder()
                .id(id)
                .name(name)
                .description(description)
                .city(city)
                .location(location)
                .image(image)
                .price(price)
                .build();
    }
}
