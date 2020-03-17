package com.codecool.zsana.salesdb;

class Customer {

    private int customerId;
    private String customerName;
    private String phoneNumber;
    private int addressId;
    private int contactNameId;

    Customer(String customerName, String phoneNumber, int addressId, int contactNameId) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.addressId = addressId;
        this.contactNameId = contactNameId;
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

    int getContactNameId() {
        return contactNameId;
    }

    void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}
