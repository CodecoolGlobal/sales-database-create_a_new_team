package com.codecool.zsana.salesdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class QueryTest extends BaseTest{

    @BeforeEach
    public void setup() {
        setupConnection();
    }

    @Test
    void getLastOrderNumber() {
        Assertions.assertEquals(Integer.valueOf(40), getQuery().getLastOrderNumber());
    }

    @Test
    void insertToOrders() {
    }

    @Test
    void insertToOrderDates() {
    }

    @Test
    void insertToOrderDetails() {
    }

    @Test
    void insertToCustomerAddress() {
    }

    @Test
    void insertIntoCustomers() {
    }

    @Test
    void insertToProductPrices() {
    }

    @Test
    void changePhoneNumber() {
    }

    @Test
    void changeCity() {
    }

    @Test
    void changeContactName() {
    }

    @Test
    void getCustomerIds() {
    }

    @Test
    void getProductCodes() {
    }
}