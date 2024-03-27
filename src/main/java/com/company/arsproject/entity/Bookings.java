package com.company.arsproject.entity;

import com.company.arsproject.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Bookings extends Auditable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id")
    private Flights flights;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String place;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
