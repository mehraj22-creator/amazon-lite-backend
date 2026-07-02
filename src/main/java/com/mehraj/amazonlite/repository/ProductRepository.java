package com.mehraj.amazonlite.repository;

import com.mehraj.amazonlite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

}