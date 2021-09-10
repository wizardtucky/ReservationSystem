package com.example.reservationSystem.ticket;


import com.example.reservationSystem.ticket.model.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping(path = "{id}")
    public TicketDto getTicket(@PathVariable Long id){

        return null;
    }

    @GetMapping
    public List<TicketDto> getAllTickets(){

        return ticketService.getAllTicketsDto();
    }

    @PostMapping
    public TicketDto createTicket(@RequestBody TicketDto ticketDto){

        return ticketService.createTicket(ticketDto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTicket(@PathVariable Long id){ ticketService.deleteTicket(id);
    }
}
