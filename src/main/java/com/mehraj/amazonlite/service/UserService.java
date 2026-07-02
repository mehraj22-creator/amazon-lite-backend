package com.mehraj.amazonlite.service;

import com.mehraj.amazonlite.dto.request.RegisterUserRequest;
import com.mehraj.amazonlite.dto.response.ProfileResponse;
import com.mehraj.amazonlite.dto.response.UserResponse;

public interface UserService {

    UserResponse register(RegisterUserRequest request);
    ProfileResponse getCurrentUser();

}