package com.refactoring.chapter8;

public class PriceCalculator {
    // 정적 유틸리티 메서드들이 있지만, 실제로는 상태를 가져야 할 것들
    private static final double BULK_DISCOUNT_RATE = 0.95;
    private static final double VIP_DISCOUNT_RATE = 0.9;
    private static final double LOYALTY_DISCOUNT_RATE = 0.95;
    
    // 이 메서드들은 실제로는 다른 클래스로 이동되어야 함
    public static double calculateLineTotal(OrderLine line) {
        double total = line.getQuantity() * line.getPrice();
        if (line.getQuantity() > 10) {
            total *= BULK_DISCOUNT_RATE;
        }
        return total;
    }
    
    public static double calculateOrderTotal(Order order) {
        double total = 0;
        for (OrderLine line : order.getOrderLines()) {
            total += calculateLineTotal(line);
        }
        return total;
    }
    
    // 고객별 할인 로직 (Customer 클래스로 이동되어야 할 메서드)
    public static double applyCustomerDiscounts(Customer customer, double amount) {
        if (amount > 1000) {
            amount *= VIP_DISCOUNT_RATE;
        }
        if (customer.getOrders().size() > 5) {
            amount *= LOYALTY_DISCOUNT_RATE;
        }
        return amount;
    }
}