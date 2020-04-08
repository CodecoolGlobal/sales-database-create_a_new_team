-- Data inserted to productline

INSERT INTO productline (productline_name)
VALUES ('Productline1'), ('Productline2'), ('Productline3');

-- Data inserted to products

INSERT INTO products
VALUES ('CODE_1', 10, 1),
('CODE_2', 20, 2),
('CODE_3', 30, 3),
('CODE_4', 40, 1);

-- Data inserted to customer_address

INSERT INTO customer_address (addressline_1, addressline_2, city, state, postalCode, country, territory)
VALUES ('Main street 1', 'flat 3', 'city1', 'state1', 'postalcode1', 'country1', 'territory1'),
('Main street 2', 'flat 3', 'city2', 'state2', 'postalcode2', 'country2', 'territory2'),
('Main street 3', 'flat 3', 'city3', 'state3', 'postalcode3', 'country3', 'territory3'),
('Main street 4', null, 'city4', 'state4', 'postalcode4', 'country4', 'territory4');

-- Data inserted to product_prices

INSERT INTO product_prices
VALUES ('CODE_1', cast('2000-01-01' AS date), 77.77),
('CODE_2', cast('2001-02-02' AS date), 77.78),
('CODE_3', cast('2000-01-01' AS date), 77.79),
('CODE_4', cast('2004-05-05' AS date), 77.8);

-- Data inserted to customers

INSERT INTO customers (customer_name, phone_number, address_id, contact_name)
VALUES ('customer1', 'phone1', 1, 'Contact Name 1'),
('customer2', 'phone2', 2, 'Contact Name 2'),
('customer3', 'phone3', 3, 'Contact Name 3'),
('customer4', 'phone4', 4, 'Contact Name 4');

-- Data inserted to dealsize

INSERT INTO dealsize (dealsize) VALUES ('Small'), ('Medium'), ('Large');

-- Data inserted to order_status

INSERT INTO order_status (status)
VALUES ('Shipped'),
('Resolved'),
('Cancelled'),
('In process'),
('Disputed'),
('On Hold');

-- Data inserted to orders

INSERT INTO orders
VALUES (10, cast('2001-02-02' AS date), 1, 1, '11:11:11'),
(20, cast('2002-03-03' AS date), 3, 2, '12:12:12'),
(30, cast('2003-04-04' AS date), 4, 3, '13:13:13'),
(40, cast('2004-05-05' AS date), 6, 4, '14:14:14');

-- Data inserted to order_details

INSERT INTO order_details
VALUES (10, 'CODE_1', 4, 300, 3),
(20, 'CODE_2', 3, 200, 2),
(30, 'CODE_3', 2, 100, 1),
(40, 'CODE_4', 1, 400, 3);