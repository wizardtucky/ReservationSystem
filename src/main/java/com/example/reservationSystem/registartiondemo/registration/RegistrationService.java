package com.example.reservationSystem.registartiondemo.registration;

import com.example.reservationSystem.registartiondemo.appuser.AppUser;
import com.example.reservationSystem.registartiondemo.appuser.AppUserService;
import com.example.reservationSystem.registartiondemo.appuser.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(new AppUser(
                request.getEmail(),
                request.getName(),
                request.getSurname(),
                request.getPassword(),
                UserRole.USER
        ));
    }
}
