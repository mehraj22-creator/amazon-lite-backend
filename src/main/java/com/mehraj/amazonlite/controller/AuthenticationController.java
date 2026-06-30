package com.mehraj.amazonlite.controller;

import com.mehraj.amazonlite.common.ApiResponse;
import com.mehraj.amazonlite.dto.request.LoginRequest;
import com.mehraj.amazonlite.dto.response.LoginResponse;
import com.mehraj.amazonlite.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authenticationService.login(request);

        return new ApiResponse<>(
                true,
                "Login successful",
                response
        );
    }

}