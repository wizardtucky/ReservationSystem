package com.example.reservationSystem.user;

import com.example.reservationSystem.user.model.CreateUserDto;
import com.example.reservationSystem.user.model.User;
import com.example.reservationSystem.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserDto> getUserDto(Long id){
        return userRepository.findById(id).map(UserMapper :: toUserDto);
    }

    public User getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by id " + id));
        return user.get();
    }

    public List<UserDto> getAllUsersDto(){
        return userRepository
                .findAll()
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }


    public UserDto createUser(CreateUserDto createUserDto){
        User newUser = UserMapper.toUser(createUserDto);
        userRepository.save(newUser);
        return UserMapper.toUserDto(newUser);
    }

    public void deleteUser(Long id){
        userRepository.delete(getUser(id));
    }
}
