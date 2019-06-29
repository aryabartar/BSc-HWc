SELECT 1;
-- 1 
SELECT DISTINCT Customer.*
FROM Customer JOIN Signature ON Customer.ssn = Signature.customer
;

SELECT 2;
-- 2
SELECT *
FROM Account 
WHERE signature_number < (
    get_signature_access_number(Account.ID)
)
;

SELECT 3;
-- 3 
SELECT PaymentOrder.*
FROM PaymentOrder JOIN Account ON PaymentOrder.account = Account.ID
WHERE Account.signature_number <= ( 
    get_signature_number(PaymentOrder.ID)
);

SELECT 4;
-- 4 
SELECT Transaction.*
FROM Transaction JOIN PaymentOrder ON Transaction.payment_order = PaymentOrder.ID 
WHERE PaymentOrder.ID NOT IN (
    SELECT payment_order
    FROM AcceptPayment
);

SELECT 5;
-- 5 
-- Contains Signatures that are removed because of delete in PaymentOrder.
(SELECT SignatureHistory.* 
FROM SignatureHistory 
WHERE SignatureHistory.customer = '1234453201') UNION (
    SELECT SignatureHistory.*
    FROM PaymentOrderHistory JOIN SignatureHistory ON PaymentOrderHistory.ID = SignatureHistory.payment_order
    WHERE PaymentOrderHistory.creator = '1234453201'
); 


SELECT 6;
-- 6
-- NOTE: I considered that the shared account between two customers means both of them are owner of the same accounts.
SELECT * 
FROM Account    
WHERE Account.ID IN (
    SELECT account
    FROM AccountOwner AS A1
    WHERE A1.customer = '1234453201' AND A1.account IN (
        SELECT A2.account 
        FROM AccountOwner AS A2
        WHERE A2.customer = '1234453202'
    )
);

SELECT 7;
-- 7
-- I assumed that a user can not sign a signed PaymentOrder.
SELECT *
FROM SignatureAccess JOIN PaymentOrder ON SignatureAccess.account = PaymentOrder.account
WHERE SignatureAccess.customer = '1234453211' AND (SignatureAccess.customer, PaymentOrder.ID) NOT IN (
    SELECT customer, payment_order 
    FROM Signature
);

SELECT 8;
-- 8
SELECT *
FROM PaymentOrder 
WHERE PaymentOrder.creator = '1234453202' AND PaymentOrder.creator NOT IN (
    SELECT customer
    FROM SignatureAccess 
    WHERE SignatureAccess.customer = PaymentOrder.creator AND SignatureAccess.account = PaymentOrder.account
);

SELECT 9;
-- 9 
SELECT *
FROM Bill
WHERE Bill.amount > 10 AND Bill.account = 1 AND Bill.bill_type = "b1"; 