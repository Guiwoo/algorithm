package programmers.aurmmanagement.Test05;

import java.time.LocalDateTime;

public class OrderDetails {
    private Long id;
    private Long orderId;
    private String shippingAddress;
    private String shippingType;
    private double shippingCost;
    private String billingAddress;
    private LocalDateTime createdDate;

    public OrderDetails(){}

    public OrderDetails(Long id, Long orderId, String shippingAddress, String shippingType, double shippingCost, String billingAddress, LocalDateTime createdDate) {
        this.id = id;
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
        this.shippingType = shippingType;
        this.shippingCost = shippingCost;
        this.billingAddress = billingAddress;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void cancelOrder(){

    }
}
