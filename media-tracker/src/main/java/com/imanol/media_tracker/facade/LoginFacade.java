package com.imanol.media_tracker.facade;

import com.imanol.media_tracker.dto.request.LoginRequest;
import com.imanol.media_tracker.dto.response.LoginResponse;
import com.imanol.media_tracker.exception.CustomLoginException;
import com.imanol.media_tracker.model.User;
import com.imanol.media_tracker.security.JwtUtils;
import com.imanol.media_tracker.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginFacade {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse generateToken(LoginRequest request) throws CustomLoginException {
        User user = userService.findByUsernameOrEmail(request.getUser())
                .orElse(null);

        // Usuario no existe → simulación para timing attack
        if (user == null) {
            passwordEncoder.matches(request.getPassword(), "$2a$10$abcdefghijklmnopqrstuv");
            throw new CustomLoginException("Usuario o contraseña inválidos.");
        }

        // Cuenta bloqueada
        if (user.isAccountLocked()) {
            throw new CustomLoginException("Usuario bloqueado, actualice la contraseña");
        }

        // Verificar contraseña
        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordMatches) {
            // Incrementar intentos fallidos
            int attempts = user.getFailedAttempts() + 1;
            user.setFailedAttempts(attempts);

            // Bloquear cuenta si alcanza 3 intentos
            if (attempts >= 3) {
                user.setAccountLocked(true);
            }

            userService.update(user);
            throw new CustomLoginException("Usuario o contraseña inválidos.");
        }

        // Login correcto → resetear intentos
        user.setFailedAttempts(0);
        userService.update(user);

        // Generar tokens JWT
        String token = jwtUtils.generateAccessToken(user.getUsername());
        String refreshToken = jwtUtils.generateRefreshToken(user.getUsername());

        return LoginResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    public LoginResponse generateTokenByRefreshToken(HttpServletRequest request) {
        String refreshToken = jwtUtils.extractToken(request);
        String accesToken = jwtUtils.generateAccessToken(jwtUtils.getUsernameFromToken(refreshToken));
        return LoginResponse.builder()
                .accessToken(accesToken)
                .refreshToken(refreshToken)
                .build();
    }
}
