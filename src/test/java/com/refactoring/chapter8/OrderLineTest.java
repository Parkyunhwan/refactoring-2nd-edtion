package com.refactoring.chapter8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineTest {
    private OrderLine orderLine;
    
    @BeforeEach
    void setUp() {
        orderLine = new OrderLine("Test Product", 5, 200.0);
    }
    
    @Test
    void testConstructor() {
        // TODO: 생성자 테스트
        fail("구현 필요");
    }
    
    @Test
    void testGetters() {
        // TODO: getter 메서드들 테스트
        fail("구현 필요");
    }
    
    @Test
    void testSetQuantity() {
        // TODO: 수량 변경 테스트
        fail("구현 필요");
    }
    
    @Test
    void testSetPrice() {
        // TODO: 가격 변경 테스트
        fail("구현 필요");
    }
}