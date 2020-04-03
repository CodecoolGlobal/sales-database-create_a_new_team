package com.codecool.zsana.salesdb;

public class BaseTest {

    private String DATABASE = "jdbc:postgresql://localhost:5432/TEST_normalized_sales";
    private String USER = System.getenv("DB_USER");
    private String PASSWORD = System.getenv("DB_PASSWORD");
    private Query query;
    private AssertQuery assertQuery;

    // connect to test database through initialize query
    void setupConnection() {
        query = new Query(DATABASE, USER, PASSWORD);
        assertQuery = new AssertQuery();
    }

    Query getQuery() {
        return this.query;
    }

    AssertQuery getAssertQuery() {
        return this.assertQuery;
    }


}
