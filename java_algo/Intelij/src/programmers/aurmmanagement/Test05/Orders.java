package programmers.aurmmanagement.Test05;

import java.time.LocalDateTime;

public class Orders {
    private Long id;
    private Long customerId;
    private LocalDateTime orderDate;
    private String status;
    private double price;
    private OrderDetails orderDetails;

    public Orders(){};

    public Orders(Long id, Long customerId, LocalDateTime orderDate, String status, double price, OrderDetails orderDetails) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.price = price;
        this.orderDetails = orderDetails;
    }

    public Orders(OrderDetails details){
        this.orderDetails = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void updateOrderStatus(){
        //logic
    }
    public void placeOrder(){
        //logic
    }
}
