package com.example.reservationSystem.ticket;

import com.example.reservationSystem.ticket.model.Ticket;
import com.example.reservationSystem.ticket.model.TicketDto;
import com.example.reservationSystem.user.UserMapper;
import com.example.reservationSystem.user.UserService;
import com.example.reservationSystem.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final UserService userService;
    private final TicketRepository ticketRepository;

    public Optional<TicketDto> getTicketDto(Long id){
        return ticketRepository.findById(id).map(TicketMapper:: toTicketDto);
    }

    public List<TicketDto> getAllTicketsDto(){
        return ticketRepository
                .findAll()
                .stream()
                .map(TicketMapper::toTicketDto)
                .collect(Collectors.toList());
    }

    public List<TicketDto> getTicketsByUser(Long id){
        return ticketRepository
                .findAllByUser(userService.getUser(id))
                .stream()
                .map(TicketMapper::toTicketDto)
                .collect(Collectors.toList());
    }

    public TicketDto createTicket(Long userId){
        Ticket newTicket = Ticket.builder()
                .time(new Date())
                .test("test")
                .user(userService.getUser(userId))
                .build();

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

    public Optional<TicketDto> getTicketDto(Long id){
        return ticketRepository.findById(id).map(TicketMapper:: toTicketDto);
    }
}
