package com.example.transactionaltest.application;

import com.example.transactionaltest.domain.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class TransactionalTest {


    @Autowired
    private  ProductProcessor productProcessor;

    @DisplayName("기본 사용")
    @Test
    void basic() {
        productProcessor.create(1L, "상품명", BigDecimal.TEN);
    }

}
