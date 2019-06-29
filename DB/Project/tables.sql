CREATE TABLE Customer (
    ssn VARCHAR(10), 
    firstname VARCHAR(10),
    lastname VARCHAR(10), 
    customer_id INT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    password_update_time TIMESTAMP,
    password VARCHAR(256),
    PRIMARY KEY (ssn),
    UNIQUE (customer_id)
);

CREATE TABLE CustomerHistory (
    history_id INT AUTO_INCREMENT,
    ssn VARCHAR(10), 
    firstname VARCHAR(10),
    lastname VARCHAR(10), 
    customer_id INT,
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    password VARCHAR(256), 
    PRIMARY KEY (history_id)
);


CREATE TABLE PhoneNumber (
    ssn VARCHAR(10), 
    number VARCHAR(11),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (ssn, number),
    FOREIGN KEY (ssn) REFERENCES Customer(ssn) ON DELETE CASCADE
);

CREATE TABLE PhoneNumberHistory (
    history_id INT AUTO_INCREMENT,
    ssn VARCHAR(10), 
    number VARCHAR(11),
    create_time TIMESTAMP ,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (history_id)
);

CREATE TABLE Address (
    ssn VARCHAR(10), 
    address VARCHAR(11),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (ssn, address),
    FOREIGN KEY (ssn) REFERENCES Customer(ssn) ON DELETE CASCADE
);

CREATE TABLE AddressHistory (
    history_id INT AUTO_INCREMENT,
    ssn VARCHAR(10), 
    address VARCHAR(11),
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (history_id)
);

CREATE TABLE Account (
    ID INT AUTO_INCREMENT, 
    amount NUMERIC(10,0), 
    account_type VARCHAR(256), 
    signature_number NUMERIC(4,0), 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CHECK (account_type in ("a1", "a2", "a3")),
    PRIMARY KEY (ID)
);

CREATE TABLE AccountHistory (
    history_id INT AUTO_INCREMENT,
    ID INT, 
    amount NUMERIC(10,0), 
    account_type VARCHAR(256), 
    signature_number NUMERIC(4,0), 
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CHECK (account_type in ("a1", "a2", "a3")),
    PRIMARY KEY (history_id)
);

CREATE TABLE PaymentOrder (
    ID INT AUTO_INCREMENT, 
    account INT, 
    creator VARCHAR(10), 
    note VARCHAR(1024),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (ID), 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE, 
    FOREIGN KEY (creator) REFERENCES Customer(ssn)
);

CREATE TABLE PaymentOrderHistory (
    history_id INT AUTO_INCREMENT,
    ID INT, 
    account INT, 
    creator VARCHAR(10), 
    note VARCHAR(1024),
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (history_id)
);

CREATE TABLE Transaction (
    payment_order INT, 
    destination INT, 
    amount NUMERIC(10,0),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (payment_order, destination), 
    FOREIGN KEY (payment_order) REFERENCES PaymentOrder(ID) ON DELETE CASCADE, 
    FOREIGN KEY (destination) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE TransactionHistory (
    history_id INT AUTO_INCREMENT,
    payment_order INT, 
    destination INT, 
    amount NUMERIC(10,0),
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (history_id)
);
 
CREATE TABLE Bill (
    ID INT AUTO_INCREMENT, 
    account INT, 
    amount NUMERIC(10,0),
    bill_type VARCHAR(256), 
    note VARCHAR(1024),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (ID), 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE, 
    CHECK (bill_type IN ("b1", "b2"))
);
 
CREATE TABLE BillHistory (
    history_id INT AUTO_INCREMENT,
    ID INT, 
    account INT, 
    amount NUMERIC(10,0),
    bill_type VARCHAR(256), 
    note VARCHAR(1024),
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    PRIMARY KEY (history_id)
);

CREATE TABLE AccountOwner (
    customer VARCHAR(10), 
    account INT, 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE AccountOwnerHistory (
    history_id INT AUTO_INCREMENT,
    customer VARCHAR(10), 
    account INT,
    create_time TIMESTAMP ,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (history_id)
);

CREATE TABLE SignatureAccess (
    customer VARCHAR(10), 
    account INT, 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE SignatureAccessHistory (
    history_id INT AUTO_INCREMENT,
    customer VARCHAR(10), 
    account INT, 
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (history_id) 
);


CREATE TABLE AcceptAccess (
    customer VARCHAR(10), 
    account INT, 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE AcceptAccessHistory (
    history_id INT AUTO_INCREMENT,
    customer VARCHAR(10), 
    account INT, 
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (history_id)
);

CREATE TABLE ViewAccountAccess (
    customer VARCHAR(10), 
    account INT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE ViewAccountAccessHistory (
    history_id INT AUTO_INCREMENT,
    customer VARCHAR(10), 
    account INT,
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    PRIMARY KEY (history_id)
);

CREATE TABLE Settings (
    customer VARCHAR(10), 
    account INT, 
    account_name VARCHAR(256), 
    color VARCHAR(256), 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, account), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (account) REFERENCES Account(ID) ON DELETE CASCADE
);

CREATE TABLE SettingsHistory (
    history_id INT AUTO_INCREMENT,
    customer VARCHAR(10), 
    account INT, 
    account_name VARCHAR(256), 
    color VARCHAR(256),
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    PRIMARY KEY (history_id) 
);

CREATE TABLE Signature (
    customer VARCHAR(10), 
    payment_order INT, 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (customer, payment_order), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (payment_order) REFERENCES PaymentOrder(ID) ON DELETE CASCADE
);

CREATE TABLE SignatureHistory (
    history_id INT AUTO_INCREMENT,
    customer VARCHAR(10), 
    payment_order INT, 
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (history_id) 
);

CREATE TABLE AcceptPayment(
    customer VARCHAR(10), 
    payment_order INT, 
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (payment_order), 
    FOREIGN KEY (customer) REFERENCES Customer(ssn) ON DELETE CASCADE, 
    FOREIGN KEY (payment_order) REFERENCES PaymentOrder(ID) ON DELETE CASCADE
);

CREATE TABLE AcceptPaymentHistory(
    history_id INT AUTO_INCREMENT,
    customer VARCHAR(10), 
    payment_order INT, 
    create_time TIMESTAMP,
    delete_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (history_id)
);