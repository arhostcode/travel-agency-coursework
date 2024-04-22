package ru.ardyc.travelagency.domain.model;

import jakarta.persistence.*;
import lombok.*;
import ru.ardyc.travelagency.dto.response.FlightResponse;

import java.time.OffsetDateTime;
import java.util.List;
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
                .from(from)
                .to(to)
                .startDate(startDate)
                .finishDate(finishDate)
                .price(price)
                .build();
    }

}





