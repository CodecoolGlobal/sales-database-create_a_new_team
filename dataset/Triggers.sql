DROP TABLE IF EXISTS changes;

-- New table for customer-related changes, including address and contact name

CREATE TABLE changes (
    customer_id int references customers(customer_id),
    table_name varchar(255),
    column_name varchar(255),
    old_value text,
    time_of_change timestamp
);

-- change the customers table, phone number

CREATE OR REPLACE FUNCTION log_customers_changes()
    RETURNS trigger AS
$BODY$
    BEGIN
    IF NEW.phone_number <> OLD.phone_number THEN
        INSERT INTO changes
        VALUES (OLD.customer_id, 'customers', 'phone_number', OLD.phone_number, now());
    END IF;
    RETURN NEW;
    END;
$BODY$
LANGUAGE PLPGSQL;


CREATE TRIGGER phone_number_changes
    BEFORE UPDATE
    ON customers
    FOR EACH ROW
    EXECUTE PROCEDURE log_customers_changes();

-- change the address table, city name

CREATE OR REPLACE FUNCTION log_address_changes()
    RETURNS trigger AS
$BODY$
    BEGIN
    IF NEW.city <> OLD.city THEN
        INSERT INTO changes
        VALUES ((SELECT customers.customer_id FROM customers
                    JOIN customer_address
                    ON customers.address_id = customer_address.address_id
                    WHERE customers.address_id = OLD.address_id),'customer_address', 'city', OLD.city, now()); --choose customer_id
    END IF;
    RETURN NEW;
    END;
$BODY$
LANGUAGE PLPGSQL;

CREATE TRIGGER address_changes
    BEFORE UPDATE
    ON customer_address
    FOR EACH ROW
    EXECUTE PROCEDURE log_address_changes();

-- change contact name in customers

CREATE OR REPLACE FUNCTION log_name_changes()
    RETURNS trigger AS
$BODY$
    BEGIN
    IF NEW.contact_name <> OLD.contact_name THEN
        INSERT INTO changes
        VALUES ((SELECT customers.customer_id FROM customers WHERE customer_id = OLD.customer_id), 'customers', 'contact_name', OLD.contact_name, now());
    END IF;
    RETURN NEW;
    END;
$BODY$
LANGUAGE PLPGSQL;

CREATE TRIGGER name_changes
    BEFORE UPDATE
    ON customers
    FOR EACH ROW
    EXECUTE PROCEDURE log_name_changes();

-- Function to get order number by quarter year;

CREATE OR REPLACE FUNCTION getQuarterYear(qy integer) RETURNS TABLE (orderNumber integer)
AS $$
BEGIN
    RETURN QUERY SELECT order_number FROM orders WHERE extract(quarter FROM order_date) = qy;
end;
$$
    LANGUAGE plpgsql;

-- Function to get order number by month;

CREATE OR REPLACE FUNCTION getMonth(m integer) RETURNS TABLE (orderNumber integer)
AS $$
BEGIN
    RETURN QUERY SELECT order_number FROM orders WHERE extract(MONTH FROM order_date) = m;
end;
$$
    LANGUAGE plpgsql;

-- Function to get order number by year;

CREATE OR REPLACE FUNCTION getYear(y integer) RETURNS TABLE (orderNumber integer)
AS $$
BEGIN
    RETURN QUERY SELECT order_number FROM orders WHERE extract(YEAR FROM order_date) = y;
end;
$$
    LANGUAGE plpgsql;