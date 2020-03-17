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
        String query = "INSERT INTO orders VALUES (?, ?, ?, ?);";
        PreparedStatement ps = null;
        Integer orderNumber = getLastOrderNumber() + 1;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderNumber);
            ps.setDate(2, order.getOrderDate().getDate());
            ps.setInt(3, order.getOrderStatus()); // default: In process
            ps.setInt(4, order.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getFinallyClause(ps, connection);
        }
        return orderNumber;
    }

    void insertToOrderDates(OrderDate orderdate) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO orderdates VALUES (?, cast(? AS quarter_year), ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setDate(1, orderdate.getDate());
            ps.setString(2, String.valueOf(orderdate.getQuarterYear().getValue()));
            ps.setInt(3, orderdate.getMonth());
            ps.setInt(4, orderdate.getYear());
            ps.executeUpdate();
        } catch (PSQLException p) {
            System.out.println("Orderdates_id already exists.");
            p.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            getFinallyClause(ps, connection);
        }
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

    int insertToContactNames(ContactName contactName) {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO contactnames (lastname, firstname) VALUES (?, ?) RETURNING contactname_id;";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, contactName.getLastName());
            ps.setString(2, contactName.getFirstName());
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
        String query = "INSERT INTO customers (customer_name, phone_number, address_id, contactname_id)" +
                " VALUES (?, ?, ?, ?) RETURNING customer_id;";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setInt(3, customer.getAddressId());
            ps.setInt(4, customer.getContactNameId());
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
            ps.setDate(2, price.getOrderDate().getDate());
            ps.setDouble(3, price.getPrice());
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

    void changeContactName(int customerId, String newLastName, String newFirstName) {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE contactnames SET lastname = ?, firstname = ?\n" +
                "WHERE contactname_id = (SELECT customers.contactname_id FROM customers\n" +
                "                        JOIN contactnames\n" +
                "                        ON customers.contactname_id = contactnames.contactname_id\n" +
                "                        WHERE customer_id = ?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, newLastName);
            ps.setString(2, newFirstName);
            ps.setInt(3, customerId);
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
