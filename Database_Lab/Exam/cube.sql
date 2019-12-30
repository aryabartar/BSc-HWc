-- CREATE TABLE product (
--     name TEXT, 
--     category TEXT, 
--     price INT
-- )
-- ;

-- INSERT INTO product(name, category, price) VALUES ('p1', 'c1' , 10);
-- INSERT INTO product(name, category, price) VALUES ('p2', 'c1' , 30);
-- INSERT INTO product(name, category, price) VALUES ('p3', 'c1' , 20);
-- INSERT INTO product(name, category, price) VALUES ('p4', 'c2' , 50);
-- INSERT INTO product(name, category, price) VALUES ('p5', 'c2' , 5);

SELECT name, category, sum(price)
FROM product
GROUP BY rollup(name, category)
;

SELECT name, category, sum(price)
FROM product
GROUP BY cube(name, category)
;

SELECT name, category, sum(price)
FROM product
GROUP BY category, cube(name)
;