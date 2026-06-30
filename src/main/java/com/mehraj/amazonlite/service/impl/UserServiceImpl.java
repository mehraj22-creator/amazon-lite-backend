package com.mehraj.amazonlite.service.impl;

import com.mehraj.amazonlite.dto.request.RegisterUserRequest;
import com.mehraj.amazonlite.dto.response.UserResponse;
import com.mehraj.amazonlite.entity.User;
import com.mehraj.amazonlite.exception.DuplicateResourceException;
import com.mehraj.amazonlite.repository.UserRepository;
import com.mehraj.amazonlite.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterUserRequest request) {

        logger.info("Registering user with email: {}", request.getEmail());
        if (userRepository.existsByEmail(request.getEmail())) {
            logger.warn("Registration failed. Email already exists: {}", request.getEmail());
            throw new DuplicateResourceException("Email already exists");
        }

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        // Password encryption will be added later
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);
        logger.info("User registered successfully. User ID: {}", savedUser.getId());

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .build();
    }
}