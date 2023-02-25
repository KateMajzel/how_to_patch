package com.example.patch.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

public record CreateUserRequest(
        @NotNull
        @Length(max = 64, min = 3) String name,
        @NotNull
        @Length(max = 64, min = 2)
        String surname,
        @PositiveOrZero
        @NotNull
        Short age) {
}
