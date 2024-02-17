package com.example.transactionaltest.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
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

    @DisplayName("두 개의 서비스 호출 성공")
    @Test
    void basic_with_order_item() {
        productProcessor.createWithOrderItem(1L, "상품명", BigDecimal.TEN);
    }

    @DisplayName("다른 서비스 호출의 catch")
    @Test
    void with_order_item_for_catch() {
        productProcessor.createWithOrderItemAndCatch(1L, "상품명", BigDecimal.TEN);
    }

    @DisplayName("두 번째 서비스 Transaction 없음")
    @Test
    void with_child_method_without_transactional() {
        productProcessor.createWithOrderItemThrowAndCatchWithoutTransactional(1L, "상품명", BigDecimal.TEN);
    }

    @DisplayName("self-invocation 발생")
    @Test
    void parentMethodWithoutTransactionalAndInnerCall() {
        productProcessor.createAndInnerCallWithOutTransactional(1L, "상품명", BigDecimal.TEN);
    }

    @DisplayName("두 번째 메소드가 @Transactional이 있고 예외를 던진 경우")
    @Test
    void createWithChildMethodIsRequiredAndTransactional() {
        productProcessor.createWithChildMethodIsRequiredAndTransactional(1L, "상품명", BigDecimal.TEN);
    }

    @DisplayName("두 번째 메소드가 @Transactional(Requires_new)이 있고 예외를 던진 경우")
    @Test
    void createAndThrowWithTransactionalAndRequires() {
        productProcessor.createWithChildMethodIsRequiresNewAndTransactional(1L, "상품명", BigDecimal.TEN);
    }

    @DisplayName("부모 메소드에서 CheckedException, Checked Exception은 롤백되지 않음")
    @Test
    void createAndParentThrowAndChild() throws IOException {
        productProcessor.createAndParentThrowAndChild(1L, "상품명", BigDecimal.TEN);
    }

    @DisplayName("부모 메소드에서 CheckedException, rollbackFor 설정으로 CheckedException도 롤백되도록 함")
    @Test
    void createAndParentThrowWithRollbackForAndChild() throws IOException {
        productProcessor.createAndParentThrowWithRollbackForAndChild(1L, "상품명", BigDecimal.TEN);
    }
}
