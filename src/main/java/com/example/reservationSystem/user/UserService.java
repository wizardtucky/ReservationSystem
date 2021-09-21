package com.example.reservationSystem.user;

import com.example.reservationSystem.user.model.CreateUserDto;
import com.example.reservationSystem.user.model.User;
import com.example.reservationSystem.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public Optional<UserDto> getUserDto(Long id) {
        return userRepository.findById(id).map(UserMapper::toUserDto);
    }

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by id " + id));
        return user.get();
    }

    public List<UserDto> getAllUsersDto() {
        return userRepository
                .findAll()
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }


    public UserDto createUser(CreateUserDto createUserDto) {
        User newUser = UserMapper.toUser(createUserDto);
        userRepository.save(newUser);
        return UserMapper.toUserDto(newUser);
    }

    public void deleteUser(Long id) {
        userRepository.delete(getUser(id));
    }

    public User checkUserLogin(LoginRequest request){
        Optional<User> foundUser = userRepository.findByEmail(request.getEmail());
        foundUser.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Login details are false "));

        boolean doPasswordsMatch = bCryptPasswordEncoder.matches(request.getPassword(), foundUser.get().getPassword());

        if(doPasswordsMatch) {
            System.out.println("works");
            foundUser.get().setPassword("Secret");
            return foundUser.get();
        }

        System.out.println("wrong pass: "+ request.getPassword());
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        //TODO: send confirmation token maybe

        return "works!";
    }
}
