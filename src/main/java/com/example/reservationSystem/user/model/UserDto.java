package com.example.reservationSystem.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    long id;
    private String email;
    private String name;
    private String surname;
    private String password;
}
