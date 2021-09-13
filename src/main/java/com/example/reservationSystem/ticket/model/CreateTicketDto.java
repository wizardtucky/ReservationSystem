package com.example.reservationSystem.ticket.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CreateTicketDto {
    private long id;
    private Date time;
    private String test;
    private Boolean isActive;
}
