package com.imanol.media_tracker.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/ping")
    public String ping() {
        return "pong";

    }
}
