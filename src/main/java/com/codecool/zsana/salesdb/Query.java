package com.codecool.zsana.salesdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

    private DatabaseConnection dbConnection;

    Query() {
        this.dbConnection = new DatabaseConnection();
    }

    Integer getLastOrderNumber() {
        Connection connection = dbConnection.getConnection();
        String query = "SELECT MAX(order_number) FROM orders";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                return set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return null;
    }

    public int insertToOrders(Order order) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO orders VALUES (?, ?, ?, ?);";
        PreparedStatement ps = null;
        Integer orderNumber = getLastOrderNumber() + 1;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderNumber);
            ps.setDate(2, order.getOrderDate().getDate());
            ps.setInt(3, 4); // default: In process
            ps.setInt(4, order.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return orderNumber;
    }

    public void insertToOrderdates(Orderdate orderdate) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO orderdates VALUES (?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setDate(1, orderdate.getDate());
            ps.setString(2, String.valueOf(orderdate.getQuarterYear().getValue()));
            ps.setInt(3, orderdate.getMonth());
            ps.setInt(4,orderdate.getYear());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    public void insertToOrderDetails(OrderDetails orderDetails) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO order_details VALUES (?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderDetails.getOrderNumber());
            ps.setString(2, orderDetails.getProductCode());
            ps.setInt(3, orderDetails.getQuantity());
            ps.setInt(4, orderDetails.getSales());
            ps.setInt(5, orderDetails.getDealSizeId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }

    }

    public void insertToCustomerAddress(Address address) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO customer_address (addressline_1, addressline_2, city," +
                "state, postalcode, country, territory) VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, address.getLine1());
            ps.setString(2, address.getLine2());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getState());
            ps.setString(5, address.getPostalCode());
            ps.setString(6, address.getCountry());
            ps.setString(7, address.getTerritory());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    public void insertToContactNames(ContactName contactName) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO contactnames (lastname, firstname) VALUES (?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, contactName.getLastName());
            ps.setString(2, contactName.getFirstName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    public void insertIntoCustomers(Customer customer) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO customers (customer_name, phone_number, address_id, contactname_id)" +
                " VALUES (?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setInt(3, customer.getAddressId());
            ps.setInt(4, customer.getContactNameId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    // change phone number, etc - triggers

    private void getFinallyClause(PreparedStatement ps, Connection connection) {
        try {
            if (ps != null) {
                ps.close();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
