package com.example.reservationSystem.user;

import com.example.reservationSystem.user.model.CreateUserDto;
import com.example.reservationSystem.user.model.User;
import com.example.reservationSystem.user.model.UserDto;

public class UserMapper {

    public static User toUser(CreateUserDto createUserDto){ // siuntimas i serveri
        return User.builder()
                .email(createUserDto.getEmail())
                .name(createUserDto.getName())
                .surname(createUserDto.getSurname())
                .password(createUserDto.getPassword())
                .build();
    }

    public static UserDto toUserDto(User user){ // get by id(is serverio)
        return UserDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }
}
