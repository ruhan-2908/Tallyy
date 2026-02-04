package com.tally.controller;


import com.tally.exceptions.UserException;
import com.tally.payload.dto.UserDto;
import com.tally.payload.response.AuthResponse;
import com.tally.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    // -> /auth/signup
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(@RequestBody UserDto userDto) throws UserException {
        return ResponseEntity.status(201).body(authService.signup(userDto));
    }

    // -> /auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody UserDto userDto) throws UserException {
        return ResponseEntity.ok(authService.login(userDto));
    }
}
