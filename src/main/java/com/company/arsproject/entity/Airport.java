package com.company.arsproject.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Airport extends Auditable {

    private String name;

    @Embedded
    private Address address;
}
