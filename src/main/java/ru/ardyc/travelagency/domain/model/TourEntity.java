package ru.ardyc.travelagency.domain.model;

import jakarta.persistence.*;
import lombok.*;
import ru.ardyc.travelagency.dto.response.TourResponse;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tour")
public class TourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;
    private String description;
    private OffsetDateTime startDate;
    private OffsetDateTime finishDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_place")
    private TourPlaceEntity tourPlace;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel")
    private HotelEntity hotel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight")
    private FlightEntity flight;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tours")
    private List<UserEntity> users;

    public TourResponse toResponse() {
        return TourResponse.builder()
                .id(id)
                .name(name)
                .description(description)
                .startDate(startDate)
                .finishDate(finishDate)
                .tourPlace(tourPlace.toResponse())
                .hotel(hotel.toResponse())
                .flight(flight.toResponse())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourEntity that = (TourEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(startDate, that.startDate) && Objects.equals(finishDate, that.finishDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(description);
        result = 31 * result + Objects.hashCode(startDate);
        result = 31 * result + Objects.hashCode(finishDate);
        return result;
    }
}
