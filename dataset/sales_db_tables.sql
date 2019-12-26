CREATE TYPE quarter_year AS ENUM (1,2,3,4);
CREATE TYPE deal_size AS ENUM ('Small', 'Medium', 'Large');

CREATE TABLE orderdates (
    QTR_id quarter_year PRIMARY KEY,
    month_id int PRIMARY KEY,
    year_id int PRIMARY KEY,
    CONSTRAINT orderdates_id PRIMARY KEY (QTR_id, month_id, year_id)
);

CREATE TABLE products (
    product_code int PRIMARY KEY,
    product_price float,
    MSRP float
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



