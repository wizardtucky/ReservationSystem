package com.example.reservationSystem.ticket.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CreateTicketDto {
    long id;
    Date time;
    private String test;
}
