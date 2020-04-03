package com.codecool.zsana.salesdb;

class Service {

    // new order from existing customer

    static void addNewOrderToExistingCustomer() {
        int customerId = RandomData.chooseCustomerId();
        newOrder(customerId);
    }

    // new order from new customer

    static void addNewOrderToNewCustomer() {
        String line1 = RandomData.generateAddressLine();
        String city = RandomData.generateCity();
        String postalCode = RandomData.generatePostalCode();
        String country = RandomData.generateCountry();
        int customerAddressId = RandomData.getQuery().insertToCustomerAddress(new Address(line1, city, postalCode, country));
        String customerName = RandomData.generateCustomerName();
        String phoneNumber = RandomData.generatePhoneNumber();
        String contactName = RandomData.generateContactName();
        int customerId = RandomData.getQuery().insertIntoCustomers(new Customer(customerName, phoneNumber, customerAddressId, contactName));
        newOrder(customerId);
    }

    // add new order

    static void newOrder(int customerId) {
        int orderStatus = 4; // default: In process
        Order newOrder = new Order(orderStatus, customerId);
        int orderNumber = RandomData.getQuery().insertToOrders(newOrder);
        String productCode = RandomData.chooseProductCode();
        double productPrice = RandomData.generateProductPrice();
        int quantity = RandomData.chooseQuantity();
        OrderDetails details = new OrderDetails(orderNumber, productCode, quantity, productPrice);
        RandomData.getQuery().insertToOrderDetails(details);
        ProductPrice price = new ProductPrice(productCode, newOrder.getDateFromTimeStampAsDate(), productPrice);
        RandomData.getQuery().insertToProductPrices(price);
    }

    // choose random id, and change its phone

    static void changePhoneNumber() {
        int customerId = RandomData.chooseCustomerId();
        String newPhone = RandomData.generatePhoneNumber();
        RandomData.getQuery().changePhoneNumber(customerId, newPhone);
    }

    // change city name in address

    static void changeCity() {
        int customerId = RandomData.chooseCustomerId();
        String newCity = RandomData.generateCity();
        RandomData.getQuery().changeCity(customerId, newCity);
    }

    // change contact name in customers

    static void changeContactName() {
        int customerId = RandomData.chooseCustomerId();
        String name = RandomData.generateContactName();
        RandomData.getQuery().changeContactName(customerId, name);
    }

}
