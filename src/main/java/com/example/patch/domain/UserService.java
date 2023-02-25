package com.example.patch.domain;

import com.example.patch.infrastructure.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Component
public class UserService {
    private final UserRepository repo;
    private final Supplier<String> uuidSupplier = () -> UUID.randomUUID().toString();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User addUser(String userName, String surname, Short age) {
        var newUser = new User(uuidSupplier.get(), userName, surname, age);
        return repo.saveUser(newUser);
    }

    public Optional<User> updateUser(
            String userId,
            Optional<String> userNameUpdate,
            Optional<String> surnameUpdate,
            Optional<Short> ageUpdate
    ) {
        return repo.getUser(userId)
                .map(oldUser ->
                        new User(userId,
                                userNameUpdate.orElseGet(oldUser::name),
                                surnameUpdate.orElseGet(oldUser::surname),
                                ageUpdate.orElseGet(oldUser::age)
                        )
                )
                .map(repo::saveUser);
    }

    public Optional<User> getUser(
            String userId
    ) {
        return repo.getUser(userId);
    }

}
