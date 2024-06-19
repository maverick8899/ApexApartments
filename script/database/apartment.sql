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
    `cost` DECIMAL(10 , 2 ) NOT NULL,
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
  
  
CREATE TABLE `question` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `question` VARCHAR(255) DEFAULT NULL,
    `type` VARCHAR(255) NOT NULL
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `answer` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `answer` TINYINT CHECK (`answer` BETWEEN 1 AND 5)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `survey` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `personal_opinion` varchar(255) DEFAULT NULL
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE `customer_survey` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `answer_id` int NOT NULL,
    `question_id` int NOT NULL, 
    `customer_id` int NOT NULL,
	`survey_id` int NOT NULL,
    KEY `fk_customer_survey_answer` (`answer_id`),
    CONSTRAINT `fk_customer_survey_answer` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`),
    KEY `fk_customer_survey_question` (`question_id`),
    CONSTRAINT `fk_customer_survey_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`), 
    KEY `fk_customer_survey_customer` (`customer_id`),
    CONSTRAINT `fk_customer_survey_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
    KEY `fk_customer_survey_survey` (`survey_id`),
    CONSTRAINT `fk_customer_survey_survey` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`id`)
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
-- ?If upddate is_receive=1 then assign CURRENT_TIMESTAMP to this.date_receive --- else ...
DELIMITER //
CREATE TRIGGER update_timestamp BEFORE UPDATE ON merchandise_cabinet_detail FOR EACH ROW 
BEGIN 
    IF NEW.is_receive = 1 THEN
        SET NEW.date_receive = CURRENT_TIMESTAMP;
    ELSE
        SET NEW.date_receive = NULL;
    END IF;
END;
// DELIMITER ;
 

-- @Insert data
-- Chèn dữ liệu mẫu vào bảng account
INSERT INTO accounts (username, password, active, role, avatar) VALUES
('user1', 'password1', 1, 'user', 'https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg'),
('admin1', 'password1', 1, 'admin', 'https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg'),
('user2', 'password2', 1, 'user', 'https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg'),
('user3', 'password3', 1, 'user', 'https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg'),
('admin2', 'password2', 1, 'admin', 'https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg');

-- Chèn dữ liệu mẫu vào bảng merchandise_cabinet
INSERT INTO merchandise_cabinet (name, description, active) VALUES
('Cabinet 1', 'Description 1', 1),
('Cabinet 2', 'Description 2', 1),
('Cabinet 3', 'Description 3', 1),
('Cabinet 4', 'Description 4', 1),
('Cabinet 5', 'Description 5', 1);

-- Chèn dữ liệu mẫu vào bảng room
INSERT INTO room (code, active, cost) VALUES
('Room A', 1, 200.00),
('Room B', 1, 250.00),
('Room C', 1, 220.00),
('Room D', 1, 270.00),
('Room E', 1, 230.00);

-- Chèn dữ liệu mẫu vào bảng resident
INSERT INTO customer (name, address, phone_number, email, gender, birthday, active, account_id, room_id, merchandise_cabinet_id) VALUES
('John Doe', '123 Street, City', '123456789', 'john@example.com', 'male', '1990-01-01', 1, 1, 1, 1),
('Jane Smith', '456 Avenue, Town', '987654321', 'jane@example.com', 'female', '1995-05-05', 1, 2, 2, 2),
('Alice Johnson', '789 Road, Village', '111222333', 'alice@example.com', 'female', '1985-08-10', 1, 3, 3, 3),
('Bob Wilson', '321 Lane, Countryside', '444555666', 'bob@example.com', 'male', '1980-12-15', 1, 4, 4, 4),
('Eva Brown', '654 Drive, Suburb', '777888999', 'eva@example.com', 'female', '1975-07-20', 1, 5, 5, 5);

-- Chèn dữ liệu mẫu vào bảng park_card
INSERT INTO relative_park_card (description, date_create, expiry, active, cost, customer_id) VALUES
('Park Card 1', '2022-01-01', '2023-01-01', 0, null, 1),
('Park Card 2', '2022-02-01', '2023-02-01', 0, null, 2),
('Park Card 3', '2022-03-01', '2023-03-01', 0, null, 3),
('Park Card 4', '2022-04-01', '2023-04-01', 0, null, 4),
('Park Card 5', '2022-05-01', '2023-05-01', 1, 115.00, 5);

