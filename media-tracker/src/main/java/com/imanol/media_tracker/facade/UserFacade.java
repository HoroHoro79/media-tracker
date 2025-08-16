package com.imanol.media_tracker.facade;


import com.imanol.media_tracker.dto.request.CreateUserRequest;
import com.imanol.media_tracker.dto.response.UserResponse;
import com.imanol.media_tracker.mapper.UserMapper;
import com.imanol.media_tracker.model.User;
import com.imanol.media_tracker.exception.UserAlreadyExistsException;
import com.imanol.media_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
