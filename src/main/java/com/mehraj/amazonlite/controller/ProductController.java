package com.mehraj.amazonlite.controller;

import com.mehraj.amazonlite.common.ApiResponse;
import com.mehraj.amazonlite.dto.request.CreateProductRequest;
import com.mehraj.amazonlite.dto.response.ProductResponse;
import com.mehraj.amazonlite.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<ProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest request) {

        ProductResponse response =
                productService.createProduct(request);

        return new ApiResponse<>(
                true,
                "Product created successfully",
                response
        );
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> response =
                productService.getAllProducts();

        return new ApiResponse<>(
                true,
                "Products fetched successfully",
                response
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(
            @PathVariable Long id) {

        ProductResponse response =
                productService.getProductById(id);

        return new ApiResponse<>(
                true,
                "Product fetched successfully",
                response
        );
    }

}