package com.company.arsproject.entity;

import com.company.arsproject.enums.Role;
import com.company.arsproject.enums.UserStatus;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@Table(name = "users")
@Schema(name = "User(Admin, Agent, Customer)", description = "Tizimdan foydalanuvchi")
public class User extends Auditable {

    @Column(name = "first_name", nullable = false)
    @Parameter(name = "Ismi")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Parameter(name = "Familiyasi")
    private String lastName;

    @Column(unique = true, nullable = false)
    @Parameter(name = "Username")
    private String username;

    @Column(nullable = false)
    @Parameter(name = "Parol")
    private String password;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "district", column = @Column(name = "address_district"))
    })
    @Parameter(name = "Yashash manzili")
    private Address address;

    @Enumerated(EnumType.STRING)
    @Parameter(name = "Holati (ACTIVE, BLOCKED)")
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    @Parameter(name = "Tizimdagi roli (ADMIN, CUSTOMER, AGENT)")
    private Role role;
}
