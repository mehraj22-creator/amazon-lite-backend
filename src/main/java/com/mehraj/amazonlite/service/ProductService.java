package com.mehraj.amazonlite.service;

import java.util.List;

import com.mehraj.amazonlite.dto.request.CreateProductRequest;
import com.mehraj.amazonlite.dto.response.ProductResponse;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long id);

}