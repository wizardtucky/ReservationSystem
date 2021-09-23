package com.example.reservationSystem.ticket;

import com.example.reservationSystem.ticket.model.Ticket;
import com.example.reservationSystem.ticket.model.TicketDto;
import com.example.reservationSystem.user.UserMapper;

public class TicketMapper {

    public static Ticket toTicket(TicketDto ticketDto){ // siuntimas i serveri
        return Ticket.builder()
                .time(ticketDto.getTime())
                .test(ticketDto.getTest())
                .build();
    }

    public static TicketDto toTicketDto(Ticket ticket){ // get by id(is serverio) Data to Object
        return TicketDto.builder()
                .time(ticket.getTime())
                .id(ticket.getId())
                .test(ticket.getTest())
                .user(UserMapper.toUserDto(ticket.getUser()))
                .isActive(ticket.getIsActive())
                .build();
    }
}
