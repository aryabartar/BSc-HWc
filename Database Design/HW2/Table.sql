CREATE TABLE branch(
    ID CHAR(10),
    name VARCHAR(256),
    head VARCHAR(256),
    deputy VARCHAR(256), 
    PRIMARY KEY (ID)
)
;

CREATE TABLE employee(
    ID CHAR(10), 
    first_name VARCHAR(256),
    last_name VARCHAR(256), 
    phone NUMERIC(11,0), 
    address VARCHAR(1024),
    branch_ID CHAR(10), 
    PRIMARY key (ID),
    FOREIGN KEY (branch_ID) REFERENCES branch(ID)
)
;

CREATE TABLE box(
    employee_ID CHAR(10), 
    password CHAR(4), 
    PRIMARY KEY (employee_ID),
    FOREIGN KEY (employee_ID) REFERENCES employee(ID)
)
;

CREATE TABLE insurance(
    ID CHAR(10),
    name VARCHAR(256),
    price NUMERIC(10,2), 
    branch_ID CHAR(10), 
    PRIMARY KEY (ID),
    FOREIGN KEY (branch_ID) REFERENCES branch(ID)
)
;

CREATE TABLE car_insurance(
    ID CHAR(10), 
    number_plate CHAR(7), 
    model VARCHAR(256), 
    color VARCHAR(256), 
    year NUMERIC(4,0),
    PRIMARY KEY (ID), 
    FOREIGN KEY (ID) REFERENCES insurance(ID)
)
;

CREATE TABLE building_insurance(
    ID CHAR(10),
    address VARCHAR(1024), 
    area NUMERIC(10,2), 
    year NUMERIC(4,0), 
    PRIMARY KEY(ID), 
    FOREIGN KEY(ID) REFERENCES insurance(ID)
)
;

CREATE TABLE life_insurance(
    ID CHAR(10), 
    first_name VARCHAR(256), 
    last_name VARCHAR(256), 
    year NUMERIC(4,0), 
    person_id NUMERIC(10,0), 
    PRIMARY KEY (ID), 
    FOREIGN KEY (ID) REFERENCES insurance(ID)
)
;

CREATE TABLE insurer(
    ID CHAR(10), 
    first_name VARCHAR(256), 
    last_name VARCHAR(256), 
    age NUMERIC(3,0), #It is better to handle in view
    gender VARCHAR(256), 
    phone CHAR(10), 
    address VARCHAR(256), 
    password VARCHAR(124), 
    CHECK (LEN(password) > 7), 
    PRIMARY KEY (ID)
)
;

CREATE TABLE message_answer (
    ID CHAR(10), 
    insurerID CHAR(10),
    message VARCHAR(1024), 
    answer VARCHAR(1024), 
    PRIMARY KEY (ID , insurerID), 
    FOREIGN KEY (insurerID) REFERENCES insurer(ID)
)
;

CREATE TABLE buy_insurance(
    insuranceID CHAR(10), 
    insurerID CHAR(10), 
    date TIMESTAMP, 
    paid NUMERIC(10,2), 
    PRIMARY KEY (insuranceID), 
    FOREIGN KEY (insuranceID) REFERENCES insurance(ID), 
    FOREIGN KEY (insurerID) REFERENCES insurer(ID)
)
;