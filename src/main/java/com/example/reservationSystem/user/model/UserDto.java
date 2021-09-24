package com.example.reservationSystem.user.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
public class UserDto {
    private Long id;
    String email;
    String name;
    String surname;
    @Enumerated(EnumType.STRING)
    UserRole userRole;
}
