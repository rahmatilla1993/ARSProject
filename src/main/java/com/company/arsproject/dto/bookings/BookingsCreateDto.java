package com.company.arsproject.dto.bookings;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class BookingsCreateDto {

    @Parameter(name = "Reysni id si", required = true)
    @Min(value = 1, message = "Reys nomeri manfiy qiymat bo'lolmaydi")
    private long flightId;

    @Parameter(name = "Buyurtma qilingan o'rin", required = true)
    private String place;
}
