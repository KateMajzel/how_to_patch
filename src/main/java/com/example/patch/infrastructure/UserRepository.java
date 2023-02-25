package com.example.patch.infrastructure;

import com.example.patch.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    public User saveUser(User user) {
        userMap.put(user.userId().toString(), user);
        return user;
    }

    public Optional<User> getUser(String userId) {
        return Optional.ofNullable(userMap.get(userId));
    }
}
