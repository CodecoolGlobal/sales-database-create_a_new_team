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
                    JOIN OLD
                    ON customers.address_id = OLD.address_id
                    WHERE customers.address_id = OLD.address_id), 'customer_address', 'city', OLD.city, now()); --choose customer_id
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

-- change the contactnames table, first and last name

CREATE OR REPLACE FUNCTION log_first_name_changes()
    RETURNS trigger AS
$BODY$
    BEGIN
    IF NEW.firstname <> OLD.firstname THEN
        INSERT INTO changes
        VALUES ((SELECT customers.customer_id FROM customers
                    JOIN OLD
                    ON customers.contactname_id = OLD.contactname_id
                    WHERE customers.contactname_id = OLD.contactname_id), 'contactnames', 'firstname', OLD.firstname, now()); --choose customer_id
    END IF;
    RETURN NEW;
    END;
$BODY$
LANGUAGE PLPGSQL;

CREATE TRIGGER first_name_changes
    BEFORE UPDATE
    ON contactnames
    FOR EACH ROW
    EXECUTE PROCEDURE log_first_name_changes();


CREATE OR REPLACE FUNCTION log_last_name_changes()
    RETURNS trigger AS
$BODY$
    BEGIN
    IF NEW.lastname <> OLD.lastname THEN
        INSERT INTO changes
        VALUES ((SELECT customers.customer_id FROM customers
                    JOIN OLD
                    ON customers.contactname_id = OLD.contactname_id
                    WHERE customers.contactname_id = OLD.contactname_id), 'contactnames', 'lastname', OLD.lastname, now()); --choose customer_id
    END IF;
    RETURN NEW;
    END;
$BODY$
LANGUAGE PLPGSQL;

CREATE TRIGGER last_name_changes
    BEFORE UPDATE
    ON contactnames
    FOR EACH ROW
    EXECUTE PROCEDURE log_last_name_changes();

