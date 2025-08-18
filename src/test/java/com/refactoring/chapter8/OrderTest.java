package com.refactoring.chapter8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;
    
    @BeforeEach
    void setUp() {
        order = new Order(LocalDate.of(2023, 1, 15));
        order.addOrderLine(new OrderLine("Product A", 5, 100.0));
        order.addOrderLine(new OrderLine("Product B", 15, 50.0));
    }
    
    @Test
    void testGetOrderTotal() {
        // TODO: 주문 총액 계산 테스트
        fail("구현 필요");
    }
    
    @Test
    void testAddOrderLine() {
        // TODO: 주문 라인 추가 테스트
        fail("구현 필요");
    }
    
    @Test
    void testOrderStatus() {
        // TODO: 주문 상태 테스트
        fail("구현 필요");
    }
    
    @Test
    void testBulkDiscountInOrder() {
        // TODO: 주문 레벨에서의 대량 할인 테스트
        fail("구현 필요");
    }
}