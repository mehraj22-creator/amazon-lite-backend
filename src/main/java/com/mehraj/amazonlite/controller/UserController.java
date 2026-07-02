package com.mehraj.amazonlite.controller;

import com.mehraj.amazonlite.common.ApiResponse;
import com.mehraj.amazonlite.dto.request.RegisterUserRequest;
import com.mehraj.amazonlite.dto.response.ProfileResponse;
import com.mehraj.amazonlite.dto.response.UserResponse;
import com.mehraj.amazonlite.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(
            @Valid @RequestBody RegisterUserRequest request) {

        UserResponse response = userService.register(request);

        return new ApiResponse<>(
                true,
                "User registered successfully",
                response
        );
    }

   @GetMapping("/profile")
    public ApiResponse<ProfileResponse> profile() {

        ProfileResponse response = userService.getCurrentUser();

        return new ApiResponse<>(
                true,
                "Profile fetched successfully",
                response
        );
    }
}