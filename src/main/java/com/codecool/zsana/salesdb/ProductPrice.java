package com.codecool.zsana.salesdb;

class ProductPrice {

    private String productCode;
    private OrderDate orderDate;
    private double price;

    ProductPrice(String productCode, OrderDate orderdate, double price) {
        this.productCode = productCode;
        this.orderDate = orderdate;
        this.price = price;
    }

    String getProductCode() {
        return productCode;
    }

    OrderDate getOrderDate() {
        return orderDate;
    }

    double getPrice() {
        return price;
    }

}
