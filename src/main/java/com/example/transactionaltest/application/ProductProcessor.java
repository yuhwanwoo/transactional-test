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
    private final OrderItemProcessor orderItemProcessor;

    public ProductProcessor(ProductRepository productRepository, OrderItemProcessor orderItemProcessor) {
        this.productRepository = productRepository;
        this.orderItemProcessor = orderItemProcessor;
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

    @Transactional
    public void createWithOrderItem(Long id, String name, BigDecimal price) {
        Product product = Product.of(id, name, price);
        productRepository.save(product);
        orderItemProcessor.create(1L, 1L, "주문아이템1", BigDecimal.TEN);
    }


    @Transactional
    public void createWithOrderItemAndCatch(Long id, String name, BigDecimal price) {
        Product product = Product.of(id, name, price);
        productRepository.save(product);
        orderItemProcessor.createForCatch(1L, 1L, "주문아이템1", BigDecimal.TEN);
    }

    @Transactional
    public void createWithOrderItemThrowAndCatchWithoutTransactional(Long id, String name, BigDecimal price) {
        Product product = Product.of(id, name, price);
        productRepository.save(product);
        try {
            orderItemProcessor.createAndThrow(1L, 1L, "주문아이템1", BigDecimal.TEN);
        } catch (RuntimeException e) {
            System.out.println("catch");
        }
    }

}
