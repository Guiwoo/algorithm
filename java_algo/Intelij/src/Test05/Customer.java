package Test05;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private String name;
    private String billingAddress;
    private String defaultShippingAddress;
    private List<ShoppingCart> shoppingCartList;
    private List<Orders> ordersList;

    public Customer(){};

    public Customer(String name, String billingAddress, String defaultShippingAddress, List<ShoppingCart> shoppingCartList, List<Orders> ordersList) {
        this.name = name;
        this.billingAddress = billingAddress;
        this.defaultShippingAddress = defaultShippingAddress;
        this.shoppingCartList = shoppingCartList;
        this.ordersList = ordersList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getDefaultShippingAddress() {
        return defaultShippingAddress;
    }

    public void setDefaultShippingAddress(String defaultShippingAddress) {
        this.defaultShippingAddress = defaultShippingAddress;
    }

    public List<ShoppingCart> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public void singUp(){
    }
    public void lonin(){}
}
