package com.imanol.media_tracker.facade;

import com.imanol.media_tracker.dto.request.LoginRequest;
import com.imanol.media_tracker.dto.response.LoginResponse;
import com.imanol.media_tracker.model.User;
import com.imanol.media_tracker.security.JwtUtils;
import com.imanol.media_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;

@Component
@RequiredArgsConstructor
public class LoginFacade {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse generateToken(LoginRequest request) throws LoginException {

        User user = userService.findByUsernameOrEmail(request.getUser())
                .orElse(null);

        boolean passwordMatches = false;
        if (user != null) {
            passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        } else {
            passwordEncoder.matches(request.getPassword(), "$2a$10$abcdefghijklmnopqrstuv");
        }

        if (user == null || !passwordMatches) {
            throw new LoginException("Usuario o contraseña inválidos.");
        }

        // Generamos token JWT
        String token = jwtUtils.generateToken(user.getUsername());
        return LoginResponse.builder().token(token).build();
    }
}
