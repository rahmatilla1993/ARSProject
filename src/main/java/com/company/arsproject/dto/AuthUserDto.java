package com.company.arsproject.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class AuthUserDto {
    @NotBlank(message = "Firstname can't be blank")
    @Size(min = 3, max = 20, message = "Length of firstname should be between {min} and {max}")
    @Parameter(name = "Ismi", required = true)
    private String firstName;

    @NotBlank(message = "Lastname can't be blank")
    @Size(min = 4, max = 20, message = "Length of lastname should be between {min} and {max}")
    @Parameter(name = "Familiyasi", required = true)
    private String lastName;

    @NotBlank(message = "Username can't be blank")
    @Parameter(name = "username", required = true)
    private String username;

    @NotBlank(message = "Password can't be blank")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d).{4,}$",
            message = "Length is at least 4 characters, consists of letters and numbers"
    )
    @Parameter(name = "Paroli", required = true)
    private String password;

    @NotBlank(message = "City can't be blank")
    @Parameter(name = "Yashash shahri", required = true)
    private String city;

    @NotBlank(message = "District can't be blank")
    @Parameter(name = "Yashash tumani", required = true)
    private String district;
}
