package com.tally.service.impl;

import com.tally.configuration.JWTProvider;
import com.tally.exceptions.UserException;
import com.tally.model.User;
import com.tally.repository.UserRepository;
import com.tally.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String token) throws UserException {
        String email = jwtProvider.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);
        if(user == null)
        {
            throw new UserException("User not found!");
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if(user == null)
        {
            throw new UserException("User not found!");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if(user == null)
        {
            throw new UserException("User not found!");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws UserException, Exception {
        User user = userRepository.findById(id).orElseThrow(
                () -> new Exception("User Not Found!")
        );
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
