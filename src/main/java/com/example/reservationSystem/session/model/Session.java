package com.example.reservationSystem.session.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Session {
    private SessionUser user;
}