-- Customer
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453201', 'Arya1', 'Khaligh1', 1, '1111');
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453202', 'Arya2', 'Khaligh2', 2, 'sss');
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453203', 'Arya3', 'Khaligh3', 3, '1sda111');
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453204', 'Arya4', 'Khaligh4', 4, 'helloarya');
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453205', 'Arya5', 'Khaligh5', 5, '1113');
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453206', 'Arya6', 'Khaligh6', 6, '1111');
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453207', 'Arya7', 'Khaligh7', 7, '13211');
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453208', 'Arya8', 'Khaligh8', 8, '111231');
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453209', 'Arya9', 'Khaligh9', 9, '11121');
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453210', 'Arya10', 'Khaligh10', 10, '11d11');
INSERT INTO Customer(ssn, firstname, lastname, customer_id, password) VALUES ('1234453211', 'Arya11', 'Khaligh11', 11, '11ds11');


-- PhoneNumber
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453201', '09011353909');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453201', '09011353910');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453201', '09011353911');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453202', '09011353912');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453202', '09011353913');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453203', '09011353914');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453204', '09011353915');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453204', '09011353916');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453205', '09011353917');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453206', '09011353918');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453207', '09011353919');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453208', '09011353920');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453209', '09011353921');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453210', '09011353922');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453211', '09011353923');
INSERT INTO PhoneNumber(ssn, number) VALUES ('1234453211', '09011353924');


-- PhoneNumber
INSERT INTO Address(ssn, address) VALUES ('1234453201', 'Diagon Alley, London');
INSERT INTO Address(ssn, address) VALUES ('1234453201', 'Rasht st, Tehran');
INSERT INTO Address(ssn, address) VALUES ('1234453202', 'Diagon Alley, London');
INSERT INTO Address(ssn, address) VALUES ('1234453203', 'Diagon Alley, London');
INSERT INTO Address(ssn, address) VALUES ('1234453204', 'Diagon Alley, London');
INSERT INTO Address(ssn, address) VALUES ('1234453205', 'Diagon Alley, London');
INSERT INTO Address(ssn, address) VALUES ('1234453206', 'Diagon Alley, London');
INSERT INTO Address(ssn, address) VALUES ('1234453207', 'Diagon Alley, London');
INSERT INTO Address(ssn, address) VALUES ('1234453208', 'Diagon Alley, London');
INSERT INTO Address(ssn, address) VALUES ('1234453209', 'Diagon Alley, London');
INSERT INTO Address(ssn, address) VALUES ('1234453210', 'Diagon Alley, London');
INSERT INTO Address(ssn, address) VALUES ('1234453211', 'Diagon Alley, London');
INSERT INTO Address(ssn, address) VALUES ('1234453211', 'Rasht st, Tehran');


-- Account 
INSERT INTO Account(ID, amount, account_type, signature_number) VALUES (1, 400, 'a1', 2);
INSERT INTO Account(ID, amount, account_type, signature_number) VALUES (2, 500, 'a2', 2);
INSERT INTO Account(ID, amount, account_type, signature_number) VALUES (3, 10, 'a3', 1);
INSERT INTO Account(ID, amount, account_type, signature_number) VALUES (4, 1000, 'a2',3);


