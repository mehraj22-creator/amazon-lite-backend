package com.mehraj.amazonlite.controller;

import com.mehraj.amazonlite.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public ApiResponse<String> home() {
        return new ApiResponse<>(
                true,
                "Application Started Successfully",
                "Amazon Lite Backend"
        );
    }

    @GetMapping("/health")
    public ApiResponse<String> health() {
        return new ApiResponse<>(
                true,
                "Application is Healthy",
                "UP"
        );
    }
}