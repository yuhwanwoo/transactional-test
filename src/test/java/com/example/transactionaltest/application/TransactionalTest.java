package com.example.transactionaltest.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class TransactionalTest {


    @Autowired
    private ProductProcessor productProcessor;

    @DisplayName("기본 사용")
    @Test
    void basic() {
        productProcessor.create(1L, "상품명", BigDecimal.TEN);
    }

    @DisplayName("기본 throw 롤백")
    @Test
    void basic_throw() {
        productProcessor.createThrow(1L, "상품명", BigDecimal.TEN);
    }

    @DisplayName("기본 throw and catch")
    @Test
    void basic_throw_and_catch() {
        productProcessor.createThrowAndCatch(1L, "상품명", BigDecimal.TEN);
    }

    @DisplayName("두 개의 서비스 호출")
    @Test
    void basic_with_order_item() {
        productProcessor.createWithOrderItem(1L, "상품명", BigDecimal.TEN);
    }
}
