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

    static double generateProductPrice() {
        return 39 + rand.nextInt((100 - 39) + 1);
    }

    static int chooseQuantity() {
        return 1 + rand.nextInt((4 - 1) + 1);
    }

    static String generateLastName() {
        return faker.name().lastName();
    }

    static String generateFirstName() {
       return faker.name().firstName();
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

    static Query getQuery() {
        return query;
    }

}