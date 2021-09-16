package com.example.reservationSystem.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    long id;
    String email;
    String name;
    String surname;
}
