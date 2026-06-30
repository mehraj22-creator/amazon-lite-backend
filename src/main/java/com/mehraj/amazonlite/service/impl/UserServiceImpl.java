package com.mehraj.amazonlite.service.impl;

import com.mehraj.amazonlite.dto.request.RegisterUserRequest;
import com.mehraj.amazonlite.dto.response.UserResponse;
import com.mehraj.amazonlite.entity.User;
import com.mehraj.amazonlite.repository.UserRepository;
import com.mehraj.amazonlite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse register(RegisterUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        // Password encryption will be added later
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .build();
    }
}