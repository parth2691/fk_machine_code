package org.example.models;

import  java.util.*;
public class Order {
    String orderId;
    List<String> restuarants;
    List<String> items;

    public Order(String orderId, List<String> restuarants, List<String> items) {
        this.orderId = orderId;
        this.restuarants = restuarants;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<String> getRestuarants() {
        return restuarants;
    }

    public void setRestuarants(List<String> restuarants) {
        this.restuarants = restuarants;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
