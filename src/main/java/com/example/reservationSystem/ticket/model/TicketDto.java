package com.example.reservationSystem.ticket.model;

import com.example.reservationSystem.user.model.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class TicketDto {
    private long id;
    private Date time;
    private String test;
    private UserDto user;
}
