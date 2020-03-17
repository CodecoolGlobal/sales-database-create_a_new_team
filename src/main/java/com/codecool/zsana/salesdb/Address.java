package com.codecool.zsana.salesdb;

class Address {

    private int addressId;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String territory;

    Address(String line1, String city, String postalCode, String country) {
        this.line1 = line1;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    int getAddressId() {
        return addressId;
    }

    void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    String getLine1() {
        return line1;
    }

    void setLine1(String line1) {
        this.line1 = line1;
    }

    String getLine2() {
        return line2;
    }

    void setLine2(String line2) {
        this.line2 = line2;
    }

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    String getState() {
        return state;
    }

    void setState(String state) {
        this.state = state;
    }

    String getPostalCode() {
        return postalCode;
    }

    void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    String getTerritory() {
        return territory;
    }

    void setTerritory(String territory) {
        this.territory = territory;
    }

}
