package com.imanol.media_tracker.mapper;

import com.imanol.media_tracker.dto.request.CreateUserRequest;
import com.imanol.media_tracker.dto.response.UserResponse;
import com.imanol.media_tracker.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class UserMapper {

    public User createRequestToEntity(@org.jetbrains.annotations.NotNull CreateUserRequest request)
    {
        return User.builder().email(request.getEmail()).username(request.getUsername()).password(request.getPassword())
                .createDate(LocalDateTime.now()).build();
    }

    public UserResponse entityToDto(User user)
    {
        return UserResponse.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail()).locked(user.isAccountLocked()).build();
    }
}
