-- Data inserted to orderdates

INSERT INTO orderdates
SELECT TO_DATE(rtrim(substring(order_date, 1, length(order_date)-4)), 'MM/DD/YYYY'),
       CAST(qtr_id AS quarter_year), CAST(month_id AS integer),
       CAST(year_id AS integer) FROM temp_all_sales ON CONFLICT DO NOTHING;

-- Data inserted to productline

INSERT INTO productline (productline_name)
SELECT DISTINCT productline
FROM temp_all_sales;

-- Data inserted to products

INSERT INTO products
SELECT DISTINCT product_code, CAST(msrp AS integer), productline_id
FROM temp_all_sales
    JOIN productline
        ON temp_all_sales.productline = productline.productline_name;

-- Data inserted to customer_address

INSERT INTO customer_address (addressline_1, addressline_2, city, state, postalCode, country, territory)
SELECT DISTINCT addressline1, addressline2, city, state, postal_code, country, territory
FROM temp_all_sales;

-- Data inserted to contactnames

INSERT INTO contactnames (lastname, firstname)
SELECT DISTINCT contact_lastname, contact_firstname
FROM temp_all_sales;

-- Data inserted to product_prices

INSERT INTO product_prices
SELECT DISTINCT product_code, TO_DATE(rtrim(substring(order_date, 1, length(order_date)-4)), 'MM/DD/YYYY'), CAST(price_each AS decimal)
FROM temp_all_sales;

-- Data inserted to customers

INSERT INTO customers (customer_name, phone_number, address_id, contactname_id)
SELECT DISTINCT customer_name, phone, address_id, contactname_id
FROM temp_all_sales
    JOIN customer_address
        ON customer_address.addressline_1 = temp_all_sales.addressline1
        AND customer_address.city = temp_all_sales.city
        AND customer_address.country = temp_all_sales.country
    JOIN contactnames
        ON contactnames.lastname = temp_all_sales.contact_lastname
        AND contactnames.firstname = temp_all_sales.contact_firstname;

-- Data inserted to dealsize

INSERT INTO dealsize VALUES ('Small');
INSERT INTO dealsize VALUES ('Medium');
INSERT INTO dealsize VALUES ('Large');

-- Data inserted to order_status

INSERT INTO order_status (status)
SELECT DISTINCT status
FROM temp_all_sales;

-- Data inserted to orders

INSERT INTO orders
SELECT DISTINCT CAST(order_number AS integer), TO_DATE(rtrim(substring(order_date, 1, length(order_date)-4)), 'MM/DD/YYYY'), status_id, customer_id
FROM temp_all_sales
    JOIN order_status
        ON order_status.status = temp_all_sales.status
    JOIN customers
        ON customers.customer_name = temp_all_sales.customer_name;

-- Data inserted to order_details

INSERT INTO order_details
SELECT DISTINCT CAST(order_number AS integer), product_code, CAST(quantity_ordered AS integer), CAST(sales AS decimal), dealsize_id
FROM temp_all_sales
    JOIN dealsize
        ON temp_all_sales.dealsize = dealsize.dealsize;