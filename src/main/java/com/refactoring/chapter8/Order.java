package com.refactoring.chapter8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDate orderDate;
    private List<OrderLine> orderLines = new ArrayList<>();
    private String status;
    
    public Order(LocalDate orderDate) {
        this.orderDate = orderDate;
        this.status = "PENDING";
    }
    
    public LocalDate getOrderDate() {
        return orderDate;
    }
    
    public void addOrderLine(OrderLine orderLine) {
        orderLines.add(orderLine);
    }
    
    public List<OrderLine> getOrderLines() {
        return orderLines;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Order 클래스가 할인 로직을 알 필요가 없는데 알고 있음
    public double getOrderTotal() {
        double total = 0;
        for (OrderLine line : orderLines) {
            double lineTotal = line.getQuantity() * line.getPrice();
            // 할인 로직이 여기저기 중복되어 있음
            if (line.getQuantity() > 10) {
                lineTotal *= 0.95;
            }
            total += lineTotal;
        }
        return total;
    }
}