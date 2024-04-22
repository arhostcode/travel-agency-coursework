package ru.ardyc.travelagency.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ardyc.travelagency.dto.response.UserResponse;

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
    private List<HotelEntity> hotels;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_flight",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id"))
    private List<FlightEntity> flights;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_tour",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private List<TourEntity> tours;

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
                .email(email);
    }

    public void bookHotel(HotelEntity hotel) {
        hotels.add(hotel);
        hotel.getUsers().add(this);
    }

    public void cancelHotel(HotelEntity hotel) {
        hotels.remove(hotel);
        hotel.getUsers().remove(this);
    }

    public void bookFlight(FlightEntity flight) {
        flights.add(flight);
        flight.getUsers().add(this);
    }

    public void cancelFlight(FlightEntity flight) {
        flights.remove(flight);
        flight.getUsers().remove(this);
    }

    public void bookTour(TourEntity tour) {
        tour.getUsers().add(this);
        tours.add(tour);
        bookHotel(tour.getHotel());
        bookFlight(tour.getFlight());
    }

    public void cancelTour(TourEntity tour) {
        tour.getUsers().remove(this);
        tours.remove(tour);
        cancelHotel(tour.getHotel());
        cancelFlight(tour.getFlight());
    }
}