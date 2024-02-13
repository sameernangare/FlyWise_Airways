package com.example.airline_reservation.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer numberOfSeatsBooked;
    @Column(columnDefinition = "BOOLEAN")
    boolean paymentStatus;
    LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    SeatClass type;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    Flight flight;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "source_stop_number", referencedColumnName = "stopNumber", nullable = false),
            @JoinColumn(name = "source_flight_id", referencedColumnName = "flight_id", nullable = false)
    })
    Stop source;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "destination_stop_number", referencedColumnName = "stopNumber", nullable = false),
            @JoinColumn(name = "destination_flight_id", referencedColumnName = "flight_id", nullable = false)
    })
    Stop destination;

    @OneToOne
    @JoinColumn(name = "payment_id")
    Payment payment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    List<Passenger> passengers;
}
