package com.example.reservationSystem.security;

import com.example.reservationSystem.security.model.CustomUserDetails;
import com.example.reservationSystem.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).map(user -> new CustomUserDetails(
                user.getEmail(),
                user.getPassword(),
                List.of("ADMIN", "USER"))
        )
                .orElseThrow(() -> new UsernameNotFoundException("User was not found by email = " + email));
    }

}