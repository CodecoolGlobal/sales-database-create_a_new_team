package com.codecool.zsana.salesdb;

class Customer {

    private int customerId;
    private String customerName;
    private String phoneNumber;
    private int addressId;
    private String contactName;

    Customer(String customerName, String phoneNumber, int addressId, String contactName) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.addressId = addressId;
        this.contactName = contactName;
    }

    int getCustomerId() {
        return customerId;
    }

    String getCustomerName() {
        return customerName;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    int getAddressId() {
        return addressId;
    }

    String getContactName() {
        return contactName;
    }

    void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}
