package com.codecool.zsana.salesdb;

public enum QuarterYear {

    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4);

    private final int value;

    QuarterYear(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

}
