package com.imanol.media_tracker.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imanol.media_tracker.constants.ConstantsRest;
import com.imanol.media_tracker.constants.ConstantsSecurity;
import com.imanol.media_tracker.dto.response.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if (isPublicEndpoint(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwtUtils.extractToken(request);

        if (token == null || token.isEmpty()) {
            sendErrorResponse(response,
                    new ErrorResponse("Token no proporcionado", List.of("El token JWT es obligatorio")),
                    HttpServletResponse.SC_UNAUTHORIZED);
            SecurityContextHolder.clearContext();
            return;
        }

        if (!jwtUtils.validateToken(token)) {
            sendErrorResponse(response,
                    new ErrorResponse("Token inválido o expirado", List.of("El token JWT no es válido o ha expirado")),
                    HttpServletResponse.SC_UNAUTHORIZED);
            SecurityContextHolder.clearContext();
            return;
        }

        String username = jwtUtils.getUsernameFromToken(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, ErrorResponse errorResponse, int status) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        String method = request.getMethod();

        // Comprobamos rutas públicas por patrón
        for (String publicPath : ConstantsSecurity.PUBLIC_PATHS) {
            if (pathMatcher.match(publicPath, requestPath)) {
                return true;
            }
        }

        // Comprobaciones explícitas para POST /users y POST /login
        return ((
                (
                        (ConstantsRest.BASE_CONTEXT_PATH + ConstantsRest.BASE_USERS_REST).equals(requestPath) ||
                                (ConstantsRest.BASE_CONTEXT_PATH + ConstantsRest.BASE_LOGIN_REST).equals(requestPath)
                )
                        &&
                        HttpMethod.POST.name().equalsIgnoreCase(method))
                || ((ConstantsRest.BASE_CONTEXT_PATH + ConstantsRest.BASE_USERS_REST).equals(requestPath)
                && HttpMethod.PUT.name().equalsIgnoreCase(method)));
    }
}
