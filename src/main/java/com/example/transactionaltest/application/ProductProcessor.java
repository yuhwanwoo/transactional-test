package com.example.transactionaltest.application;

import com.example.transactionaltest.domain.Product;
import com.example.transactionaltest.domain.ProductRepository;
import com.example.transactionaltest.exception.ProductException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class ProductProcessor {
    private final ProductRepository productRepository;

    public ProductProcessor(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void create(Long id, String name, BigDecimal price) {
        Product product = Product.of(id, name, price);
        productRepository.save(product);
    }

    @Transactional
    public void createThrow(Long id, String name, BigDecimal price) {
        Product product = Product.of(id, name, price);
        productRepository.save(product);
        throw new ProductException();
    }

    @Transactional
    public void createThrowAndCatch(Long id, String name, BigDecimal price) {
        Product product = Product.of(id, name, price);
        productRepository.save(product);
        try {
            throw new ProductException();
        } catch (RuntimeException e) {
            System.out.println("Product Exception");
        }
    }
}
