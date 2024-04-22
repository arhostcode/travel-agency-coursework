package ru.ardyc.travelagency.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ardyc.travelagency.dto.response.UserResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_entity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_hotel",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id"))
    private List<HotelEntity> hotels = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_flight",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id"))
    private List<FlightEntity> flights = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_tour",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private List<TourEntity> tours = new ArrayList<>();

    private boolean isAdmin = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return isAdmin ? List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
        ) : List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserResponse.UserResponseBuilder toResponse() {
        return UserResponse.builder()
                .id(id)
                .fullName(fullName)
                .hotels(hotels.stream().map(HotelEntity::toResponse).toList())
                .flights(flights.stream().map(FlightEntity::toResponse).toList())
                .tours(tours.stream().map(TourEntity::toResponse).toList())
                .email(email);
    }

    public void bookHotel(HotelEntity hotel) {
        if (hotels.contains(hotel)) {
            return;
        }
        hotels.add(hotel);
        hotel.getUsers().add(this);
    }

    public void cancelHotel(HotelEntity hotel) {
        if (!hotels.contains(hotel)) {
            return;
        }
        hotels.remove(hotel);
        hotel.getUsers().remove(this);
    }

    public void bookFlight(FlightEntity flight) {
        if (flights.contains(flight)) {
            return;
        }
        flights.add(flight);
        flight.getUsers().add(this);
    }

    public void cancelFlight(FlightEntity flight) {
        if (!flights.contains(flight)) {
            return;
        }
        flights.remove(flight);
        flight.getUsers().remove(this);
    }

    public void bookTour(TourEntity tour) {
        if (tours.contains(tour)) {
            return;
        }
        tour.getUsers().add(this);
        tours.add(tour);
        bookHotel(tour.getHotel());
        bookFlight(tour.getFlight());
    }

    public void cancelTour(TourEntity tour) {
        if (!tours.contains(tour)) {
            return;
        }
        tour.getUsers().remove(this);
        tours.remove(tour);
        cancelHotel(tour.getHotel());
        cancelFlight(tour.getFlight());
    }
}