-- CREATE OR REPLACE FUNCTION inc(val INT)
-- RETURNS INT 
-- AS
-- $$
-- DECLARE temp_name TEXT := 'Arya';

-- BEGIN
    
--     RETURN val + 1;
-- END;
-- $$
-- LANGUAGE PLPGSQL
-- ;

-- SELECT inc(2);

------

-- CREATE OR REPLACE FUNCTION move_money(p1 VARCHAR(128), p2 VARCHAR(128), money_amoount NUMERIC(10,2))
-- RETURNS VOID
-- AS
-- $$
-- BEGIN
    UPDATE bank
    SET amount = amount + money_amoount
    WHERE name = p1
    ;

    PERFORM pg_sleep(10);

    UPDATE bank
    SET amount = amount - money_amoount
    WHERE name = p2
    ;
    
-- END;
-- $$
-- LANGUAGE PLPGSQL
-- ;

-- SELECT move_money('Arya', 'MahSa', 1000)
-- ;

-- CREATE FUNCTION customSum(val1 INT, val2 INT)
-- RETURNS INT AS
-- $$
-- BEGIN 
-- RETURN $1 + $2;
-- END;
-- $$
-- LANGUAGE PLPGSQL
-- ;

-- SELECT customSum(1,2);

