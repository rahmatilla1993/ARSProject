package com.company.arsproject.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
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
public class AirportDto {

    @Parameter(name = "Airport nomi", required = true)
    @NotBlank(message = "Airport name can't be blank")
    private String name;

    @Parameter(name = "Airport joylashgan shahar", required = true)
    @NotBlank(message = "AddressCity can't be blank")
    private String addressCity;

    @Parameter(name = "Airport joylashgan hudud", required = true)
    @NotBlank(message = "AddressDistrict can't be blank")
    private String addressDistrict;
}
