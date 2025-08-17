package com.imanol.media_tracker.facade;


import com.imanol.media_tracker.dto.request.ChangePasswordUserRequest;
import com.imanol.media_tracker.dto.request.CreateUserRequest;
import com.imanol.media_tracker.dto.response.UserResponse;
import com.imanol.media_tracker.exception.ObjectNotExistsException;
import com.imanol.media_tracker.mapper.UserMapper;
import com.imanol.media_tracker.model.User;
import com.imanol.media_tracker.exception.UserAlreadyExistsException;
import com.imanol.media_tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserResponse createUser(CreateUserRequest request) {
        // Validaciones
        if (userService.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (userService.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User savedUser = userService.createUser(userMapper.createRequestToEntity(request));
        return userMapper.UserToDto(savedUser);

    }

    public List<UserResponse> getAllUsers() {
        List<User> users =  userService.findAll();
        return users.stream().map(userMapper::UserToDto).toList();
    }

    public UserResponse getById(Long id) {
        User user = userService.findById(id);
        return userMapper.UserToDto(user);
    }

    public UserResponse changePassword(@Valid ChangePasswordUserRequest request) {
        Optional<User> user = userService.findByUsernameOrEmail(request.getUsername());
        if(user.isPresent()){
            user.get().setPassword(request.getPassword());
          return  userMapper.UserToDto(userService.update(user.get()));
        }
        throw new ObjectNotExistsException("User not exists");
    }
}
