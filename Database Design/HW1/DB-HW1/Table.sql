CREATE TABLE user (
    user_id INT AUTO_INCREMENT,
    name VARCHAR(128),
    address VARCHAR(128),
    phone CHAR(11),
    gender VARCHAR(256),
    PRIMARY KEY (user_id)
)
;

CREATE TABLE category(
    category_id INT AUTO_INCREMENT,
    name VARCHAR (256),
    PRIMARY KEY (category_id)
)
; 

CREATE TABLE product(
    product_id INT AUTO_INCREMENT,
    name VARCHAR(256),
    category_id INT,
    price NUMERIC(10,2),
    PRIMARY KEY (product_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
)
; 

CREATE TABLE rating(
    user_id INT,
    product_id INT,
    rate INT(1),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
)
; 

CREATE TABLE purchase (
    order_id INT AUTO_INCREMENT,
    user_id INT,
    product_id INT,
    quantity INT(4), 
    shipping_status VARCHAR(256),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    PRIMARY KEY (order_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
)
;