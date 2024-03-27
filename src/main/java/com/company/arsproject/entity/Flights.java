package com.company.arsproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Flights extends Auditable {

    @Column(nullable = false)
    private String number;

    private String plane;
    @Column(name = "town_from")
    private String townFrom;
    @Column(name = "town_to")
    private String townTo;
    private double price;
    @Column(name = "ticket_count", columnDefinition = "int default 1")
    private int ticketCount;

    private LocalDateTime flightTime;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;
}
