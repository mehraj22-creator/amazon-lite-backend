package com.mehraj.amazonlite.controller;

import com.mehraj.amazonlite.common.ApiResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<String> dashboard(Authentication authentication) {
         System.out.println("Authentication = " + authentication);
        System.out.println("Authorities = " + authentication.getAuthorities());
        return new ApiResponse<>(
                true,
                "Welcome Admin",
                "Authorities = " + authentication.getAuthorities()
        );
    }

    @GetMapping("/debug")
    public String debug(Authentication authentication) {

        System.out.println("Authentication = " + authentication);
        System.out.println("Authorities = " + authentication.getAuthorities());

        return authentication.getAuthorities().toString();
    }
}