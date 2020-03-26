package com.codecool.zsana.salesdb;

class Service {

    // new order from existing customer

    static void addNewOrderToExistingCustomer() {
        int customerId = RandomData.chooseCustomerId();
        newOrder(customerId);
    }

    // new order from new customer

    static void addNewOrderToNewCustomer() {
        String lastname = RandomData.generateLastName();
        String firstName = RandomData.generateFirstName();
        int contactNameId = RandomData.getQuery().insertToContactNames(new ContactName(firstName, lastname));
        String line1 = RandomData.generateAddressLine();
        String city = RandomData.generateCity();
        String postalCode = RandomData.generatePostalCode();
        String country = RandomData.generateCountry();
        int customerAddressId = RandomData.getQuery().insertToCustomerAddress(new Address(line1, city, postalCode, country));
        String customerName = RandomData.generateCustomerName();
        String phoneNumber = RandomData.generatePhoneNumber();
        int customerId = RandomData.getQuery().insertIntoCustomers(new Customer(customerName, phoneNumber, customerAddressId, contactNameId));
        newOrder(customerId);
    }

    static void newOrder(int customerId) {
        int orderStatus = 4; // default: In process
        OrderDate orderdate = new OrderDate();
        RandomData.getQuery().insertToOrderDates(orderdate);
        Order newOrder = new Order(orderdate, orderStatus, customerId);
        int orderNumber = RandomData.getQuery().insertToOrders(newOrder);
        System.out.println("OrderNumber: " + orderNumber);
        String productCode = RandomData.chooseProductCode();
        double productPrice = RandomData.generateProductPrice();
        int quantity = RandomData.chooseQuantity();
        OrderDetails details = new OrderDetails(orderNumber, productCode, quantity, productPrice);
        RandomData.getQuery().insertToOrderDetails(details);
        ProductPrice price = new ProductPrice(productCode, orderdate, productPrice);
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
        System.out.println(customerId);
        System.out.println(newCity);
        RandomData.getQuery().changeCity(customerId, newCity);
    }

    // change lastname, firstname in contactnames

    static void changeContactName() {
        int customerId = RandomData.chooseCustomerId();
        String lastName = RandomData.generateLastName();
        String firstName = RandomData.generateFirstName();
        RandomData.getQuery().changeContactName(customerId, lastName, firstName);
    }

}
