package com.example.patch.api.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

import java.util.Optional;

public class PatchUserRequest {
    private final String name;
    private final String surname;
    private final Short age;
    @AssertTrue(message = "At least one field is required")
    private final boolean fieldsExists;

    public PatchUserRequest(@Length(max = 64, min = 3) String name, @Length(max = 64, min = 2) String surname, @PositiveOrZero Short age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.fieldsExists = name != null || age != null || surname != null;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getSurname() {
        return Optional.ofNullable(surname);
    }

    public Optional<Short> getAge() {
        return Optional.ofNullable(age);
    }
}