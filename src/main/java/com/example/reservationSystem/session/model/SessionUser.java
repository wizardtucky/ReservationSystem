package com.example.reservationSystem.session.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionUser {
    private long id;
    private String name;
    private String surname;
    private String email;
}