package com.codecool.zsana.salesdb;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;

class RandomData {

    private static Query query = new Query();
    private static Random rand = new Random();
    private static Faker faker = new Faker();

    static int chooseCustomerId() {
        List<Integer> ids = query.getCustomerIds();
        return ids.get(rand.nextInt(ids.size()));
    }

    static String chooseProductCode() {
        List<String> codes = query.getProductCodes();
        return codes.get(rand.nextInt(codes.size()));
    }

    static int chooseProductLineId() {
        List<Integer> ids = query.getProductLineIds();
        return ids.get(rand.nextInt(ids.size()));
    }

    static String generateProductCode() {
        List<String> codes = query.getProductCodes();
        String newCode = getLetterForProductCode() + rand.nextInt(999) + "_" + rand.nextInt(9999);
        while (codes.contains(newCode)) {
            newCode = getLetterForProductCode() + rand.nextInt(999) + "_" + rand.nextInt(9999);
        }
        return newCode;
    }

    static double generateProductPrice() {
        double price = 39 + (100 - 39) * rand.nextDouble();
        String p = String.valueOf(price).substring(0, 5);
        return Double.valueOf(p);
    }

    static int chooseQuantity() {
        return 1 + rand.nextInt((4 - 1) + 1);
    }

    static String generateContactName() {
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    static String generateCustomerName() {
        return faker.company().name();
    }

    static String generateAddressLine() {
        return faker.address().streetAddress();
    }

    static String generateCity() {
        return faker.address().city();
    }

    static String generatePostalCode() {
        return faker.address().zipCode();
    }

    static String generateCountry() {
        return faker.address().country();
    }

    static String generatePhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    static String generateProductline() {
        return faker.company().industry();
    }

    static Query getQuery() {
        return query;
    }

    private static String getLetterForProductCode() {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        return letters[rand.nextInt(letters.length)];
    }

}