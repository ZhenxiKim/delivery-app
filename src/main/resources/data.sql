-- member
INSERT INTO member (member_email,member_password, member_name, member_signup_date, created_dt, modified_dt) VALUES ('test01@gmail.com','$2a$10$cUBEHNQuyU/TALl9SuGBl.Z4Rl9IWSsejMIjbsJEfmS8/9PhUuxIq','tester01',NOW(), NOW(), NOW());
INSERT INTO member (member_email,member_password, member_name, member_signup_date, created_dt, modified_dt) VALUES ('test02@gmail.com','$2a$10$cUBEHNQuyU/TALl9SuGBl.Z4Rl9IWSsejMIjbsJEfmS8/9PhUuxIq','tester02',NOW(), NOW(), NOW());
INSERT INTO member (member_email,member_password, member_name, member_signup_date, created_dt, modified_dt) VALUES ('test03@gmail.com','$2a$10$cUBEHNQuyU/TALl9SuGBl.Z4Rl9IWSsejMIjbsJEfmS8/9PhUuxIq','tester03',NOW(), NOW(), NOW());

-- delivery
INSERT INTO delivery(member_no, order_platform, delivery_status, delivery_dt, address, created_dt, modified_dt)
VALUES (1,'YOGIYO','NOT_STARTED', NOW(),'서울시 성동구',NOW(), NOW());