package com.imanol.media_tracker.service;


import com.imanol.media_tracker.model.User;
import com.imanol.media_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Guardar el usuario si pasa las validaciones
        return userRepository.save(user);
    }

    public Optional<User> findByUsernameOrEmail(String identifier) {
        Optional<User> byUsername = userRepository.findByUsername(identifier);
        if (byUsername.isPresent()) return byUsername;
        return userRepository.findByEmail(identifier);
    }

    public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
