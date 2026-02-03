package com.tally.service;

import com.tally.exceptions.UserException;
import com.tally.payload.dto.UserDto;
import com.tally.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse signup (UserDto userDto) throws UserException;
    AuthResponse login (UserDto userDto);
}
