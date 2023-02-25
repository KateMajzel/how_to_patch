package com.example.patch.api;

import com.example.patch.api.dto.CreateUserRequest;
import com.example.patch.api.dto.PatchUserRequest;
import com.example.patch.api.dto.UserResponseDto;
import com.example.patch.domain.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@ResponseBody
@RequestMapping("/users")
class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody CreateUserRequest request, UriComponentsBuilder builder) {
        var user = userService.addUser(request.name(), request.surname(), request.age());
        return ResponseEntity.created(
                        builder.path(user.userId())
                                .build()
                                .toUri()
                )
                .body(UserResponseDto.fromDomain(user));
    }

    @GetMapping("/{id}")
    ResponseEntity<UserResponseDto> getUser(@PathVariable String id) {
        return ResponseEntity.of(userService.getUser(id).map(UserResponseDto::fromDomain));
    }

    @PatchMapping("/{id}")
    ResponseEntity<UserResponseDto> updateUser(@PathVariable String id, @Valid @RequestBody PatchUserRequest request) {
        return ResponseEntity.of(userService.updateUser(id, request.getName(), request.getSurname(), request.getAge())
                .map(UserResponseDto::fromDomain));
    }
}
