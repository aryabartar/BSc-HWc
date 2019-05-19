CREATE TABLE branch(
    ID CHAR(10),
    name VARCHAR(128),
    head VARCHAR(128),
    deputy VARCHAR(128), 
    PRIMARY KEY (ID)
)
;

CREATE TABLE employee(
    ID CHAR(10), 
    first_name VARCHAR(128),
    last_name VARCHAR(128), 
    phone NUMERIC(11,0), 
    address VARCHAR(1024),
    branch_ID CHAR(5), 
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

)
;