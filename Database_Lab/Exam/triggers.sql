-- CREATE TABLE employees(
--     id SERIAL PRIMARY KEY,
--     first_name VARCHAR(40) NOT NULL,
--     last_name VARCHAR(40) NOT NULL
-- )
-- ;

-- CREATE TABLE employeeslog(
--     id SERIAL PRIMARY KEY,
--     employee_id INT NOT NULL,
--     last_name VARCHAR(40) NOT NULL,
--     changed_on TIMESTAMP NOT NULL
-- )
-- ;

-- CREATE OR REPLACE FUNCTION log_last_name_changes()
-- RETURNS TRIGGER AS
-- $$
-- BEGIN

--     IF NEW.last_name <> OLD.last_name THEN 
--         INSERT INTO employeeslog(employee_id, last_name, changed_on) VALUES (OLD.id , OLD.last_name, NOW());
--     END IF;

--     RETURN NEW;

-- END;
-- $$
-- LANGUAGE PLPGSQL


-- CREATE TRIGGER last_name_changes
--     BEFORE UPDATE
--     ON employees
--     FOR EACH ROW
--     EXECUTE PROCEDURE log_last_name_changes()
-- ;