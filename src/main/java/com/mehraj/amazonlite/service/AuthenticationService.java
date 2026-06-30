package com.mehraj.amazonlite.service;

import com.mehraj.amazonlite.dto.request.LoginRequest;
import com.mehraj.amazonlite.dto.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);

}