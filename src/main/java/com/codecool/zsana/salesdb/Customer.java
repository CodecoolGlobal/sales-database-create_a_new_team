package com.codecool.zsana.salesdb;

public class Customer {

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

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getContactNameId() {
        return contactNameId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }



}
