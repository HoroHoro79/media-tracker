package com.imanol.media_tracker.controller;

import com.imanol.media_tracker.constants.ConstantsRest;
import com.imanol.media_tracker.dto.request.LoginRequest;
import com.imanol.media_tracker.dto.response.LoginResponse;
import com.imanol.media_tracker.facade.LoginFacade;
import com.imanol.media_tracker.model.User;
import com.imanol.media_tracker.service.UserService;
import com.imanol.media_tracker.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
}
