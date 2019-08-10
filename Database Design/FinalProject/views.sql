DROP VIEW FullPaymentOrderInfo;
DROP VIEW AccountAndBillsInfo;

CREATE VIEW FullPaymentOrderInfo(ID, account, amount, creator, note) AS 
    SELECT ID, account, SUM(amount), creator, note 
    FROM PaymentOrder JOIN Transaction ON PaymentOrder.ID = Transaction.payment_order
    GROUP BY ID
; 

CREATE VIEW AccountAndBillsInfo(account_id, account_amount, bill_id, bill_note) AS 
    SELECT Account.ID, Account.amount, Bill.ID, Bill.note
    FROM Account JOIN Bill ON Account.ID = Bill.account 
    ORDER BY Account.ID
;

