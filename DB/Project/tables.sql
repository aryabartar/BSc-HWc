CREATE TABLE Customer (
    ssn VARCHAR(10), 
    firstname VARCHAR(10),
    lastname VARCHAR(10), 
    customer_id INT, 
    password VARCHAR(256), 
    PRIMARY KEY (ssn)
);

CREATE TABLE PhoneNumber (
    ssn VARCHAR(10), 
    number VARCHAR(11),
    PRIMARY KEY (ssn, number),
    FOREIGN KEY (ssn) REFERENCES Customer(ssn)
);

CREATE TABLE Address (
    ssn VARCHAR(10), 
    address VARCHAR(11),
    PRIMARY KEY (ssn, address),
    FOREIGN KEY (ssn) REFERENCES Customer(ssn)
);

CREATE TABLE Account (
    ID INT AUTO_INCREMENT, 
    amount NUMERIC(10,0), 
    account_type VARCHAR(256), 
    signature_number NUMERIC(4,0), 
    CHECK (ID in ("a1", "a2", "a3")),
    PRIMARY KEY (ID)
);

CREATE TABLE PaymentOrder (
    ID INT AUTO_INCREMENT, 
    account INT, 
    creator VARCHAR(10), 
    acceptor VARCHAR(10), 
    note VARCHAR(1024),
    PRIMARY KEY (ID), 
    FOREIGN KEY (account) REFERENCES Account(ID), 
    FOREIGN KEY (creator) REFERENCES Customer(ssn), 
    FOREIGN KEY (acceptor) REFERENCES Customer(ssn)
);

CREATE TABLE Transaction (
    payment_order INT, 
    destination INT, 
    amount NUMERIC(10,0),
    PRIMARY KEY (payment_order, destination), 
    FOREIGN KEY (payment_order) REFERENCES PaymentOrder(ID), 
    FOREIGN KEY (destination) REFERENCES Account(ID)
);
 
CREATE TABLE Bill (
    ID INT AUTO_INCREMENT, 
    account INT, 
    amount NUMERIC(10,0),
    bill_type VARCHAR(256), 
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    note VARCHAR(1024),     
    PRIMARY KEY (ID), 
    FOREIGN KEY (account) REFERENCES Account(ID), 
    CHECK (bill_type IN ("b1", "b2"))
);

CREATE TABLE AccountOwner (
    customer VARCHAR(10), 
    account INT, 
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn), 
    FOREIGN KEY (account) REFERENCES Account(ID)
);

CREATE TABLE SignatureAccess (
    customer VARCHAR(10), 
    account INT, 
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn), 
    FOREIGN KEY (account) REFERENCES Account(ID)
);

CREATE TABLE AcceptAccess (
    customer VARCHAR(10), 
    account INT, 
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn), 
    FOREIGN KEY (account) REFERENCES Account(ID)
);

CREATE TABLE ViewAccountAccess (
    customer VARCHAR(10), 
    account INT, 
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn), 
    FOREIGN KEY (account) REFERENCES Account(ID)
);

CREATE TABLE Settings (
    customer VARCHAR(10), 
    account INT, 
    account_name VARCHAR(256), 
    color VARCHAR(256), 
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn), 
    FOREIGN KEY (account) REFERENCES Account(ID)
);

CREATE TABLE Signature (
    customer VARCHAR(10), 
    payment_order INT, 
    PRIMARY KEY (customer, payment_order), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn), 
    FOREIGN KEY (payment_order) REFERENCES PaymentOrder(ID)
);
