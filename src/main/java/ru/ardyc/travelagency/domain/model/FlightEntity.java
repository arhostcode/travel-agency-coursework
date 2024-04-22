package ru.ardyc.travelagency.domain.model;

import jakarta.persistence.*;
import lombok.*;
import ru.ardyc.travelagency.dto.response.FlightResponse;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "flight")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "from_place")
    private String from;

    @Column(name = "to_place")
    private String to;

    private String name;
    private OffsetDateTime startDate;
    private OffsetDateTime finishDate;
    private double price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "flights")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private List<TourEntity> tours;

    public FlightResponse toResponse() {
        return FlightResponse.builder()
                .id(id)
                .name(name)
                .from(from)
                .to(to)
                .startDate(startDate)
                .finishDate(finishDate)
                .price(price)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightEntity that = (FlightEntity) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(id, that.id) && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(name, that.name) && Objects.equals(startDate, that.startDate) && Objects.equals(finishDate, that.finishDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(from);
        result = 31 * result + Objects.hashCode(to);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(startDate);
        result = 31 * result + Objects.hashCode(finishDate);
        result = 31 * result + Double.hashCode(price);
        return result;
    }
}





