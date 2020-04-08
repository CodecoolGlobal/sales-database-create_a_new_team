package com.codecool.zsana.salesdb;

public class Main {

    public static void main(String[] args) {
        Service.addNewOrderToExistingCustomer();
        Service.addNewOrderToNewCustomer();
        Service.addNewProductLine();
        Service.addNewProduct();
        Service.changePhoneNumber();
        Service.changeCity();
        Service.changeContactName();
    }

}