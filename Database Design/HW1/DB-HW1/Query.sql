SELECT '1';
 # 1
SELECT * FROM product;


SELECT '2';
 # 2
SELECT * FROM product 
WHERE NOT product_id = SOME (
    SELECT product_id 
    FROM rating
    )
;


SELECT '3';
# 3
SELECT DISTINCT name, gender
FROM user NATURAL JOIN purchase
WHERE purchase.product_id IN (
    SELECT product_id
    FROM product
    WHERE category_id IN (
        SELECT category_id
        FROM category
        WHERE name='Sport'
        )
    )
;


SELECT '4';
# 4
SELECT DISTINCT product.*, category.name
FROM product NATURAL JOIN rating JOIN category
    ON  category.category_id = product.category_id
WHERE rating.rate > 4
; 


SELECT '5';
# 5 
SELECT product.*, Sum(purchase.quantity * product.price) AS sale
FROM product NATURAL JOIN purchase
GROUP BY product.product_id
HAVING sale > 100
ORDER BY sale DESC
;


SELECT '6';
# 6
SELECT phone
FROM user
WHERE 500 < (
    SELECT Sum(price * quantity)
    FROM product NATURAL JOIN purchase
    WHERE Date(timestamp)='2019-03-24' 
        AND purchase.user_id = user.user_id
    GROUP BY user_id
    )
;



SELECT '7';
# 7
SELECT user.name, user.phone, Sum(quantity)
FROM user NATURAL JOIN purchase
WHERE purchase.product_id IN (
    SELECT product.product_id
    FROM product JOIN category 
        ON category.category_id = product.category_id
    WHERE category.name = 'Sport'
    )
GROUP BY user.user_id
HAVING 3 < Sum(quantity)
;


SELECT '8';
# 8
SELECT user.name, user.phone 
FROM user
WHERE 20 < (
    SELECT Sum(price * quantity)
    FROM product NATURAL JOIN purchase JOIN category
        ON product.category_id = category.category_id
    WHERE category.name = 'Sport' AND purchase.user_id = user.user_id
    GROUP BY purchase.user_id
    )
;


SELECT '9';
# 9 
SELECT user.name, user.phone
FROM user
WHERE user.user_id IN (
    SELECT pur1.user_id
    FROM product NATURAL JOIN purchase AS pur1 JOIN category 
        ON product.category_id = category.category_id
    WHERE category.name = 'Sport' AND  
        EXISTS (
            SELECT *
            FROM product AS pro2 NATURAL JOIN purchase AS pur2 JOIN category AS cat2
                ON pro2.category_id = cat2.category_id
            WHERE cat2.name = 'Book' AND pur2.user_id = pur1.user_id
        )
)
; 

SELECT '10';
# 10
SELECT product.name, Sum(purchase.quantity * product.price) AS buy
FROM user NATURAL JOIN purchase JOIN product 
    ON purchase.product_id = product.product_id
WHERE user.user_id = 5
GROUP BY product.product_id
;


SELECT '11';
# 11
SELECT product.*, Sum(purchase.quantity) as quantity
FROM product NATURAL JOIN purchase 
GROUP BY product.product_id
ORDER BY quantity DESC
LIMIT 1
;


SELECT '12';
# 12
SELECT category.name, Sum(purchase.quantity) AS sale_amount
FROM category JOIN product ON category.category_id = product.category_id
    JOIN purchase ON product.product_id = purchase.product_id
GROUP BY category.category_id
ORDER BY Sum(purchase.quantity) DESC
LIMIT 1
;


SELECT '13';
# 13
SELECT product.name, Sum(purchase.quantity) as quantity
FROM product NATURAL JOIN purchase 
GROUP BY product.product_id
ORDER BY quantity
LIMIT 3
;

SELECT '14';
# 14
SELECT user.name, user.phone, 
    Sum(purchase.quantity * product.price) AS total_buy
FROM product NATURAL JOIN purchase
    JOIN user ON user.user_id=purchase.user_id
GROUP BY user.user_id
ORDER BY total_buy DESC
LIMIT 1 OFFSET 1
;

SELECT '15';
# 15
SELECT user.name, user.phone, Count(*) AS rate_number
FROM rating NATURAL JOIN user
WHERE rating.timestamp > NOW() - INTERVAL 1 MONTH
GROUP BY user.user_id
ORDER BY rate_number DESC
LIMIT 1 OFFSET 1
;


SELECT '16';
# 16
SELECT t1.phone
FROM (
    SELECT user.*, (last.last_time - first.first_time) AS time_difference
    FROM user NATURAL JOIN (
        SELECT user_id, Max(rating.timestamp) AS last_time
        FROM rating NATURAL JOIN user
        GROUP BY user.user_id 
    ) AS last NATURAL JOIN (
        SELECT user_id, Min(rating.timestamp) AS first_time
        FROM rating NATURAL JOIN user
        GROUP BY user.user_id 
    ) AS first 
) AS t1 
WHERE t1.time_difference >= All (
    SELECT (last.last_time - first.first_time) AS last_time
    FROM user NATURAL JOIN (
        SELECT user_id, Max(rating.timestamp) AS last_time
        FROM rating NATURAL JOIN user
        GROUP BY user.user_id
    ) AS last NATURAL JOIN (
        SELECT user_id, Min(rating.timestamp) AS first_time
        FROM rating NATURAL JOIN user
        GROUP BY user.user_id 
    ) AS first
)
;


SELECT '17';
# 17
SELECT user_payment.phone
FROM (
    SELECT user.*, Sum(purchase.quantity * product.price) as total_payment
    FROM purchase NATURAL JOIN product JOIN user
        ON purchase.user_id=user.user_id
    WHERE Date(purchase.timestamp)='2019-03-24'
    GROUP BY user.user_id
    ) AS user_payment
WHERE user_payment.total_payment > (
    SELECT Avg(pay.total_payment)
    FROM (
        SELECT Sum(purchase.quantity * product.price) as total_payment
        FROM purchase NATURAL JOIN product JOIN user
          ON purchase.user_id=user.user_id
        WHERE Date(purchase.timestamp)='2019-03-24'
        GROUP BY user.user_id
    ) AS pay
  )
; 