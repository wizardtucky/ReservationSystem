package com.example.reservationSystem.user;

import com.example.reservationSystem.user.model.CreateUserDto;
import com.example.reservationSystem.user.model.User;
import com.example.reservationSystem.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userService.getUserDto(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by id " + id));
    }

    @GetMapping(path = "/allusers")
    @PreAuthorize("isAuthenticated()")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsersDto();
    }

    @PostMapping
    public UserDto createUser(@RequestBody CreateUserDto user){
        return userService.createUser(user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PostMapping(path = "/login")
    public User loginUser(@RequestBody LoginRequest request){
        return userService.checkUserLogin(request);
    }
}
