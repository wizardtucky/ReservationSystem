package com.example.reservationSystem.session;

import com.example.reservationSystem.session.model.LoginData;
import com.example.reservationSystem.session.model.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    public Session getSession() {
        return sessionService.getSession();
    }

    @PostMapping
    public Session createSession(HttpServletRequest httpServletRequest, @RequestBody LoginData sessionRequest) {
        return sessionService.createSession(
                httpServletRequest,
                sessionRequest.getEmail(),
                sessionRequest.getPassword()
        );
    }

    @DeleteMapping
    public void deleteSession(HttpServletRequest httpServletRequest) {
        sessionService.deleteSession(httpServletRequest);
    }
}