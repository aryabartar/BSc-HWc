CREATE TABLE Customer (
    ssn VARCHAR(10), 
    firstname VARCHAR(10),
    lastname VARCHAR(10), 
    customer_id INT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    password VARCHAR(256), 
    PRIMARY KEY (ssn)
);

CREATE TABLE CustomerHistory (
    ssn VARCHAR(10), 
    firstname VARCHAR(10),
    lastname VARCHAR(10), 
    customer_id INT,
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    password VARCHAR(256), 
    PRIMARY KEY (ssn, delete_time)
);


CREATE TABLE PhoneNumber (
    ssn VARCHAR(10), 
    number VARCHAR(11),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ssn, number),
    FOREIGN KEY (ssn) REFERENCES Customer(ssn) ON DELETE CASCADE
);

CREATE TABLE PhoneNumberHistory (
    ssn VARCHAR(10), 
    number VARCHAR(11),
    create_time TIMESTAMP ,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ssn, number, delete_time)
);

CREATE TABLE Address (
    ssn VARCHAR(10), 
    address VARCHAR(11),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ssn, address),
    FOREIGN KEY (ssn) REFERENCES Customer(ssn) ON DELETE CASCADE
);

CREATE TABLE AddressHistory (
    ssn VARCHAR(10), 
    address VARCHAR(11),
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ssn, address, delete_time)
);

CREATE TABLE Account (
    ID INT AUTO_INCREMENT, 
    amount NUMERIC(10,0), 
    account_type VARCHAR(256), 
    signature_number NUMERIC(4,0), 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CHECK (account_type in ("a1", "a2", "a3")),
    PRIMARY KEY (ID)
);

CREATE TABLE AccountHistory (
    ID INT AUTO_INCREMENT, 
    amount NUMERIC(10,0), 
    account_type VARCHAR(256), 
    signature_number NUMERIC(4,0), 
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CHECK (account_type in ("a1", "a2", "a3")),
    PRIMARY KEY (ID, delete_time)
);

CREATE TABLE PaymentOrder (
    ID INT AUTO_INCREMENT, 
    account INT, 
    creator VARCHAR(10), 
    note VARCHAR(1024),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID), 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE, 
    FOREIGN KEY (creator) REFERENCES Customer(ssn)
);

CREATE TABLE PaymentOrderHistory (
    ID INT AUTO_INCREMENT, 
    account INT, 
    creator VARCHAR(10), 
    note VARCHAR(1024),
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID, delete_time)
);

CREATE TABLE Transaction (
    payment_order INT, 
    destination INT, 
    amount NUMERIC(10,0),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (payment_order, destination), 
    FOREIGN KEY (payment_order) REFERENCES PaymentOrder(ID) ON DELETE CASCADE, 
    FOREIGN KEY (destination) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE TransactionHistory (
    payment_order INT, 
    destination INT, 
    amount NUMERIC(10,0),
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (payment_order, destination, delete_time)
);
 
CREATE TABLE Bill (
    ID INT AUTO_INCREMENT, 
    account INT, 
    amount NUMERIC(10,0),
    bill_type VARCHAR(256), 
    note VARCHAR(1024),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID), 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE, 
    CHECK (bill_type IN ("b1", "b2"))
);
 
CREATE TABLE BillHistory (
    ID INT AUTO_INCREMENT, 
    account INT, 
    amount NUMERIC(10,0),
    bill_type VARCHAR(256), 
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    note VARCHAR(1024),
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    PRIMARY KEY (ID, delete_time), 
    CHECK (bill_type IN ("b1", "b2"))
);

CREATE TABLE AccountOwner (
    customer VARCHAR(10), 
    account INT, 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE AccountOwnerHistory (
    customer VARCHAR(10), 
    account INT,
    create_time TIMESTAMP ,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account, delete_time)
);

CREATE TABLE SignatureAccess (
    customer VARCHAR(10), 
    account INT, 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE SignatureAccessHistory (
    customer VARCHAR(10), 
    account INT, 
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account, delete_time) 
);


CREATE TABLE AcceptAccess (
    customer VARCHAR(10), 
    account INT, 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE AcceptAccessHistory (
    customer VARCHAR(10), 
    account INT, 
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account, delete_time)
);

CREATE TABLE ViewAccountAccess (
    customer VARCHAR(10), 
    account INT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE ViewAccountAccessHistory (
    customer VARCHAR(10), 
    account INT,
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    PRIMARY KEY (customer, account, delete_time)
);

CREATE TABLE Settings (
    customer VARCHAR(10), 
    account INT, 
    account_name VARCHAR(256), 
    color VARCHAR(256), 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE SettingsHistory (
    customer VARCHAR(10), 
    account INT, 
    account_name VARCHAR(256), 
    color VARCHAR(256),
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    PRIMARY KEY (customer, account, delete_time) 
);

CREATE TABLE Signature (
    customer VARCHAR(10), 
    payment_order INT, 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, payment_order), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (payment_order) REFERENCES PaymentOrder(ID) ON DELETE CASCADE
);

CREATE TABLE SignatureHistory (
    customer VARCHAR(10), 
    payment_order INT, 
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, payment_order, delete_time) 
);

CREATE TABLE AcceptPayment(
    customer VARCHAR(10), 
    payment_order INT, 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (payment_order), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (payment_order) REFERENCES PaymentOrder(ID) ON DELETE CASCADE
);

CREATE TABLE AcceptPaymentHistory(
    customer VARCHAR(10), 
    payment_order INT, 
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (payment_order, delete_time)
);