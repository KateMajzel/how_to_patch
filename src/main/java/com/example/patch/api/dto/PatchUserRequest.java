package com.example.patch.api.dto;

import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

import java.util.Optional;

public record PatchUserRequest(
        Optional<@Length(max = 64, min = 3) String> name,
        Optional<@Length(max = 64, min = 2) String> surname,
        Optional<@PositiveOrZero Short> age) {
}
