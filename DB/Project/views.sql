DROP VIEW FullPaymentOrderInfo;

CREATE VIEW FullPaymentOrderInfo(ID, account, amount, creator, note) AS 
    SELECT ID, account, SUM(amount), creator, note 
    FROM PaymentOrder JOIN Transaction ON PaymentOrder.ID = Transaction.payment_order
    GROUP BY ID; 