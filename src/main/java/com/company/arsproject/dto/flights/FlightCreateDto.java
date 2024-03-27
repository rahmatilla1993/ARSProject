package com.company.arsproject.dto.flights;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class FlightCreateDto {

    @NotBlank(message = "Flight number can't be blank")
    @Parameter(name = "Reys nomeri")
    private String number;

    @NotBlank(message = "Plane can't be blank")
    @Parameter(name = "Reys amalga oshiriladigan samolyot")
    private String plane;

    @NotBlank(message = "Town from number can't be blank")
    @Parameter(name = "Qayerdan")
    private String townFrom;

    @NotBlank(message = "Town to number can't be blank")
    @Parameter(name = "Qayerga")
    private String townTo;

    @Parameter(name = "Reys chipta narxi")
    @Min(value = 0, message = "Reys chipta narxi manfiy bo'lolmaydi")
    private double price;

    @Parameter(name = "Reys chiptalar soni")
    @Min(value = 0, message = "Reys chiptalar soni manfiy bo'lolmaydi")
    private int ticketCount;

    @NotBlank(message = "Flight time can't be blank")
    @Parameter(name = "Uchish vaqti", example = "dd-MM-yyyy HH:mm")
    @Pattern(
            regexp = "b(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(d{4}) (?:[01]d|2[0123]):[012345]db",
            message = "Flight time pattern should be dd-MM-yyyy hh:mm"
    )
    private String flight_time;

    @Parameter(name = "Reysni amalga oshiradigan kompaniya id si")
    @Min(value = 1, message = "Company id should be non negative")
    private Long companyId;

    @Parameter(name = "Airport id si")
    @Min(value = 1, message = "Airport id should be non negative")
    private Long airportId;
}
