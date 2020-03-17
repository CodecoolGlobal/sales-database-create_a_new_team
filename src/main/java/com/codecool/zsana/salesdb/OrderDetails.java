package com.codecool.zsana.salesdb;

class OrderDetails {

    private int orderNumber;
    private String productCode;
    private int quantity;
    private double sales;
    private int dealSizeId;
    private double productPrice;

    OrderDetails(int orderNumber, String productCode, int quantity, double productPrice) {
        this.orderNumber = orderNumber;
        this.productCode = productCode;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.sales = quantity*productPrice;
        if (sales < 3000) {
            this.dealSizeId = 1;
        } else if (sales < 7000) {
            this.dealSizeId = 2;
        } else {
            dealSizeId = 3;
        }
    }

    int getOrderNumber() {
        return orderNumber;
    }

    String getProductCode() {
        return productCode;
    }

    int getQuantity() {
        return quantity;
    }

    double getSales() {
        return sales;
    }

    double getProductPrice() {
        return productPrice;
    }

    int getDealSizeId() {
        return dealSizeId;
    }

}
