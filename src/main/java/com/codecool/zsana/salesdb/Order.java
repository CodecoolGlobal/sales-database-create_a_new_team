package com.codecool.zsana.salesdb;

public class Order {

    private int orderNumber;
    private Orderdate orderDate;
    private int orderStatus;
    private int customerId;

    Order(Orderdate orderDate, int orderStatus, int customerId) {
        this.orderNumber = setOrderNumberFromDatabase();
        this.orderStatus = orderStatus;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    private static int setOrderNumberFromDatabase() { // mi van, ha több order jön be egyszerre???
        Query q = new Query();
        return q.getLastOrderNumber() + 1;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Orderdate getOrderDate() {
        return orderDate;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", orderDate=" + orderDate.getDate() +
                ", orderStatus=" + orderStatus +
                ", customerId=" + customerId +
                '}';
    }
}