-- Accesses
    -- Account.ID = 1
        -- AccountOwner
        INSERT INTO AccountOwner(customer, account) VALUES ('1234453201', 1);
        INSERT INTO AccountOwner(customer, account) VALUES ('1234453202', 1);
        INSERT INTO AccountOwner(customer, account) VALUES ('1234453203', 1);
        -- SignatureAccess
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453201', 1);
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453203', 1);
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453204', 1);
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453205', 1);
        -- AcceptAccess
        INSERT INTO AcceptAccess(customer, account) VALUES ('1234453203', 1);
        -- ViewAccountAccess 
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453201', 1);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453202', 1);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453203', 1);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453204', 1);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453205', 1);
    
    -- Account.ID = 2
        -- AccountOwner
        INSERT INTO AccountOwner(customer, account) VALUES ('1234453209', 2);
        INSERT INTO AccountOwner(customer, account) VALUES ('1234453210', 2);
        -- SignatureAccess
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453207', 2);
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453208', 2);
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453209', 2);
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453210', 2);
        -- AcceptAccess
        INSERT INTO AcceptAccess(customer, account) VALUES ('1234453207', 2);
        INSERT INTO AcceptAccess(customer, account) VALUES ('1234453208', 2);
        INSERT INTO AcceptAccess(customer, account) VALUES ('1234453210', 2);
        -- ViewAccountAccess 
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453207', 2);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453208', 2);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453209', 2);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453210', 2);
    
    -- Account.ID = 3
        -- AccountOwner
        INSERT INTO AccountOwner(customer, account) VALUES ('12344532011', 3);
        -- SignatureAccess
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453210', 3);
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453211', 3);
        -- AcceptAccess
        INSERT INTO AcceptAccess(customer, account) VALUES ('1234453210', 3);
        INSERT INTO AcceptAccess(customer, account) VALUES ('1234453211', 3);
        -- ViewAccess 
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453208', 3);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453210', 3);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453211', 3);

    -- Account.ID = 4
        -- AccountOwner
        INSERT INTO AccountOwner(customer, account) VALUES ('1234453201', 4);
        INSERT INTO AccountOwner(customer, account) VALUES ('1234453202', 4);
        -- SignatureAccess
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453201', 4);
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453202', 4);
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453203', 4);
        INSERT INTO SignatureAccess(customer, account) VALUES ('1234453204', 4);
        -- AcceptAccess
        INSERT INTO AcceptAccess(customer, account) VALUES ('1234453201', 4);
        INSERT INTO AcceptAccess(customer, account) VALUES ('1234453203', 4);
        -- ViewAccountAccess 
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453201', 4);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453202', 4);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453203', 4);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453204', 4);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453208', 4);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453210', 4);
        INSERT INTO ViewAccountAccess(customer, account) VALUES ('1234453211', 4);
    
    -- Settings
        INSERT INTO Settings(customer, account, account_name, color) VALUES ('1234453201', 1, "Work1", "Red");
        INSERT INTO Settings(customer, account, account_name, color) VALUES ('1234453208', 4, "Work2", "Green");

-- End access


-- PaymentOrder
    -- Account.ID = 1
    INSERT INTO PaymentOrder(ID, account, creator, note) VALUES (1, 1, '1234453201', 'Emergency');
    INSERT INTO PaymentOrder(ID, account, creator, note) VALUES (2, 1, '1234453202', 'Nonsignificant');
    -- Account.ID = 2 
    INSERT INTO PaymentOrder(ID, account, creator, note) VALUES (3, 2, '1234453209', 'Emergency');
    -- Account.ID = 3
    INSERT INTO PaymentOrder(ID, account, creator, note) VALUES (4, 3, '1234453211', 'Emergency');
    -- Account.ID = 4
    INSERT INTO PaymentOrder(ID, account, creator, note) VALUES (5, 4, '1234453201', 'Nonsignificant');


-- Transaction 
    -- for PaymentOrder.ID = 1
    INSERT INTO Transaction(payment_order, destination, amount) VALUES (1, 2, 10);
    INSERT INTO Transaction(payment_order, destination, amount) VALUES (1, 3, 20);
    INSERT INTO Transaction(payment_order, destination, amount) VALUES (1, 4, 50);
    -- for PaymentOrder.ID = 2
    INSERT INTO Transaction(payment_order, destination, amount) VALUES (2, 1, 100);
    INSERT INTO Transaction(payment_order, destination, amount) VALUES (2, 4, 150);
    -- for PaymentOrder.ID = 3
    INSERT INTO Transaction(payment_order, destination, amount) VALUES (3, 1, 200);
    -- for PaymentOrder.ID = 4
    INSERT INTO Transaction(payment_order, destination, amount) VALUES (4, 2, 50);
    INSERT INTO Transaction(payment_order, destination, amount) VALUES (4, 3, 50);
    -- for PaymentOrder.ID = 5
    INSERT INTO Transaction(payment_order, destination, amount) VALUES (5, 1, 1200);


-- Signature
    -- Account.ID = 1
    INSERT INTO Signature(customer, payment_order) VALUES ('1234453201', 1);
    INSERT INTO Signature(customer, payment_order) VALUES ('1234453203', 1);
    -- Account.ID = 2
    INSERT INTO Signature(customer, payment_order) VALUES ('1234453207', 2);

-- AcceptPayment
    INSERT INTO AcceptPayment(customer, payment_order) VALUES ('1234453201', 1);
    INSERT INTO AcceptPayment(customer, payment_order) VALUES ('1234453210', 3);
