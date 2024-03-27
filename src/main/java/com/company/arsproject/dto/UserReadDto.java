package com.company.arsproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReadDto {
    private String firstName;
    private String lastName;
    private String username;
    private String status;
}
