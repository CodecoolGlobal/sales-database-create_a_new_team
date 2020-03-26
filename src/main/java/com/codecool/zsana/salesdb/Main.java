package com.codecool.zsana.salesdb;

public class Main {

    public static void main(String[] args) {
        Service.addNewOrderToExistingCustomer();
        Service.addNewOrderToNewCustomer();
        Service.changePhoneNumber();
        Service.changeCity();
        Service.changeContactName();
        //System.out.println(RandomData.generateCountry());
    }

}