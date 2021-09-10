package com.example.reservationSystem.user;

import com.example.reservationSystem.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);

    /*
    @Query(
            "SELECT u from User u " +
                    "LEFT JOIN Initiative i ON i.id = :initiative_id " +
                    "JOIN i.candidate c ON c.id = u.id ")
    List<User> getCandidatesFromInitiative(@Param("initiative_id") long initiativeID);

     */
}
