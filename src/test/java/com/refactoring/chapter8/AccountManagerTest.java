package com.refactoring.chapter8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagerTest {
    private Customer customer;
    private AccountManager accountManager;
    
    @BeforeEach
    void setUp() {
        customer = new Customer("Jane Smith");
        Order order = new Order(LocalDate.of(2023, 1, 10));
        order.addOrderLine(new OrderLine("Product X", 8, 150.0));
        customer.addOrder(order);
        
        accountManager = new AccountManager(customer);
    }
    
    @Test
    void testGetCustomerName() {
        // TODO: 고객명 조회 테스트
        fail("구현 필요");
    }
    
    @Test
    void testGetCustomerTotalAmount() {
        // TODO: 고객 총 주문 금액 조회 테스트
        fail("구현 필요");
    }
    
    @Test
    void testGetCustomerDiscountedAmount() {
        // TODO: 할인된 금액 조회 테스트
        fail("구현 필요");
    }
    
    @Test
    void testProcessAccount() {
        // TODO: 계정 처리 테스트
        fail("구현 필요");
    }
    
    @Test
    void testFormatCustomerData() {
        // TODO: 고객 데이터 포맷팅 테스트
        fail("구현 필요");
    }
}