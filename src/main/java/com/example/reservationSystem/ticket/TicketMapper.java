package com.example.reservationSystem.ticket;

import com.example.reservationSystem.ticket.model.Ticket;
import com.example.reservationSystem.ticket.model.TicketDto;

public class TicketMapper {

    public static Ticket toTicket(TicketDto ticketDto){ // siuntimas i serveri
        return Ticket.builder()
                .time(ticketDto.getTime())
                .test(ticketDto.getTest())
                .build();
    }

    public static TicketDto toTicketDto(Ticket ticket){ // get by id(is serverio)
        return TicketDto.builder()
                .time(ticket.getTime())
                .id(ticket.getId())
                .test(ticket.getTest())
                .build();
    }
}
