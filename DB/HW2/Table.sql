CREATE TABLE branch(
    ID CHAR(5),
    name VARCHAR(128),
    head VARCHAR(128),
    deputy VARCHAR(128), 
    PRIMARY KEY (ID)
)
;

CREATE TABLE employee(
    ID CHAR(5), 
    first_name VARCHAR(128),
    last_name VARCHAR(128), 
    phone NUMERIC(11,0), 
    branch_ID CHAR(5), 
    address VARCHAR(1024),
    PRIMARY key (ID), 
    FOREIGN KEY (branch_ID) REFERENCES branch(ID)
)
;

CREATE TABLE employee_box(
    employee_ID CHAR(5), 
    password CHAR(4), 
    PRIMARY KEY (employee_ID),
    FOREIGN KEY (employee_ID) REFERENCES employee(ID)
)
;


