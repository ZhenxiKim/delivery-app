-- member
INSERT INTO member (member_email,member_password, member_name, member_signup_date, created_dt, modified_dt) VALUES ('test01@gmail.com','$2a$10$q64qyrqzzuxlo2vRuIgGgOLpHqxd95vbvh6l3yfdM9SjpaFNy/bkq','tester01',NOW(), NOW(), NOW());
INSERT INTO member (member_email,member_password, member_name, member_signup_date, created_dt, modified_dt) VALUES ('test02@gmail.com','$2a$10$q64qyrqzzuxlo2vRuIgGgOLpHqxd95vbvh6l3yfdM9SjpaFNy/bkq','tester02',NOW(), NOW(), NOW());
INSERT INTO member (member_email,member_password, member_name, member_signup_date, created_dt, modified_dt) VALUES ('test03@gmail.com','$2a$10$q64qyrqzzuxlo2vRuIgGgOLpHqxd95vbvh6l3yfdM9SjpaFNy/bkq','tester03',NOW(), NOW(), NOW());

-- delivery
INSERT INTO delivery(member_no, order_platform, delivery_status, delivery_dt, address, created_dt, modified_dt) VALUES (1,'YOGIYO','NOT_STARTED', NOW(),'서울시 성동구',NOW(), NOW());
INSERT INTO delivery(member_no, order_platform, delivery_status, delivery_dt, address, created_dt, modified_dt) VALUES (1,'YOGIYO','NOT_STARTED', NOW(),'서울시 강남구',NOW(), NOW());
INSERT INTO delivery(member_no, order_platform, delivery_status, delivery_dt, address, created_dt, modified_dt) VALUES (1,'YOGIYO','NOT_STARTED', NOW(),'서울시 서초구',NOW(), NOW());
INSERT INTO delivery(member_no, order_platform, delivery_status, delivery_dt, address, created_dt, modified_dt) VALUES (2,'YOGIYO','DONE', NOW(),'서울시 서초구',NOW(), NOW());