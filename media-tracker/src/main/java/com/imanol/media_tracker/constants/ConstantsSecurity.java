package com.imanol.media_tracker.constants;

public class ConstantsSecurity {
    // Paths públicos que no dependen del método HTTP
    public static final String[] PUBLIC_PATHS = {
            "/api/media-tracker/swagger-ui/**",
            "/api/media-tracker/v3/api-docs/**",
            "/api/media-tracker/v3/api-docs.yaml",
            "/api/media-tracker/v3/api-docs/swagger-config",
            "/api/media-tracker/webjars/**",
            "/api/media-tracker/favicon.ico"
    };
}


