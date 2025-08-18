package com.refactoring.chapter8;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Order> orders = new ArrayList<>();
    
    public Customer(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void addOrder(Order order) {
        orders.add(order);
    }
    
    public List<Order> getOrders() {
        return orders;
    }
    
    // 고객의 총 주문 금액을 계산 (Customer가 Order의 세부사항을 너무 많이 알고 있음)
    public double getTotalOrderAmount() {
        double total = 0;
        for (Order order : orders) {
            for (OrderLine line : order.getOrderLines()) {
                total += line.getQuantity() * line.getPrice();
                if (line.getQuantity() > 10) {
                    total *= 0.95; // 10개 이상 주문시 5% 할인
                }
            }
        }
        return total;
    }
    
    // 고객의 할인된 주문 금액 계산 (복잡한 비즈니스 로직이 Customer에 있음)
    public double getDiscountedTotal() {
        double total = getTotalOrderAmount();
        if (total > 1000) {
            total *= 0.9; // 1000원 이상시 10% 할인
        }
        if (orders.size() > 5) {
            total *= 0.95; // 주문 5개 이상시 추가 5% 할인
        }
        return total;
    }
    
    // 고객 정보를 문자열로 변환 (너무 많은 책임)
    public String getCustomerSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer: ").append(name).append("\n");
        sb.append("Total Orders: ").append(orders.size()).append("\n");
        sb.append("Total Amount: ").append(getTotalOrderAmount()).append("\n");
        sb.append("Discounted Amount: ").append(getDiscountedTotal()).append("\n");
        
        for (Order order : orders) {
            sb.append("  Order Date: ").append(order.getOrderDate()).append("\n");
            for (OrderLine line : order.getOrderLines()) {
                sb.append("    ").append(line.getProductName())
                  .append(" x").append(line.getQuantity())
                  .append(" @ ").append(line.getPrice()).append("\n");
            }
        }
        return sb.toString();
    }
}