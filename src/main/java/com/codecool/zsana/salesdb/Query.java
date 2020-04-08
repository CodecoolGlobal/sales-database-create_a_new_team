package com.codecool.zsana.salesdb;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Query {

    private DatabaseConnection dbConnection;

    Query() {
        this.dbConnection = new DatabaseConnection();
    }

    Query(String database, String user, String password) {
        this.dbConnection = new DatabaseConnection(database, user, password);
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

    int insertToOrders(Order order) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO orders VALUES (?, cast(? AS date), ?, ?, cast(? AS time));";
        PreparedStatement ps = null;
        Integer orderNumber = getLastOrderNumber() + 1;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderNumber);
            ps.setTimestamp(2, order.getTimestamp());
            ps.setInt(3, order.getOrderStatus()); // default: In process
            ps.setInt(4, order.getCustomerId());
            ps.setTimestamp(5, order.getTimestamp());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return orderNumber;
    }

    void insertToOrderDetails(OrderDetails orderDetails) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO order_details VALUES (?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderDetails.getOrderNumber());
            ps.setString(2, orderDetails.getProductCode());
            ps.setInt(3, orderDetails.getQuantity());
            ps.setDouble(4, orderDetails.getSales());
            ps.setInt(5, orderDetails.getDealSizeId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }

    }

    int insertToCustomerAddress(Address address) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO customer_address (addressline_1, addressline_2, city," +
                "state, postalcode, country, territory) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING address_id;";
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
            ps.executeQuery();
            ResultSet result = ps.getResultSet();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return -1;
    }

    int insertIntoCustomers(Customer customer) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO customers (customer_name, phone_number, address_id, contact_name)" +
                " VALUES (?, ?, ?, ?) RETURNING customer_id;";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setInt(3, customer.getAddressId());
            ps.setString(4, customer.getContactName());
            ps.executeQuery();
            ResultSet result = ps.getResultSet();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return -1;
    }

    void insertToProductPrices(ProductPrice price) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO product_prices VALUES (?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, price.getProductCode());
            ps.setDate(2, price.getDate());
            ps.setDouble(3, price.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    void addNewProductLine(String productLine) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO productline (productline_name) VALUES (?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, productLine);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    void addNewProduct(Product product) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO products VALUES (?, ?, ?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getProductCode());
            ps.setInt(2, product.getMsrp());
            ps.setInt(3, product.getProductLineId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    void changePhoneNumber(int id, String phoneNumber) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE customers SET phone_number = ? WHERE customer_id = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, phoneNumber);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    void changeCity(int customerId, String newCityName) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE customer_address " +
                "SET city = ? " +
                "WHERE address_id = (SELECT customers.address_id FROM customers\n" +
                "                    JOIN customer_address\n" +
                "                    ON customers.address_id = customer_address.address_id\n" +
                "                    WHERE customer_id = ?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, newCityName);
            ps.setInt(2, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    void changeContactName(int customerId, String contactName) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE customers SET contact_name = ? WHERE customer_id = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, contactName);
            ps.setInt(2, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
    }

    List<Integer> getCustomerIds() {
        Connection connection = dbConnection.getConnection();
        String query = "SELECT customer_id FROM customers;";
        PreparedStatement ps = null;
        List<Integer> ids = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ps.executeQuery();
            ResultSet result = ps.getResultSet();
            while (result.next()) {
                ids.add(result.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return ids;
    }

    List<String> getProductCodes() {
        Connection connection = dbConnection.getConnection();
        String query = "SELECT product_code FROM products;";
        PreparedStatement ps = null;
        List<String> codes = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ps.executeQuery();
            ResultSet result = ps.getResultSet();
            while (result.next()) {
                codes.add(result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return codes;
    }

    List<Integer> getProductLineIds() {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        String query = "SELECT productline_id FROM productline;";
        List<Integer> ids = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ps.executeQuery();
            ResultSet result = ps.getResultSet();
            while (result.next()) {
                ids.add(result.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return ids;
    }

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
