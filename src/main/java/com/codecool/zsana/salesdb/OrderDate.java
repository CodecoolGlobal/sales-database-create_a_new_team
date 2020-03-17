package com.codecool.zsana.salesdb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;

class OrderDate {

    private int year;
    private int month;
    private int day;
    private Date date;
    private QuarterYear quarterYear;

    // date given as today
    OrderDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        this.date = java.sql.Date.valueOf(localDate);
        String dateString = dateFormat.format(this.date);
        this.year = Integer.valueOf(dateString.substring(0,4));
        this.month = Integer.valueOf(dateString.substring(5,7));
        this.day = Integer.valueOf(dateString.substring(8));
        if (month <= 3) {
            quarterYear = QuarterYear.FIRST;
        } else if (month <= 6) {
            quarterYear = QuarterYear.SECOND;
        } else if (month <= 9) {
            quarterYear = QuarterYear.THIRD;
        } else {
            quarterYear = QuarterYear.FOURTH;
        }
    }

    Date getDate() {
        return this.date;
    }

    int getYear() {
        return year;
    }

    int getMonth() {
        return month;
    }

    int getDay() {
        return day;
    }

    QuarterYear getQuarterYear() {
        return quarterYear;
    }

}
