-- -- CREATE TABLE tb2
-- -- (
-- --  section1 text,
-- --  status1 text,
-- --  count1 INT
-- -- )
-- -- ;

-- -- INSERT INTO tb2 VALUES 
-- --   ('A', 'Active', 1), ('A', 'Inactive', 2)
-- -- , ('B', 'Active', 4), ('B', 'Inactive', 5)
-- --                     , ('C', 'Inactive', 7)
-- -- ;

-- SELECT *
-- FROM   crosstab(
--    $$SELECT "section1", "status1", "count1"
--     FROM   tb2
--     ORDER BY 1,2$$
--    ) 
--    AS t 
--    ("section11" text, "status11" text, "count11" int)
-- ;

CREATE TABLE tbl (
   section   text
 , status    text
 , ct        integer 
);

INSERT INTO tbl VALUES 
  ('A', 'Active', 1), ('A', 'Inactive', 2)
, ('B', 'Active', 4), ('B', 'Inactive', 5)
                    , ('C', 'Inactive', 7);  -- ('C', 'Active') is missing

SELECT * 
FROM tb1
;

SELECT *
FROM   crosstab(
   'SELECT section, status, ct
    FROM   tbl
    '  
   ) AS ct ("Section" text, "Active" int, "Inactive" int);  