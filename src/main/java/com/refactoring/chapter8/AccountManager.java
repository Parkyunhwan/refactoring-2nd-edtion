package com.refactoring.chapter8;

public class AccountManager {
    private Customer customer;
    
    public AccountManager(Customer customer) {
        this.customer = customer;
    }
    
    // 중개자 역할만 하는 메서드들 (Remove Middle Man 후보)
    public String getCustomerName() {
        return customer.getName();
    }
    
    public double getCustomerTotalAmount() {
        return customer.getTotalOrderAmount();
    }
    
    public double getCustomerDiscountedAmount() {
        return customer.getDiscountedTotal();
    }
    
    public String getCustomerSummary() {
        return customer.getCustomerSummary();
    }
    
    // AccountManager만의 고유한 기능
    public void processAccount() {
        System.out.println("Processing account for " + getCustomerName());
    }
    
    // 외부 라이브러리를 사용하는 기능 (Introduce Foreign Method 예제용)
    public String formatCustomerData() {
        // 외부 DateFormatter 클래스가 있다고 가정
        // 하지만 우리가 원하는 기능이 없어서 여기서 직접 구현
        return "Formatted: " + customer.getName() + " - " + customer.getOrders().size() + " orders";
    }
}