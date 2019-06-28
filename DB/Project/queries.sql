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

-- -- 3 
-- SELECT PaymentOrder.*
-- FROM PaymentOrder JOIN Account ON PaymentOrder.account = Account.ID
-- WHERE Account.signature_number <= (
--     SELECT count(*)
--     FROM Signature 
--     WHERE Signature.payment_order = PaymentOrder.ID
-- );

-- -- 4 
-- SELECT Transaction.*
-- FROM Transaction JOIN PaymentOrder ON Transaction.payment_order = PaymentOrder.ID 
-- WHERE PaymentOrder.ID NOT IN (
--     SELECT payment_order
--     FROM AcceptPayment
-- );

-- 5 RRR

-- -- 6
-- -- NOTE: I considered that the shared account between two customers means both of them are owner of the same accounts.
-- SELECT * 
-- FROM Account    
-- WHERE Account.ID IN (
--     SELECT account
--     FROM AccountOwner AS A1
--     WHERE A1.customer = '1234453201' AND A1.account IN (
--         SELECT A2.account 
--         FROM AccountOwner AS A2
--         WHERE A2.customer = '1234453202'
--     )
-- );

-- -- 7
-- -- I assumed that a user can not sign a signed PaymentOrder.
-- SELECT 
-- FROM (SignatureAccess JOIN PaymentOrder ON SignatureAccess.account = PaymentOrder.account) 
-- WHERE SignatureAccess.customer = '1234453201' AND (SignatureAccess.customer, PaymentOrder.ID) NOT IN (
--     SELECT * 
--     FROM Signature
-- );

-- -- 8
-- SELECT *
-- FROM PaymentOrder 
-- WHERE PaymentOrder.creator = '1234453202' AND PaymentOrder.creator NOT IN (
--     SELECT customer
--     FROM SignatureAccess 
--     WHERE SignatureAccess.customer = PaymentOrder.creator
-- );

-- -- 9 
-- SELECT *
-- FROM Bill
-- WHERE Bill.amount > 100 AND Bill.account = 1 AND Bill.bill_type = "b1"; 