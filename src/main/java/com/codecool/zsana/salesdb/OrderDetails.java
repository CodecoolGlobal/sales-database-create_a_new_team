package com.codecool.zsana.salesdb;

public class OrderDetails {

    private int orderNumber;
    private String productCode;
    private int quantity;
    private int sales;
    private int dealSizeId;
    private int productPrice;

    OrderDetails(int orderNumber, String productCode, int quantity, int productPrice) {
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

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSales() {
        return sales;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getDealSizeId() {
        return dealSizeId;
    }
}
