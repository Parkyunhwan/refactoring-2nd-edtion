package com.refactoring.chapter8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

class CustomerTest {
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("John Doe");
    }

    @Nested
    @DisplayName("getTotalOrderAmount 테스트")
    class GetTotalOrderAmountTest {
        
        @Test
        @DisplayName("단일 주문 - 할인 없음")
        void singleOrderWithoutDiscount() {
            Order order1 = new Order(LocalDate.of(2025, 8, 17));
            OrderLine productA = new OrderLine("A", 5, 2500);
            order1.addOrderLine(productA);
            customer.addOrder(order1);

            double aAmount = productA.getPrice() * productA.getQuantity();
            assertThat(customer.getTotalOrderAmount())
                .isEqualTo(aAmount);
        }

        @Test
        @DisplayName("단일 주문 - 벌크 할인 적용 (10개 이상)")
        void singleOrderWithBulkDiscount() {
            Order order1 = new Order(LocalDate.of(2025, 8, 17));
            OrderLine productA = new OrderLine("A", 20, 2500);
            order1.addOrderLine(productA);
            customer.addOrder(order1);

            double aAmount = productA.getPrice() * productA.getQuantity() * 0.95;
            assertThat(customer.getTotalOrderAmount())
                .isEqualTo(aAmount);
        }

        @Test
        @DisplayName("여러 주문 - 혼합 할인")
        void multipleOrdersWithMixedDiscounts() {
            // given
            Order order1 = new Order(LocalDate.of(2025, 8, 17));
            Order order2 = new Order(LocalDate.of(2025, 8, 16));
            OrderLine productA = new OrderLine("A", 5, 2500);
            OrderLine productB = new OrderLine("B", 13, 1000);
            order1.addOrderLine(productA);
            order2.addOrderLine(productB);
            customer.addOrder(order1);
            customer.addOrder(order2);

            // when
            double result = customer.getTotalOrderAmount();

            // then
            double aAmount = productA.getPrice() * productA.getQuantity();
            double bAmount = productB.getPrice() * productB.getQuantity() * 0.95;
            double expectedAmount = aAmount + bAmount;

            assertThat(result)
                .isEqualTo(expectedAmount);
        }

        @Test
        @DisplayName("빈 주문 목록")
        void emptyOrderList() {
            // when
            double result = customer.getTotalOrderAmount();

            // then
            assertThat(result)
                .isZero();
        }

        @Test
        @DisplayName("경계값 테스트 - 정확히 10개")
        void exactlyTenItems() {
            // given
            Order order = new Order(LocalDate.now());
            OrderLine orderLine = new OrderLine("2", 10, 1000);
            order.addOrderLine(orderLine);
            customer.addOrder(order);

            // when
            double result = customer.getTotalOrderAmount();

            // then
            assertThat(result)
                .isEqualTo(orderLine.getPrice() * orderLine.getQuantity());
        }

        @Test
        @DisplayName("경계값 테스트 - 11개")
        void elevenItems() {
            // given
            Order order = new Order(LocalDate.now());
            OrderLine orderLine = new OrderLine("2", 11, 1000);
            order.addOrderLine(orderLine);
            customer.addOrder(order);

            // when
            double result = customer.getTotalOrderAmount();

            // then
            assertThat(result)
                .isEqualTo(orderLine.getPrice() * orderLine.getQuantity() * 0.95);
        }
    }

    @Nested
    @DisplayName("getDiscountedTotal 테스트")
    class GetDiscountedTotalTest {
        
        @Test
        @DisplayName("VIP 할인만 적용 (1000원 이상)")
        void vipDiscountOnly() {
            // given
            Order order1 = new Order(LocalDate.of(2025, 8, 17));
            OrderLine productA = new OrderLine("A", 5, 2500);
            order1.addOrderLine(productA);
            customer.addOrder(order1);

            // when
            double result = customer.getDiscountedTotal();

            // then
            double totalOrderAmount = customer.getTotalOrderAmount();
            double expectedAmount = totalOrderAmount * 0.9;
            assertThat(result)
                .isEqualTo(expectedAmount);
            
        }

        @Test
        @DisplayName("로열티 할인만 적용 (주문 5개 이상)")
        void loyaltyDiscountOnly() {
            // given
            IntStream.range(0, 6)
                .forEach(i -> {
                    System.out.println("Number" + i);
                    Order order = new Order(LocalDate.now().minusDays(i));
                    order.addOrderLine(new OrderLine("Product " + i, 2, 50.0));
                    customer.addOrder(order);
                });
            // when
            double result = customer.getDiscountedTotal();

            // then
            double baseAmount = 2 * 50 * 6;
            double loyaltyAmount = baseAmount * 0.95;
            assertThat(result)
                .isEqualTo(loyaltyAmount);
        }

        @Test
        @DisplayName("모든 할인 적용")
        void allDiscountsApplied() {
            // given
            IntStream.range(0, 6)
                .forEach(i -> {
                    System.out.println("Number" + i);
                    Order order = new Order(LocalDate.now().minusDays(i));
                    order.addOrderLine(new OrderLine("Product " + i, 2, 100.0));
                    customer.addOrder(order);
                });
            // when
            double result = customer.getDiscountedTotal();

            // then
            double baseAmount = 2 * 100 * 6;
            double vipAmount = baseAmount * 0.9;
            double loyaltyVipAmount = vipAmount * 0.95;
            assertThat(result)
                .isEqualTo(loyaltyVipAmount);
        }

        @Test
        @DisplayName("할인 없음 (기준 미달)")
        void noDiscountsApplied() {
            // given
            Order order1 = new Order(LocalDate.of(2025, 8, 17));
            OrderLine productA = new OrderLine("A", 5, 50);
            order1.addOrderLine(productA);
            customer.addOrder(order1);

            // when
            double result = customer.getDiscountedTotal();

            // then
            double totalOrderAmount = 5 * 50;
            assertThat(result)
                .isEqualTo(totalOrderAmount);
        }
    }

    @Test
    @DisplayName("고객 요약 정보 생성")
    void customerSummaryGeneration() {
        // Given
          Order order = new Order(LocalDate.of(2023, 1, 15));
          order.addOrderLine(new OrderLine("Product A", 2, 100.0));
          customer.addOrder(order);

          // When
          String summary = customer.getCustomerSummary();

          // Then
          assertThat(summary)
              .contains("Customer: John Doe")
              .contains("Total Orders: 1")
              .contains("Product A x2 @ 100.0");
    }

    @Test
    @DisplayName("고객명 조회")
    void getCustomerName() {
        String name = customer.getName();
        assertThat(name)
            .isEqualTo("John Doe");
    }

    @Test
    @DisplayName("주문 추가 및 조회")
    void addAndGetOrders() {
        // Given
        Order order1 = new Order(LocalDate.of(2023, 1, 15));
        order1.addOrderLine(new OrderLine("Product A", 2, 100.0));
        
        Order order2 = new Order(LocalDate.of(2023, 2, 20));
        order2.addOrderLine(new OrderLine("Product B", 3, 200.0));

        // When
        customer.addOrder(order1);
        customer.addOrder(order2);

        // Then
        assertThat(customer.getOrders())
            .hasSize(2)
            .contains(order1, order2);
        
        assertThat(customer.getOrders().get(0).getOrderDate())
            .isEqualTo(LocalDate.of(2023, 1, 15));
        
        assertThat(customer.getOrders().get(1).getOrderDate())
            .isEqualTo(LocalDate.of(2023, 2, 20));
    }
}