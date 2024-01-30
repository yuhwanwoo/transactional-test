package com.example.transactionaltest.application;

import com.example.transactionaltest.domain.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductProcessor {
    private final ProductRepository productRepository;

    public ProductProcessor(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