-- Chèn dữ liệu mẫu vào bảng receipt
INSERT INTO receipt (date, total, is_pay, customer_id) VALUES
('2022-01-01', 150.00, 1, 1),
('2022-02-01', 180.00, 1, 2),
('2022-03-01', 170.00, 1, 3),
('2022-04-01', 200.00, 1, 4),
('2022-05-01', 160.00, 1, 5);

-- Chèn dữ liệu mẫu vào bảng service
INSERT INTO service (name, description, unit, cost, active) VALUES
('Service Fee 1', 'Description 1', 'Unit 1', 50.00, 1),
('Service Fee 2', 'Description 2', 'Unit 2', 70.00, 1),
('Service Fee 3', 'Description 3', 'Unit 3', 60.00, 1),
('Service Fee 4', 'Description 4', 'Unit 4', 80.00, 1),
('Service Fee 5', 'Description 5', 'Unit 5', 55.00, 1);

-- Chèn dữ liệu mẫu vào bảng use_service
INSERT INTO use_service (active, service_id, customer_id) VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 3),
(1, 4, 4),
(1, 5, 5);

-- Chèn dữ liệu mẫu vào bảng receipt_detail
INSERT INTO detail_receipt (active, quantity, cost, receipt_id, service_id) VALUES
(1, 3, 150.00, 1, 1),
(1, 2, 180.00, 2, 2),
(1, 2, 170.00, 3, 3),
(1, 4, 200.00, 4, 4),
(1, 3, 160.00, 5, 5);

-- Chèn dữ liệu mẫu vào bảng feedback
INSERT INTO feedback (title, content, customer_id) VALUES
('Feedback 1', 'Content 1', 1),
('Feedback 2', 'Content 2', 2),
('Feedback 3', 'Content 3', 3),
('Feedback 4', 'Content 4', 4),
('Feedback 5', 'Content 5', 5);

-- Chèn dữ liệu mẫu vào bảng merchandise
INSERT INTO merchandise (name, note, unit, active) VALUES
('Merchandise 1', 'Note 1', 'Unit 1', 1),
('Merchandise 2', 'Note 2', 'Unit 2', 1),
('Merchandise 3', 'Note 3', 'Unit 3', 1),
('Merchandise 4', 'Note 4', 'Unit 4', 1),
('Merchandise 5', 'Note 5', 'Unit 5', 1);

-- Chèn dữ liệu mẫu vào bảng merchandise_cabinet_detail
INSERT INTO merchandise_cabinet_detail (quantity, date_receive, is_receive, merchandise_id, customer_id) VALUES
(10, '2022-01-01', 1, 1, 1),
(15, '2022-02-01', 1, 2, 2),
(20, '2022-03-01', 1, 3, 3),
(25, '2022-04-01', 1, 4, 4),
(30, '2022-05-01', 1, 5, 5);

INSERT INTO question (question, type) VALUES 
('Bạn hài lòng với sản phẩm này không?', 'hygiene'),
('Đánh giá về chất lượng dịch vụ của chúng tôi?', 'infrastructure'),
('Bạn sẽ giới thiệu sản phẩm này cho bạn bè không?', 'service');

INSERT INTO answer (answer) VALUES 
(1), (2), (3), (4), (5);

INSERT INTO survey (  personal_opinion) VALUES 
-- Liên kết các cuộc khảo sát với dữ liệu từ bảng customer_survey
('Rất hài lòng'),
( 'Hài lòng'),
( 'Trung bình');

INSERT INTO customer_survey (answer_id, question_id, customer_id, survey_id) VALUES 
-- Dữ liệu mẫu cho khách hàng 1
(5, 1, 1,1),
(4, 2, 1,2),
(3, 3, 1,3),
-- Dữ liệu mẫu cho khách hàng 2
(4, 1, 2,1),
(3, 2, 2,2),
(2, 3, 2,3),
-- Dữ liệu mẫu cho khách hàng 3
(3, 1, 3,1),
(2, 2, 3,2),
(1, 3, 3,3);

-- #