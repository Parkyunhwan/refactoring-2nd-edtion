package com.refactoring.chapter8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {
    private Customer customer;
    private Order order;
    private OrderLine orderLine;
    
    @BeforeEach
    void setUp() {
        customer = new Customer("Test Customer");
        order = new Order(LocalDate.of(2023, 1, 1));
        orderLine = new OrderLine("Test Product", 12, 100.0);
        order.addOrderLine(orderLine);
        customer.addOrder(order);
    }
    
    @Test
    void testCalculateLineTotal() {
        // TODO: 주문 라인 총액 계산 테스트
        fail("구현 필요");
    }
    
    @Test
    void testCalculateLineTotalWithBulkDiscount() {
        // TODO: 대량 할인이 적용된 라인 총액 테스트
        fail("구현 필요");
    }
    
    @Test
    void testCalculateOrderTotal() {
        // TODO: 주문 총액 계산 테스트
        fail("구현 필요");
    }
    
    @Test
    void testApplyCustomerDiscounts() {
        // TODO: 고객 할인 적용 테스트
        fail("구현 필요");
    }
    
    @Test
    void testApplyVipDiscount() {
        // TODO: VIP 할인 테스트 (1000원 이상)
        fail("구현 필요");
    }
    
    @Test
    void testApplyLoyaltyDiscount() {
        // TODO: 충성도 할인 테스트 (주문 5개 이상)
        fail("구현 필요");
    }
}