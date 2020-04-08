package com.codecool.zsana.salesdb;

public class Product {

    private String productCode;
    private int msrp;
    private int productLineId;

    Product(String productCode, int msrp, int productLineId) {
        this.productCode = productCode;
        this.msrp = msrp;
        this.productLineId = productLineId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getMsrp() {
        return msrp;
    }

    public void setMsrp(int msrp) {
        this.msrp = msrp;
    }

    public int getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(int productLineId) {
        this.productLineId = productLineId;
    }
}
