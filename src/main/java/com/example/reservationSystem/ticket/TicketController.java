package com.example.reservationSystem.ticket;


import com.example.reservationSystem.ticket.model.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping(path = "{id}")
    public TicketDto getTicket(@PathVariable Long id){

        return ticketService.getTicketDto(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found by id " + id));
    }

    @GetMapping
    public List<TicketDto> getAllTickets(){

        return ticketService.getAllTicketsDto();
    }

    @PostMapping(path = "/{id}")
    public TicketDto createTicket(@PathVariable Long id){

        return ticketService.createTicket(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTicket(@PathVariable Long id){ ticketService.deleteTicket(id);
    }

    @PutMapping(path = "/{id}/{isActive}")
    public void changeActiveStatus(@PathVariable Long id, @PathVariable Boolean isActive){
        ticketService.changeTicketStatus(id, isActive);
    }

    @GetMapping(path = "/user/{id}")
    public List<TicketDto> getTicketsByUser(@PathVariable Long id){
        return ticketService.getTicketsByUser(id);
    }

}
