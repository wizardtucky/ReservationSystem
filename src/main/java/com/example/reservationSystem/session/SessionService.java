package com.example.reservationSystem.session;

import com.example.reservationSystem.security.SecurityContextService;
import com.example.reservationSystem.session.model.Session;
import com.example.reservationSystem.session.model.SessionUser;
import com.example.reservationSystem.user.UserRepository;
import com.example.reservationSystem.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SecurityContextService securityContextService;
    private final UserRepository userRepository;

    public Session getSession() {
        return Session.builder()
                .user(getSessionUser())
                .build();
    }

    public Session createSession(HttpServletRequest httpServletRequest, String username, String password) {
        securityContextService.createSession(httpServletRequest, username, password);
        return Session.builder()
                .user(getSessionUser())
                .build();
    }

    public void deleteSession(HttpServletRequest httpServletRequest) {
        securityContextService.deleteSession(httpServletRequest);
    }

    public SessionUser getSessionUser() {
        if(!Optional.ofNullable(securityContextService.getCurrentUser()).isPresent()){
            return null;
        }

        Optional<User> user = userRepository.findByEmail(securityContextService.getCurrentUser().getUsername());
        return Optional.ofNullable(securityContextService.getCurrentUser())
                .map(userDetails -> SessionUser.builder()
                        .id(user.get().getId())
                        .name(user.get().getName())
                        .surname(user.get().getSurname())
                        .email(user.get().getEmail())
                        .build())
                .orElse(null);
    }

}