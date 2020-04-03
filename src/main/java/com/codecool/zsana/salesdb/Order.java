package com.codecool.zsana.salesdb;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

class Order {

    private int orderNumber;
    private Timestamp timestamp;
    private int orderStatus;
    private int customerId;

    Order(int orderStatus, int customerId) {
        this.orderStatus = orderStatus;
        this.customerId = customerId;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    int getOrderNumber() {
        return orderNumber;
    }

    void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    Timestamp getTimestamp() {
        return this.timestamp;
    }

    int getOrderStatus() {
        return orderStatus;
    }

    int getCustomerId() {
        return customerId;
    }

    void setTimestamp(Timestamp stamp) {
        this.timestamp = stamp;
    }

    String getDateFromTimestampAsString() {
        Date date = new Date(this.timestamp.getTime());
        return String.valueOf(date);
    }

    java.sql.Date getDateFromTimeStampAsDate() {
        return new Date(this.timestamp.getTime());
    }

    String getTimeFromTimeStampAsString() {
        Time time = new Time(this.timestamp.getTime());
        return String.valueOf(time);
    }

    java.sql.Time getTimeFromTimeStampAsTime() {
        return new Time(this.timestamp.getTime());
    }

}
