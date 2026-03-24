package com.tally.payload.response;


import com.tally.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDto userDto;
}
