package com.example.transactionaltest.application;

import com.example.transactionaltest.domain.OrderItem;
import com.example.transactionaltest.domain.OrderItemRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class OrderItemProcessor {
    private final OrderItemRepository orderItemRepository;

    public OrderItemProcessor(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public void create(Long id, Long quantity, String name, BigDecimal price) {
        OrderItem orderItem = OrderItem.of(id, quantity, name, price);
        orderItemRepository.save(orderItem);
    }

    @Transactional
    public void createForCatch(Long id, Long quantity, String name, BigDecimal price) {
        OrderItem orderItem = OrderItem.of(id, quantity, name, price);
        orderItemRepository.save(orderItem);
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {

        }
    }
}
