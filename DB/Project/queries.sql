-- 1 
SELECT DISTINCT Customer.firstname, Customer.lastname
FROM Customer JOIN (
    SELECT customer AS cust_id
    FROM Signature 
) AS C ON Customer.ssn = C.cust_id
;

-- 2
