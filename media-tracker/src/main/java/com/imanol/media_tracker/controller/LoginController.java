package com.imanol.media_tracker.controller;

import com.imanol.media_tracker.constants.ConstantsRest;
import com.imanol.media_tracker.dto.request.LoginRequest;
import com.imanol.media_tracker.dto.response.LoginResponse;
import com.imanol.media_tracker.facade.LoginFacade;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping(ConstantsRest.BASE_LOGIN_REST)
@RequiredArgsConstructor
public class LoginController {

    private final LoginFacade loginFacade;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws LoginException {
        return new ResponseEntity<>( loginFacade.generateToken(loginRequest), HttpStatus.CREATED);
    }


    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(ConstantsRest.LOGIN_REFRESH)
    public ResponseEntity<LoginResponse> refreshAccessToken(HttpServletRequest request) {
        return new ResponseEntity<>( loginFacade.generateTokenByRefreshToken(request), HttpStatus.CREATED);
    }


}
