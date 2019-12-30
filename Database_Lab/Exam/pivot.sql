-- CREATE TABLE evaluations(
--     student VARCHAR(60),
--     subject VARCHAR(60),
--     result  NUMERIC(3,1),
--     day TIMESTAMP DEFAULT NOW()
-- )
-- ;

-- INSERT INTO evaluations VALUES ('Arya', 'Music' , 7);
-- INSERT INTO evaluations VALUES ('Arya', 'Maths' , 4);
-- INSERT INTO evaluations VALUES ('Arya', 'History' , 9);
-- INSERT INTO evaluations VALUES ('Arya', 'Language' , 7);
-- INSERT INTO evaluations VALUES ('Arya', 'Geography' , 9);
-- INSERT INTO evaluations VALUES ('MahSa', 'Music' , 2);
-- INSERT INTO evaluations VALUES ('MahSa', 'Maths' , 10);
-- INSERT INTO evaluations VALUES ('MahSa', 'History' , 7);
-- INSERT INTO evaluations VALUES ('MahSa', 'Language' , 4);
-- INSERT INTO evaluations VALUES ('MahSa', 'Geography' , 10);

-- SELECT * 
-- FROM crosstab( 
--     $$ select student, subject, result
--      from evaluations
--      WHERE student = 'Arya'
--      order by 1,2
--      $$
--     )
--     AS final_result(
--         Student VARCHAR(40), geography NUMERIC,History NUMERIC,Language NUMERIC,Maths NUMERIC,Music NUMERIC
--     )
-- ;

-- SELECT * 
-- FROM crosstab( 
--     $$ 
--     select student, subject, result
--      from evaluations
--      order by 1,2 -- VIP *****
--     $$
--     ,
--     $$
--     select DISTINCT subject
--     from evaluations
--     order by 1
--     $$
--     )
--     AS final_result(
--         Student VARCHAR(40), geography NUMERIC,History NUMERIC,Language NUMERIC,Maths NUMERIC,Music NUMERIC
--     )
-- ;

-- SELECT * 
-- FROM crosstab3( 
--     $$ 
--     select student, subject, result
--      from evaluations
--      order by 1,2 -- VIP *****
--     $$
--     )
-- ;


CREATE TABLE cth2(rowid text, rowdt timestamp, rowdt1 text, attribute text, val text);
INSERT INTO cth2 VALUES('test1','01 March 2004', 't1','temperature','42');
INSERT INTO cth2 VALUES('test1','01 March 2003', 't1','test_result','PASS');
INSERT INTO cth2 VALUES('test1','01 March 2003', 't1','volts','2.6987');
INSERT INTO cth2 VALUES('test2','02 March 2003', 't2','temperature','53');
INSERT INTO cth2 VALUES('test2','02 March 2003', 't2','test_result','FAIL');
INSERT INTO cth2 VALUES('test2','02 March 2003', 't2','test_startdate','01 March 2003');
INSERT INTO cth2 VALUES('test2','02 March 2003', 't2','volts','3.1234');

SELECT * FROM crosstab
(
  'SELECT rowid, rowdt,rowdt1, attribute, val FROM cth2 ORDER BY 1',
  'SELECT DISTINCT attribute FROM cth ORDER BY 1'
)
AS
(
       rowid text,
       rowdt timestamp,
       rowdt1 text,
       temperature int4,
       test_result text,
       test_startdate timestamp,
       volts float8
);