package com.mehraj.amazonlite.service.impl;

import com.mehraj.amazonlite.dto.request.RegisterUserRequest;
import com.mehraj.amazonlite.dto.response.ProfileResponse;
import com.mehraj.amazonlite.dto.response.UserResponse;
import com.mehraj.amazonlite.entity.User;
import com.mehraj.amazonlite.enums.Role;
import com.mehraj.amazonlite.exception.DuplicateResourceException;
import com.mehraj.amazonlite.repository.UserRepository;
import com.mehraj.amazonlite.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
        user.setRole(Role.USER);        
        User savedUser = userRepository.save(user);
        logger.info("User registered successfully. User ID: {}", savedUser.getId());

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .build();
    }

    @Override
    public ProfileResponse getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ProfileResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}