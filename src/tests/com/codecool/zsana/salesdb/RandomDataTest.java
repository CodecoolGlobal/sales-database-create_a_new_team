package com.codecool.zsana.salesdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomDataTest {

    // See manual tests in TestDocumentations

    // NextDouble we need instead of nextInt!
    @Test
    void generateProductPrice() {
        for (int i = 0; i < 100; i++) {
            double price = RandomData.generateProductPrice();
            Assertions.assertTrue(price >= 39 && price <= 100);
        }
    }

    @Test
    void chooseQuantity() {
        for (int i = 0; i < 100; i++) {
            int quantity = RandomData.chooseQuantity();
            Assertions.assertTrue(quantity >= 1 && quantity <= 4);
        }
    }

}