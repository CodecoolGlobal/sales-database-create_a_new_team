CREATE TYPE quarter_year AS ENUM (1,2,3,4);
CREATE TYPE deal_size AS ENUM ('Small', 'Medium', 'Large');
CREATE TYPE status AS ENUM ('In Process', 'Disputed', 'Shipped', 'Cancelled', 'On Hold', 'Resolved');

CREATE TABLE orderdates (
    orderdates_id date PRIMARY KEY,
    QTR_id quarter_year NOT NULL ,
    month_id int NOT NULL,
    year_id int NOT NULL
);

CREATE TABLE products (
    product_code varchar(255) PRIMARY KEY,
    product_price decimal,
    MSRP int
);

CREATE TABLE customer_address (
    address_id SERIAL PRIMARY KEY,
    addressline_1 text,
    addressline_2 text,
    city varchar(255),
    state varchar(255),
    postalcode varchar(255),
    country varchar(255),
    territory varchar(255)
);

CREATE TABLE contactnames (
    contactname_id SERIAL PRIMARY KEY,
    lastname text,
    firstname text
);

CREATE TABLE productline (
    productline_id SERIAL PRIMARY KEY,
    productline_name varchar(255)
);

CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    customer_name text,
    phone_number varchar(255),
    address_id int REFERENCES customer_address(address_id),
    contactname_id int REFERENCES contactnames(contactname_id)
);

CREATE TABLE orders (
    order_number int PRIMARY KEY,
    orderdates_id date REFERENCES orderdates(orderdates_id),
    status status,
    customer_id int REFERENCES customers(customer_id)
);

CREATE TABLE order_items (
    order_number int REFERENCES orders(order_number),
    product_code int REFERENCES products(product_code),
    quantity_ordered int,
    sales decimal, -- price * quantity
    productline_id int REFERENCES productline(productline_id),
    dealsize deal_size,
    orderline_number int
);

CREATE INDEX ON orderdates (QTR_id);
CREATE INDEX ON orderdates (month_id);
CREATE INDEX ON orderdates (year_id);
