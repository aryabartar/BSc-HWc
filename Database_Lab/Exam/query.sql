-- Variables

-- DO $$
-- DECLARE 
-- first_name VARCHAR(128) := 'ARYA';
-- last_name VARCHAR(128) := 'KHALIGH';

-- BEGIN
-- RAISE NOTICE '%', first_name;
-- RAISE NOTICE '%', last_name;

-- END $$


-- -- Transactions
-- BEGIN TRANSACTION;
-- -- BEGIN WORK;
-- -- BEGIN;

-- INSERT INTO a5(c1,c2) VALUES (3,3);
-- COMMIT; -- No save without commit!
-- -- COMMIT TRANSACTION;
-- -- COMMIT WORK;

---------

-- BEGIN; 

-- INSERT INTO bank VALUES('Arya', 10000);
-- INSERT INTO bank VALUES('MahSa', 10000);

-- UPDATE bank
-- SET amount = amount - 1000
-- WHERE name = 'Arya'
-- ;

-- UPDATE bank
-- SET amount = amount + 1000
-- WHERE name = 'MahSa'
-- ;

-- SELECT *
-- FROM bank
-- ;

-- COMMIT;

--------


-- SELECT
--    c1,
--    COUNT (c1)
-- FROM
--    a5
-- GROUP BY
--    c1
-- ;

-- -- Use aggregate functions in HAVING clause
-- -------

-- SELECT (NOW() + INTERVAL '2 hours 20 minutes');

---------
