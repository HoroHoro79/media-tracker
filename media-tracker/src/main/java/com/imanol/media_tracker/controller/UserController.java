package com.imanol.media_tracker.controller;

import com.imanol.media_tracker.constants.ConstantsRest;
import com.imanol.media_tracker.dto.request.ChangePasswordUserRequest;
import com.imanol.media_tracker.dto.request.CreateUserRequest;
import com.imanol.media_tracker.dto.response.UserResponse;
import com.imanol.media_tracker.facade.UserFacade;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ConstantsRest.BASE_USERS_REST)
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PutMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid ChangePasswordUserRequest request) {
        return new ResponseEntity<>(userFacade.changePassword(request), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        return new ResponseEntity<>(userFacade.createUser(request), HttpStatus.CREATED);
    }


    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userFacade.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(ConstantsRest.GET_BY_ID)
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userFacade.getById(id), HttpStatus.OK);
    }



}
