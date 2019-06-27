-- 1)
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453201', 'Arya1', 'Khaligh1', 1, '1111');

-- 2)
INSERT INTO Account(amount, account_type, signature_number) VALUES (400, 'a1', 4);

-- 3)
    -- insert
    INSERT INTO SignatureAccess(customer, account) VALUES ('1234453201', 1);

    -- alter
    -- 1
        DELETE FROM SignatureAccess
        WHERE customer = '1234453201' AND account = 1;
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453201', 1);
    -- 2 
        UPDATE SignatureAccess
        SET account = 1
        WHERE customer = '1234453201' AND account = 3;

    -- delete 
    DELETE FROM SignatureAccess
    WHERE customer = '1234453201' AND account = 1;

-- 4)
    -- insert_to_payment_order trigger will execute on this.
    INSERT INTO PaymentOrder(ID, account, creator, acceptor, note) VALUES (1, 1, '1234453201', null, 'Emergency');

-- 5)
    -- for modification 
        -- update_payment_order trigger will execute on this.
        UPDATE PaymentOrder
        SET note = "Important"
        WHERE ID = 1;

        -- insert_transaction trigger will execute on this.
        INSERT INTO Transaction(payment_order, destination, amount) VALUES (3, 4, 10);

        -- update_transaction trigger will execute on this.
        UPDATE Transaction 
        SET amount = 999
        WHERE payment_order = 2 AND destination = 4;
    
    -- for deletion
    DELETE FROM PaymentOrder 
    WHERE ID = 5;

-- 6) 
    -- for insertion
    INSERT INTO Signature(customer, payment_order) VALUES ('1234453201', 1);
