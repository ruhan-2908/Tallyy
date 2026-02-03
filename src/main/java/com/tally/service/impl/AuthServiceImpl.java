package com.tally.service.impl;

import com.tally.configuration.JWTProvider;
import com.tally.exceptions.UserException;
import com.tally.model.User;
import com.tally.payload.dto.UserDto;
import com.tally.payload.response.AuthResponse;
import com.tally.repository.UserRepository;
import com.tally.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserImplementation customUserImplementation;

    @Override
    public AuthResponse signup(UserDto userDto) throws UserException {
        User user = userRepository.findByEmail(userDto.getEmail());
        if(user != null)
        {
            throw new UserException("User already exists!");
        }

        User newUser = new User();
        newUser.setFullName(userDto.getFullName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPhone(userDto.getPhone());
        newUser.setRole(userDto.getRole());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setCreatedAt(userDto.getCreatedAt());
        newUser.setUpdatedAt(userDto.getUpdatedAt());
        newUser.setLastLogin(userDto.getLastLogin());

        userRepository.save(newUser);

        return null;
    }

    @Override
    public AuthResponse login(UserDto userDto) {
        return null;
    }
}
