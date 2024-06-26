CREATE TABLE `accounts` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(255) UNIQUE NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `active` BIT(1) DEFAULT 0,
    `role` VARCHAR(255) NOT NULL,
    `avatar` VARCHAR(255) DEFAULT 'https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg',
     CONSTRAINT `account_chk_1` CHECK (
      (
        `role` in (
          _utf8mb4 'user',
          _utf8mb4 'admin'
        )
      )
    )
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `merchandise_cabinet` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) DEFAULT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    `active` BIT(1) DEFAULT 0
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `room` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `code` VARCHAR(20) UNIQUE KEY NOT NULL,
    `active` BIT(1) DEFAULT 0, 
    `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `customer` (
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `address` varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    `email` varchar(255) UNIQUE KEY NOT NULL,
    `gender` varchar(255) DEFAULT NULL,
    `birthday` date DEFAULT NULL,
    `active` bit (1) DEFAULT 0,
    `account_id` int DEFAULT NULL,
    `room_id` int DEFAULT NULL,
    `merchandise_cabinet_id` int DEFAULT NULL,
    KEY `fk_customer_account` (`account_id`),
    KEY `fk_customer_room` (`room_id`),
    KEY `fk_customer_merchandise_cabinet` (`merchandise_cabinet_id`),
    CONSTRAINT `fk_customer_merchandise_cabinet` FOREIGN KEY (`merchandise_cabinet_id`) REFERENCES `merchandise_cabinet` (`id`),
    CONSTRAINT `fk_customer_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
    CONSTRAINT `fk_customer_account` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`),
    CONSTRAINT `customer_chk_1` CHECK (
      (
        `gender` in (
          _utf8mb4 'male',
          _utf8mb4 'female',
          _utf8mb4 'other'
        )
      )
    )
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
  
  
CREATE TABLE `survey` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    `active` BIT(1) DEFAULT 1
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `question` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `question` VARCHAR(255) DEFAULT NULL,
    `type` VARCHAR(255) NOT NULL,
    `survey_id` int ,
     KEY `fk_question_survey` (`survey_id`),
    CONSTRAINT `fk_question_survey` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `answer` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `answer` TINYINT CHECK (`answer` BETWEEN 1 AND 5),
    `survey_id` int ,
     KEY `fk_answer_survey` (`survey_id`),
    CONSTRAINT `fk_answer_survey` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `customer_survey` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `personal_opinion` varchar(255) DEFAULT NULL,
    `customer_id` int ,
	`survey_id` int ,
    KEY `fk_customer_survey_customer` (`customer_id`),
    CONSTRAINT `fk_customer_survey_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
    KEY `fk_customer_survey_survey` (`survey_id`),
    CONSTRAINT `fk_customer_survey_survey` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;


CREATE TABLE `customer_survey_detail` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `answer_id` int ,
    `question_id` int , 
    `customer_survey_id` int ,
    KEY `fk_customer_survey_answer` (`answer_id`),
    CONSTRAINT `fk_customer_survey_answer` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`),
    KEY `fk_customer_survey_question` (`question_id`),
    CONSTRAINT `fk_customer_survey_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`), 
    KEY `fk_customer_survey_detail_customer_survey` (`customer_survey_id`),
    CONSTRAINT `fk_customer_survey_detail_customer_survey` FOREIGN KEY (`customer_survey_id`) REFERENCES `customer_survey` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

  
CREATE TABLE `relative_park_card` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `description` VARCHAR(255) DEFAULT NULL,
    `date_create` DATE NOT NULL,
    `expiry` DATE NOT NULL,
    `active` BIT(1) DEFAULT 0,
    `cost` DECIMAL(10 , 2 ),
    `customer_id` INT NOT NULL,
    KEY `fk_park_card_customer` (`customer_id`),
    CONSTRAINT `fk_park_card_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `receipt` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `date` DATE NOT NULL,
    `total` DECIMAL(10 , 2 ),
    `is_pay` BIT(1) DEFAULT 0,
    `customer_id` INT NOT NULL,
    KEY `fk_receipt_customer` (`customer_id`),
    CONSTRAINT `fk_receipt_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `service` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(59) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `unit` VARCHAR(59) NOT NULL,
    `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `cost` DECIMAL(10 , 2 ) NOT NULL,
    `active` BIT(1) DEFAULT 0
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `use_service` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `active` BIT(1) DEFAULT 0,
    `service_id` INT NOT NULL,
    `customer_id` INT NOT NULL,
    KEY `fk_use_service_service` (`service_id`),
    KEY `fk_use_service_customer` (`customer_id`),
    CONSTRAINT `fk_use_service_service` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
    CONSTRAINT `fk_use_service_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `detail_receipt` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `active` BIT(1) DEFAULT 0,
    `quantity` FLOAT NOT NULL,
    `cost` DECIMAL(10 , 2 ) NOT NULL,
    `receipt_id` INT NOT NULL,      
    `service_id` INT NOT NULL,
    KEY `fk_receipt_detail_service` (`service_id`),
    KEY `fk_receipt_detail_receipt` (`receipt_id`),
    CONSTRAINT `fk_receipt_detail_service` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
    CONSTRAINT `fk_receipt_detail_receipt` FOREIGN KEY (`receipt_id`) REFERENCES `receipt` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `feedback` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL,
    `customer_id` INT DEFAULT NULL,
    KEY `fk_feedback_customer` (`customer_id`),
    CONSTRAINT `fk_feedback_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `merchandise` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `note` VARCHAR(255) NOT NULL,
    `unit` VARCHAR(50) NOT NULL,
    `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `active` BIT(1) DEFAULT 0
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `merchandise_cabinet_detail` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `quantity` FLOAT DEFAULT 0,
    `date_receive` TIMESTAMP DEFAULT NULL,
    `is_receive` BIT(1) DEFAULT 0,
    `merchandise_id` INT DEFAULT NULL,
    `customer_id` INT DEFAULT NULL,
    KEY `fk_merchandise_cabinet_detail_customer` (`customer_id`),
    CONSTRAINT `fk_merchandise_cabinet_detail_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ,
    KEY `fk_merchandise_cabinet_detail_merchandise` (`merchandise_id`),
    CONSTRAINT `fk_merchandise_cabinet_detail_merchandise` FOREIGN KEY (`merchandise_id`) REFERENCES `merchandise` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;


