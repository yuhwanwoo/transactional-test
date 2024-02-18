package com.example.transactionaltest.application;

import com.example.transactionaltest.domain.OrderItem;
import com.example.transactionaltest.domain.OrderItemRepository;
import com.example.transactionaltest.exception.OrderItemException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
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
            throw new OrderItemException();
        } catch (RuntimeException e) {
            System.out.println("Catch");
        }
    }

    public void createAndThrow(Long id, Long quantity, String name, BigDecimal price) {
        OrderItem orderItem = OrderItem.of(id, quantity, name, price);
        orderItemRepository.save(orderItem);
        throw new OrderItemException();
    }

    @Transactional
    public void createAndThrowWithTransactional(Long id, Long quantity, String name, BigDecimal price) {
        OrderItem orderItem = OrderItem.of(id, quantity, name, price);
        orderItemRepository.save(orderItem);
        throw new OrderItemException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createAndThrowWithTransactionalAndRequiresNew(Long id, Long quantity, String name, BigDecimal price) {
        OrderItem orderItem = OrderItem.of(id, quantity, name, price);
        orderItemRepository.save(orderItem);
        throw new OrderItemException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createAndThrowWithTransactionalAndRequiresNewWithCatch(Long id, Long quantity, String name, BigDecimal price) {
        try {
            OrderItem orderItem = OrderItem.of(id, quantity, name, price);
            orderItemRepository.save(orderItem);
        } catch (RuntimeException e) {
            System.out.println("Runtime Exception");
        }
    }
}
