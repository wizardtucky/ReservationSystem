package com.example.reservationSystem.user.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping(path = "user/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    // Premission
    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
