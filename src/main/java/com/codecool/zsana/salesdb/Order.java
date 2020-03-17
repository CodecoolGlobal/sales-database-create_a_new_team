package com.codecool.zsana.salesdb;

class Order {

    private int orderNumber;
    private OrderDate orderDate;
    private int orderStatus;
    private int customerId;

    Order(OrderDate orderDate, int orderStatus, int customerId) {
        this.orderStatus = orderStatus;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    int getOrderNumber() {
        return orderNumber;
    }

    void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    OrderDate getOrderDate() {
        return orderDate;
    }

    int getOrderStatus() {
        return orderStatus;
    }

    int getCustomerId() {
        return customerId;
    }
}
