package com.imanol.media_tracker.constants;

public class ConstantsSecurity {
    // Paths públicos que no dependen del método HTTP
    public static final String[] PUBLIC_PATHS = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/v3/api-docs/swagger-config",
            "/webjars/**",
            "/favicon.ico"
    };
}
