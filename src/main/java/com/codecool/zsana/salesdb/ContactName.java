package com.codecool.zsana.salesdb;

public class ContactName {

    private int contactNameId;
    private String firstName;
    private String lastName;

    ContactName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getContactNameId() {
        return contactNameId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setContactNameId(int contactNameId) {
        this.contactNameId = contactNameId;
    }

}
