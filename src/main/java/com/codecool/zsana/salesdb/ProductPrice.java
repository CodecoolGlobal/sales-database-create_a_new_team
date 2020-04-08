package com.codecool.zsana.salesdb;

class ProductPrice {

    private String productCode;
    private java.sql.Date date;
    private double price;

    ProductPrice(String productCode, java.sql.Date date, double price) {
        this.productCode = productCode;
        this.date = date;
        this.price = price;
    }

    String getProductCode() {
        return productCode;
    }

    java.sql.Date getDate() {
        return date;
    }

    double getPrice() {
        return price;
    }

}
