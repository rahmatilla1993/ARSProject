package com.company.arsproject.dto.flights;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightReadDto {

    private String number;
    private String plane;
    private String townFrom;
    private String townTo;
    private double price;
    private String flight_time;
    private String company;
    private String airport;
    private int ticket_count;
}
