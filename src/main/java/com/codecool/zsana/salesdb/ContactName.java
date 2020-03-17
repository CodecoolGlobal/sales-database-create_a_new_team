package com.codecool.zsana.salesdb;

class ContactName {

    private int contactNameId;
    private String firstName;
    private String lastName;

    ContactName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    int getContactNameId() {
        return contactNameId;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setContactNameId(int contactNameId) {
        this.contactNameId = contactNameId;
    }

}
