SELECT * 
FROM SignatureAccess JOIN PaymentOrder ON SignatureAccess.account = PaymentOrder.account
WHERE SignatureAccess.customer = '1234453207';
-- WHERE SignatureAccess.customer = '1234453207' AND PaymentOrder.ID = 2;