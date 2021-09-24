package com.example.reservationSystem.session.model;

import com.example.reservationSystem.user.model.UserRole;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
public class SessionUser {
    private long id;
    private String name;
    private String surname;
    private String email;
    @Enumerated(EnumType.STRING)
    UserRole userRole;
}