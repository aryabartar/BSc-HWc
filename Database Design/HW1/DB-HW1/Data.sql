# Users
INSERT INTO user(user_id, name, address, phone, gender) VALUES (1,'Test user 1','Hafez ave.','09011353908','Female');
INSERT INTO user(user_id, name, address, phone, gender) VALUES (2,'Test user 2','Test Ave 2','09011353909','Male');
INSERT INTO user(user_id, name, address, phone, gender) VALUES (3,'Test user 3','Test Ave 3','09011353910', NULL);
INSERT INTO user(user_id, name, address, phone, gender) VALUES (4,'Test user 4','Test Ave 4','09011353910','Female');
INSERT INTO user(user_id, name, address, phone, gender) VALUES (5,'Test user 5','Test Ave 5','09011353911','Rather not say');
INSERT INTO user(user_id, name, address, phone, gender) VALUES (6,'Test user 6','Test Ave 6','09011353911','Male');

# Category
INSERT INTO category VALUES (1,'Category1');
INSERT INTO category VALUES (2,'Category2');
INSERT INTO category VALUES (3,'Category3');
INSERT INTO category VALUES (4,'Category4');
INSERT INTO category VALUES (5,'Category5');
INSERT INTO category VALUES (6,'Book');
INSERT INTO category VALUES (7,'Sport');

# Product
INSERT INTO product VALUES (6,'Product1',1,10.00);
INSERT INTO product VALUES (7,'Product2',5,18.00);
INSERT INTO product VALUES (8,'Product3',4,20.00);
INSERT INTO product VALUES (9,'Product4',4,5.00);
INSERT INTO product VALUES (10,'Product5',6,75.00);
INSERT INTO product VALUES (11,'Product6',1,55.00);
INSERT INTO product VALUES (12,'Product7',2,155.00);
INSERT INTO product VALUES (13,'Product8',3,45.00);
INSERT INTO product VALUES (14,'Product9',3,40.00);
INSERT INTO product VALUES (15,'Product10',1,48.00);
INSERT INTO product VALUES (16,'Product11',6,99.00);
INSERT INTO product VALUES (17,'Product12',6,199.00);
INSERT INTO product VALUES (18,'Product13',5,99.00);
INSERT INTO product VALUES (19,'Product14',5,59.00);
INSERT INTO product VALUES (20,'Product15',2,29.00);
INSERT INTO product VALUES (23,'Thermos',7,14.00);
INSERT INTO product VALUES (24,'Manchester T-Shirt',7,89.00);
INSERT INTO product VALUES (25,'Shaker',7,19.00);

# Rating
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (1,4,'2019-03-24 07:38:28',6);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (1,4,'2019-03-24 07:38:41',11);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (1,5,'2019-03-24 07:38:45',13);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (1,2,'2019-03-24 07:38:56',14);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (2,5,'2019-03-24 13:45:46',6);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (2,3,'2017-10-09 20:30:00',10);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (2,5,'2019-03-24 07:40:01',19);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (2,5,'2019-03-24 07:40:06',20);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (3,2,'2019-03-24 07:39:03',14);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (3,5,'2019-03-24 07:39:40',16);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (3,5,'2019-03-24 07:39:43',17);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (4,4,'2019-03-24 07:39:11',14);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (5,1,'2019-03-24 07:39:22',7);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (5,4,'2019-03-24 07:39:27',11);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (5,5,'2019-03-24 07:39:31',15);
INSERT INTO rating(user_id, rate, timestamp, product_id) VALUES (5,3,'2019-03-24 07:39:54',18);

# Purchase
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (1,1,6,1,'Recieved','2019-03-24 07:41:56'); 
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (4,1,12,2,'Recieved','2019-03-24 07:42:20') ; 
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (5,2,12,1,'Recieved','2019-03-24 07:42:28') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (6,2,16,1,'Recieved','2019-03-24 07:42:35') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (7,2,18,1,'Recieved','2019-03-24 07:42:38') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (8,2,18,2,'Recieved','2019-03-24 07:42:44') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (9,3,7,1,'Recieved','2019-03-24 07:42:56') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (10,3,7,1,'Sent','2019-03-24 07:43:06') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (11,3,8,4,'Sent','2019-03-24 07:43:14') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (12,3,18,1,'Sent','2019-03-24 07:43:18') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (13,3,19,1,'Sent','2019-03-24 07:43:21') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (14,3,11,2,'Recieved','2019-03-24 07:43:32') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (15,4,9,9,'Recieved','2019-03-24 07:43:57') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (16,4,9,1,'Recieved','2019-03-24 07:44:00') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (17,4,14,2,'Recieved','2019-03-24 07:44:04') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (18,4,18,5,'Recieved','2019-03-24 07:44:12') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (19,4,17,1,'Recieved','2019-03-24 07:44:17') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (20,6,13,1,'Recieved','2019-03-24 07:44:29') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (21,6,14,3,'Recieved','2019-03-24 07:44:36') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (22,6,15,2,'Recieved','2019-03-24 07:44:39') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (23,6,7,2,'Sent','2019-03-24 07:44:49') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (24,1,24,1,'Recieved','2019-03-24 08:10:45') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (25,4,24,1,'Recieved','2019-03-24 08:10:49') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (26,6,25,1,'Recieved','2019-03-24 08:10:59') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (27,5,23,2,'Recieved','2019-03-24 08:11:22') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (28,2,15,2,'Recieved','2017-11-10 20:30:00') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (29,2,20,5,'Recieved','2017-11-11 08:50:22') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (30,1,23,4,'Recieved','2019-03-25 03:38:02') ;
INSERT INTO purchase(order_id, user_id, product_id, quantity, shipping_status, timestamp) VALUES (31,5,25,2,'Recieved','2019-03-25 03:38:32') ;