-- -- 1 
-- SELECT DISTINCT Customer.firstname, Customer.lastname
-- FROM Customer JOIN (
--     SELECT customer AS cust_id
--     FROM Signature 
-- ) AS C ON Customer.ssn = C.cust_id
-- ;

-- -- 2
-- SELECT *
-- FROM Account 
-- WHERE signature_number < (
--     SELECT count(*)
--     FROM SignatureAccess
--     WHERE SignatureAccess.account = Account.ID
-- )
-- ;

-- 3 
SELECT * 
FROM PaymentOrder