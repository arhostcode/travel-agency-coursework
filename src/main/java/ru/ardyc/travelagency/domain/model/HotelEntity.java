package ru.ardyc.travelagency.domain.model;


import jakarta.persistence.*;
import lombok.*;
import ru.ardyc.travelagency.dto.response.HotelResponse;

import java.util.List;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelEntity that = (HotelEntity) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(city, that.city) && Objects.equals(location, that.location) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(description);
        result = 31 * result + Objects.hashCode(city);
        result = 31 * result + Objects.hashCode(location);
        result = 31 * result + Objects.hashCode(image);
        result = 31 * result + Double.hashCode(price);
        return result;
    }
}