--  @Trigger
-- -- ?If upddate is_receive=1 then assign CURRENT_TIMESTAMP to this.date_receive --- else ...
-- DELIMITER //
-- CREATE TRIGGER update_timestamp BEFORE UPDATE ON merchandise_cabinet_detail FOR EACH ROW 
-- BEGIN 
--     IF NEW.is_receive = 1 THEN
--         SET NEW.date_receive = CURRENT_TIMESTAMP;
--     ELSE
--         SET NEW.date_receive = NULL;
--     END IF;
-- END;
-- // DELIMITER ;
 

-- @Insert data 

INSERT INTO merchandise (name,note,unit,`date`,active) VALUES
	 ('Merchandise 1','Note 1','Unit 1','2024-04-20 04:12:25',1),
	 ('Merchandise 2','Note 2','Unit 2','2024-04-20 04:12:25',1),
	 ('Merchandise 3','Note 3','Unit 3','2024-04-20 04:12:25',1),
	 ('Merchandise 4','Note 4','Unit 4','2024-04-20 04:12:25',1),
	 ('Merchandise 5','Note 5','Unit 5','2024-04-20 04:12:25',1);
INSERT INTO merchandise_cabinet (name,description,active) VALUES
	 ('Cabinet 1','Description 1',1),
	 ('Cabinet 2','Description 2',1),
	 ('Cabinet 3','Description 3',1),
	 ('Cabinet 4','Description 4',1),
	 ('Cabinet 5',NULL,NULL),
	 ('A08',NULL,NULL),
	 ('A09',NULL,NULL),
	 ('A09',NULL,NULL),
	 ('A20',NULL,NULL),
	 ('A09',NULL,NULL);

INSERT INTO room (code,active,`date`) VALUES
	 ('Room A',1,'2024-04-20 04:11:28'),
	 ('Room B',1,'2024-04-20 04:11:28'),
	 ('Room C',1,'2024-04-20 04:11:28'),
	 ('Room D',1,'2024-04-20 04:11:28'),
	 ('Room E',1,'2024-04-20 04:11:28');
INSERT INTO service (name,description,unit,`date`,cost,active) VALUES
	 ('Service Fee 1','Description 1','Unit 1','2024-04-20 04:12:20',50.00,1),
	 ('Service Fee 2','Description 2','Unit 2','2024-04-20 04:12:20',70.00,1),
	 ('Service Fee 3','Description 3','Unit 3','2024-04-20 04:12:20',60.00,1),
	 ('Service Fee 4','Description 4','Unit 4','2024-04-20 04:12:20',40.00,1),
	 ('Service Fee 5','Description 5','Unit 5','2024-04-20 04:12:20',55.00,1);

INSERT INTO survey (`date`,active) VALUES
	 ('2024-06-21 21:27:49',1);
INSERT INTO answer (answer,survey_id) VALUES
	 (3,1),
	 (5,1),
	 (4,1); 
INSERT INTO question (question,`type`,survey_id) VALUES
	 ('Bạn đánh giá thế nào về thái độ và hiệu quả làm việc của nhân viên vệ sinh tại chung cư? ','hygiene',1),
	 ('Bạn có hài lòng với cơ sở hạ tầng và các tiện ích chung tại chung cư (ví dụ như thang máy, hệ thống điện nước, bãi đậu xe, khu vui chơi, phòng gym, bể bơi, v.v.) không? ','infrastructure',1),
	 ('Bạn đánh giá thế nào về chất lượng dịch vụ quản lý chung cư (ví dụ: xử lý các yêu cầu, hỗ trợ cư dân, tổ chức sự kiện, quản lý bảo trì)?','service',1);
