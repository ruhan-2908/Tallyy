package com.tally.mapper;

import com.tally.model.User;
import com.tally.payload.dto.UserDto;

import java.time.LocalDateTime;

public class UserMapper {
    public static UserDto toDTO(User savedUser) {
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setFullName(savedUser.getFullName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setPhone(savedUser.getPhone());
        userDto.setRole(savedUser.getRole());
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setUpdatedAt(LocalDateTime.now());
        userDto.setLastLogin(LocalDateTime.now());
        return userDto;
    }
}
