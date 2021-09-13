package com.example.reservationSystem.ticket;

import com.example.reservationSystem.ticket.model.Ticket;
import com.example.reservationSystem.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
        @Override
        Optional<Ticket> findById(Long aLong);

        List<Ticket> findAllByUser(User user);
}
