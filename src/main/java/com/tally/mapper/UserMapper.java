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
        userDto.setBranchId(savedUser.getBranch()!=null ? savedUser.getBranch().getId():null);
        userDto.setStoreId(savedUser.getStore()!=null ? savedUser.getStore().getId():null);
        return userDto;
    }
    public static User toEntity(UserDto userDto)
    {
        User createdUser = new User();
        createdUser.setEmail(userDto.getEmail());
        createdUser.setRole(userDto.getRole());
        createdUser.setFullName(userDto.getFullName());
        createdUser.setCreatedAt(userDto.getCreatedAt());
        createdUser.setUpdatedAt(userDto.getUpdatedAt());
        createdUser.setLastLogin(userDto.getLastLogin());
        createdUser.setPassword(userDto.getPassword());
        createdUser.setPhone(userDto.getPhone());
        return createdUser;
    }
}
