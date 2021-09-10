package com.example.reservationSystem.ticket;

import com.example.reservationSystem.ticket.model.Ticket;
import com.example.reservationSystem.ticket.model.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public List<TicketDto> getAllTicketsDto(){
        return ticketRepository
                .findAll()
                .stream()
                .map(TicketMapper::toTicketDto)
                .collect(Collectors.toList());
    }

    public TicketDto createTicket(TicketDto createTicketDto){
        Ticket newTicket = TicketMapper.toTicket(createTicketDto);
        ticketRepository.save(newTicket);
        return TicketMapper.toTicketDto(newTicket);
    }

    public Ticket getTicket(Long id){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        ticket.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by id " + id));
        return ticket.get();
    }

    public void deleteTicket(Long id){
        ticketRepository.delete(getTicket(id));
    }
}
