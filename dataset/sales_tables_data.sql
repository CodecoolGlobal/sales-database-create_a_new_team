-- Data inserted to orderdates

INSERT INTO orderdates
SELECT TO_DATE(rtrim(substring(order_date, 1, length(order_date)-4)), 'MM/DD/YYYY'),
       CAST(qtr_id AS quarter_year), CAST(month_id AS integer),
       CAST(year_id AS integer) FROM temp_all_sales ON CONFLICT DO NOTHING;

-- Data inserted to productline

INSERT INTO productline (productline_name)
SELECT DISTINCT productline FROM temp_all_sales;

-- Data inserted to products

INSERT INTO products
SELECT DISTINCT product_code, CAST(msrp AS integer), productline_id
FROM temp_all_sales JOIN productline
ON temp_all_sales.productline = productline.productline_name;

-- Data inserted to customer_address

INSERT INTO customer_address (addressline_1, addressline_2, city, state, postalcode, country, territory)
SELECT DISTINCT addressline1, addressline2, city, state, postal_code, country, territory
FROM temp_all_sales;

-- Data inserted to contactnames

INSERT INTO contactnames (lastname, firstname)
SELECT DISTINCT contact_lastname, contact_firstname FROM temp_all_sales;