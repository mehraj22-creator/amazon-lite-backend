package com.mehraj.amazonlite.service.impl;

import com.mehraj.amazonlite.dto.request.LoginRequest;
import com.mehraj.amazonlite.dto.response.LoginResponse;
import com.mehraj.amazonlite.entity.User;
import com.mehraj.amazonlite.exception.ResourceNotFoundException;
import com.mehraj.amazonlite.repository.UserRepository;
import com.mehraj.amazonlite.security.JwtService;
import com.mehraj.amazonlite.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return LoginResponse.builder()
                .token(token)
                .build();
    }
}