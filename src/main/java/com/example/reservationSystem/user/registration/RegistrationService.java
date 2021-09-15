package com.example.reservationSystem.user.registration;

import com.example.reservationSystem.user.UserService;
import com.example.reservationSystem.user.model.User;
import com.example.reservationSystem.user.model.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        return userService.signUpUser(new User(
                request.getEmail(),
                request.getName(),
                request.getSurname(),
                request.getPassword(),
                UserRole.USER
        ));
    }
}
