CREATE TABLE Customer (
    ssn VARCHAR(10), 
    firstname VARCHAR(10),
    lastname VARCHAR(10), 
    customer_id VARCHAR(10), 
    password VARCHAR(256), 
    PRIMARY KEY (ssn)
);

CREATE TABLE PhoneNumber (
    ssn VARCHAR(10), 
    number VARCHAR(11),
    PRIMARY KEY (ssn),
    FOREIGN KEY (ssn) REFERENCES Customer(ssn)
);

CREATE TABLE Address (
    ssn VARCHAR(10), 
    address VARCHAR(11),
    PRIMARY KEY (ssn),
    FOREIGN KEY (ssn) REFERENCES Customer(ssn)
);

CREATE TABLE Account (
    ID VARCHAR(10), 
    amount NUMERIC(10,0), 
    account_type VARCHAR(256), 
    signature_number NUMERIC(4,0), 
    CHECK (ID in ("a1", "a2", "a3")),
    PRIMARY KEY (ID)
);

CREATE TABLE PaymentOrder (
    ID VARCHAR(10), 
    note VARCHAR(256),
    account VARCHAR(10), 
    creator VARCHAR(10), 
    acceptor VARCHAR(10), 
    PRIMARY KEY (ID), 
    FOREIGN KEY (account) REFERENCES Account(ID), 
    FOREIGN KEY (creator) REFERENCES Customer(ssn), 
    FOREIGN KEY (acceptor) REFERENCES Customer(ssn), 
);

CREATE TABLE transaction (
    payment_order VARCHAR(10), 
    destination VARCHAR(10), 
    amount NUMERIC(10,0),
    PRIMARY KEY (payment_order, destination), 
    FOREIGN KEY payment_order REFERENCES PaymentOrder(ID), 
    FOREIGN KEY destination REFERENCES Account(ID)

);
 
CREATE TABLE Bill (
    ID VARCHAR(10) AUTO_INCREMENT, 
    account VARCHAR(10), 
    amount NUMERIC(10,0),
    note VARCHAR(1024), 
    bill_type VARCHAR(256), 
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    PRIMARY KEY (ID), 
    FOREIGN KEY (account) REFERENCES Account(ID), 
    CHECK (bill_type IN ("b1", "b2"))
);

CREATE TABLE AccountOwner (

);