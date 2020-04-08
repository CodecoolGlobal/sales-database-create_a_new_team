DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS customer_address;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_prices;
DROP TABLE IF EXISTS productline;
DROP TABLE IF EXISTS dealsize;
DROP TABLE IF EXISTS order_status;


CREATE TABLE productline (
    productline_id SERIAL PRIMARY KEY,
    productline_name varchar(255) UNIQUE
);

CREATE TABLE dealsize (
    dealsize_id SERIAL PRIMARY KEY,
    dealsize varchar(255)
);

CREATE TABLE order_status (
    status_id SERIAL PRIMARY KEY,
    status varchar(255)
);

CREATE TABLE products (
    product_code varchar(255) PRIMARY KEY,
    MSRP int,
    productline_id int REFERENCES productline(productline_id)
);

CREATE TABLE customer_address (
    address_id SERIAL PRIMARY KEY,
    addressline_1 text,
    addressline_2 text,
    city varchar(255),
    state varchar(255),
    postalCode varchar(255),
    country varchar(255),
    territory varchar(255)
);

CREATE TABLE product_prices (
    product_code varchar(255) REFERENCES products(product_code),
    order_date date,
    price decimal
);

CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    customer_name text,
    phone_number varchar(255),
    address_id int REFERENCES customer_address(address_id),
    contact_name varchar(255)
);

CREATE TABLE orders (
    order_number int PRIMARY KEY,
    order_date date,
    order_time time(0),
    status_id int REFERENCES order_status(status_id),
    customer_id int REFERENCES customers(customer_id)
);

CREATE TABLE order_details (
    order_number int REFERENCES orders(order_number),
    product_code varchar(255) REFERENCES products(product_code),
    quantity_ordered int,
    sales decimal, -- price * quantity
    dealsize_id int REFERENCES dealsize(dealsize_id)
);