INSERT INTO accounts (username,password,active,`role`,avatar) VALUES
	 ('demo','$2a$10$64GGIvPiX.7be33JVjXxq..Rk6GQUSy1bvVYOwQjrGFoVkLG.Y12i',1,'user','https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg'),
	 ('admin1','$2a$10$64GGIvPiX.7be33JVjXxq..Rk6GQUSy1bvVYOwQjrGFoVkLG.Y12i',1,'admin','https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg'),
	 ('user2','$2a$10$64GGIvPiX.7be33JVjXxq..Rk6GQUSy1bvVYOwQjrGFoVkLG.Y12i',1,'user','https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg'),
	 ('user3','$2a$10$64GGIvPiX.7be33JVjXxq..Rk6GQUSy1bvVYOwQjrGFoVkLG.Y12i',1,'user','https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg');
INSERT INTO customer (name,address,phone_number,email,gender,birthday,active,account_id,room_id,merchandise_cabinet_id) VALUES
	 ('John Doe','123 Street, City','123456789','john@example.com','male','1990-01-01',1,1,1,1),
	 ('Jane Smith','456 Avenue, Town','987654321','jane@example.com','female','1995-05-05',1,2,2,2),
	 ('Alice Johnson','789 Road, Village','111222333','alice@example.com','female','1985-08-10',1,3,3,3),
	 ('Bob Wilson','321 Lane, Countryside','444555666','bob@example.com','male','1980-12-15',1,4,4,4);
     
INSERT INTO use_service (`date`,active,service_id,customer_id) VALUES
	 ('2024-04-20 04:12:25',0,1,1),
	 ('2024-04-20 04:12:25',1,2,2),
	 ('2024-04-20 04:12:25',1,3,3),
	 ('2024-04-20 04:12:25',1,4,4),  
	 ('2024-05-08 00:00:00',1,1,4),
	 ('2024-05-16 00:00:00',1,2,4),
	 ('2024-05-02 00:00:00',0,1,1),
	 ('2024-05-16 00:00:00',0,2,1),
	 ('2024-05-07 00:00:00',0,3,1),
	 ('2024-05-01 00:00:00',1,3,2),
	 ('2024-05-02 00:00:00',1,5,2), 
	 ('2024-06-26 21:35:55',1,1,1),
	 ('2024-06-26 21:37:29',1,2,1),
	 ('2024-06-26 21:38:38',1,3,1);
INSERT INTO feedback (title,content,customer_id) VALUES
	 ('Feedback 1','Content 1',1),
	 ('Feedback 2','Content 2',2),
	 ('Feedback 3','Content 3',3),
	 ('Feedback 4','Content 4',4),
	 ('demo','demo content',1),
	 ('Have a good day','Some thing go wrong',1),
	 ('demo','demo',1);

INSERT INTO relative_park_card (description,date_create,expiry,active,cost,customer_id) VALUES
	 ('Park Card 1','2022-01-01','2023-01-01',1,111.00,1),
	 ('Park Card 2','2022-02-01','2023-02-01',0,NULL,2),
	 ('Park Card 3','2022-03-01','2023-03-01',0,NULL,3),
	 ('Park Card 4','2022-04-01','2023-04-01',0,NULL,4),
	 ('My Park Card','2024-06-26','2024-06-18',1,200.00,1);
INSERT INTO receipt (`date`,total,is_pay,customer_id) VALUES
	 ('2024-06-11',16400000.00,1,1),
	 ('2024-06-18',2442.00,0,2),
	 ('2024-07-26',12500000.00,0,1);
INSERT INTO detail_receipt (active,quantity,cost,receipt_id,service_id) VALUES
	 (1,1.0,1.00,1,1),
	 (1,2.0,2.00,1,2),
	 (1,3.0,3.00,1,3),
	 (1,22.0,1.00,2,2),
	 (1,33.0,2.00,2,3),
	 (1,44.0,3.00,2,5),
	 (1,1.0,11.00,3,2),
	 (1,1.0,1.00,1,1),
	 (1,2.0,2.00,1,2),
	 (1,3.0,3.00,1,3);
INSERT INTO merchandise_cabinet_detail (quantity,date_receive,is_receive,merchandise_id,customer_id) VALUES
	 (10.0,'2022-01-01 00:00:00',1,1,1),
	 (15.0,'2022-02-01 00:00:00',1,2,2),
	 (20.0,'2022-03-01 00:00:00',1,3,3),
	 (25.0,'2022-04-01 00:00:00',1,4,4);
INSERT INTO customer_survey (`date`,personal_opinion,customer_id,survey_id) VALUES 
	 ('2024-06-26 20:53:51','Everything alright ❤️',1,1);
INSERT INTO customer_survey_detail (answer_id,question_id,customer_survey_id) VALUES
	 (1,1,1),
	 (2,2,1),
	 (3,3,1);

