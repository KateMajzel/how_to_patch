package com.example.patch.api.dto;

import com.example.patch.domain.User;

public record UserResponseDto(String id, String name, String surname, boolean isAdult) {
    public static UserResponseDto fromDomain(User user) {
        return new UserResponseDto(user.userId(), user.name(), user.surname(), user.age() >= 18);
    }
}
