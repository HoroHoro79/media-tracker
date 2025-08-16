package com.imanol.media_tracker.controller;

import com.imanol.media_tracker.dto.request.CreateUserRequest;
import com.imanol.media_tracker.dto.response.UserResponse;
import com.imanol.media_tracker.facade.UserFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        UserResponse createdUser = userFacade.createUser(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
