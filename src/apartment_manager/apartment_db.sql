-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: apartment_db
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` bit(1) DEFAULT b'0',
  `role` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  CONSTRAINT `accounts_chk_1` CHECK ((`role` in (_utf8mb4'user',_utf8mb4'admin')))
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'user1','password1',_binary '','user','https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg'),(2,'admin1','password1',_binary '','admin','https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg'),(3,'user2','password2',_binary '','user','https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg'),(4,'user3','password3',_binary '','user','https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg'),(5,'donglon12','aduvip',NULL,'admin',NULL),(19,'donglonn11','adong3bich',NULL,'user',NULL),(22,'donglonn111','adong3bich',NULL,'user',NULL),(23,'donglonn111m','adong3bich',NULL,'user',NULL),(24,'donglonn123h','adong3bich',NULL,'user',NULL),(25,'donglonn54','adong3bich',NULL,'user',NULL);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `active` bit(1) DEFAULT b'0',
  `account_id` int DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `merchandise_cabinet_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_customer_account` (`account_id`),
  KEY `fk_customer_room` (`room_id`),
  KEY `fk_customer_merchandise_cabinet` (`merchandise_cabinet_id`),
  CONSTRAINT `fk_customer_account` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `fk_customer_merchandise_cabinet` FOREIGN KEY (`merchandise_cabinet_id`) REFERENCES `merchandise_cabinet` (`id`),
  CONSTRAINT `fk_customer_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  CONSTRAINT `customer_chk_1` CHECK ((`gender` in (_utf8mb4'male',_utf8mb4'female',_utf8mb4'other')))
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'John Doe','123 Street, City','123456789','john@example.com','male','1990-01-01',_binary '',1,1,1),(2,'Jane Smith','456 Avenue, Town','987654321','jane@example.com','female','1995-05-05',_binary '',2,2,2),(3,'Alice Johnson','789 Road, Village','111222333','alice@example.com','female','1985-08-10',_binary '',3,3,3),(4,'Bob Wilson','321 Lane, Countryside','444555666','bob@example.com','male','1980-12-15',_binary '',4,4,4),(5,'Dong','654 Drive, Suburb','77788899','eva@example.com','male','1975-07-20',NULL,5,NULL,5),(18,'HUYNH DUY DONG','phuong hiep binh chanh tp thu duc tp ho chi minh','0338688127','hddongqn@gmail.com','male','2000-02-12',NULL,19,NULL,NULL),(21,'HUYNH DUY DONG','phuong hiep binh chanh tp thu duc tp ho chi minh','0338688127','donghuynh2422@gmail.com','male',NULL,NULL,22,1,7),(22,'Dongg','','','huynhduydong92@gmail.comm','female',NULL,NULL,23,5,8),(23,'HUYNH DUY DONG','','','2151053012dong@ou.edu.vnk','male',NULL,NULL,24,1,9),(24,'HUYNH DUY DONG','phuong hiep binh chanh tp thu duc tp ho chi minh','','2151053012dong@ou.edu.vngvg','male',NULL,NULL,25,4,10);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_receipt`
--

DROP TABLE IF EXISTS `detail_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail_receipt` (
  `id` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT b'0',
  `quantity` float NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `receipt_id` int NOT NULL,
  `service_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_receipt_detail_service` (`service_id`),
  KEY `fk_receipt_detail_receipt` (`receipt_id`),
  CONSTRAINT `fk_receipt_detail_receipt` FOREIGN KEY (`receipt_id`) REFERENCES `receipt` (`id`),
  CONSTRAINT `fk_receipt_detail_service` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_receipt`
--

LOCK TABLES `detail_receipt` WRITE;
/*!40000 ALTER TABLE `detail_receipt` DISABLE KEYS */;
INSERT INTO `detail_receipt` VALUES (1,_binary '',3,150.00,1,1),(2,_binary '',2,180.00,2,2),(3,_binary '',2,170.00,3,3),(5,_binary '',3,160.00,5,5);
/*!40000 ALTER TABLE `detail_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feedback_customer` (`customer_id`),
  CONSTRAINT `fk_feedback_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'Feedback 1','Content 1',1),(2,'Feedback 2','Content 2',2),(3,'Feedback 3','Content 3',3),(4,'Feedback 4','Content 4',4),(5,'Feedback 5','Content 5',5);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchandise`
--

DROP TABLE IF EXISTS `merchandise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merchandise` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `note` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `unit` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchandise`
--

LOCK TABLES `merchandise` WRITE;
/*!40000 ALTER TABLE `merchandise` DISABLE KEYS */;
INSERT INTO `merchandise` VALUES (1,'Merchandise 1','Note 1','Unit 1','2024-04-19 21:12:25',_binary ''),(2,'Merchandise 2','Note 2','Unit 2','2024-04-19 21:12:25',_binary ''),(3,'Merchandise 3','Note 3','Unit 3','2024-04-19 21:12:25',_binary ''),(4,'Merchandise 4','Note 4','Unit 4','2024-04-19 21:12:25',_binary ''),(5,'Merchandise 5','Note 5','Unit 5','2024-04-19 21:12:25',_binary '');
/*!40000 ALTER TABLE `merchandise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchandise_cabinet`
--

DROP TABLE IF EXISTS `merchandise_cabinet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merchandise_cabinet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchandise_cabinet`
--

LOCK TABLES `merchandise_cabinet` WRITE;
/*!40000 ALTER TABLE `merchandise_cabinet` DISABLE KEYS */;
INSERT INTO `merchandise_cabinet` VALUES (1,'Cabinet 1','Description 1',_binary ''),(2,'Cabinet 2','Description 2',_binary ''),(3,'Cabinet 3','Description 3',_binary ''),(4,'Cabinet 4','Description 4',_binary ''),(5,'Cabinet 5',NULL,NULL),(7,'A08',NULL,NULL),(8,'A09',NULL,NULL),(9,'A09',NULL,NULL),(10,'A20',NULL,NULL);
/*!40000 ALTER TABLE `merchandise_cabinet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchandise_cabinet_detail`
--

DROP TABLE IF EXISTS `merchandise_cabinet_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merchandise_cabinet_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` float DEFAULT '0',
  `date_receive` timestamp NULL DEFAULT NULL,
  `is_receive` bit(1) DEFAULT b'0',
  `merchandise_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_merchandise_cabinet_detail_customer` (`customer_id`),
  KEY `fk_merchandise_cabinet_detail_merchandise` (`merchandise_id`),
  CONSTRAINT `fk_merchandise_cabinet_detail_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_merchandise_cabinet_detail_merchandise` FOREIGN KEY (`merchandise_id`) REFERENCES `merchandise` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchandise_cabinet_detail`
--

LOCK TABLES `merchandise_cabinet_detail` WRITE;
/*!40000 ALTER TABLE `merchandise_cabinet_detail` DISABLE KEYS */;
INSERT INTO `merchandise_cabinet_detail` VALUES (1,10,'2021-12-31 17:00:00',_binary '',1,1),(2,15,'2022-01-31 17:00:00',_binary '',2,2),(3,20,'2022-02-28 17:00:00',_binary '',3,3),(4,25,'2022-03-31 17:00:00',_binary '',4,4),(5,30,'2022-04-30 17:00:00',_binary '',5,5);
/*!40000 ALTER TABLE `merchandise_cabinet_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `is_pay` bit(1) DEFAULT b'0',
  `customer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_receipt_customer` (`customer_id`),
  CONSTRAINT `fk_receipt_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (1,'2022-01-01',150.00,_binary '',1),(2,'2022-02-01',180.00,_binary '',2),(3,'2022-03-01',170.00,_binary '',3),(4,'2022-04-01',200.00,_binary '',4),(5,'2022-05-01',160.00,_binary '',5);
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relative_park_card`
--

DROP TABLE IF EXISTS `relative_park_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relative_park_card` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_create` date NOT NULL,
  `expiry` date NOT NULL,
  `active` bit(1) DEFAULT b'0',
  `cost` decimal(10,2) NOT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_park_card_customer` (`customer_id`),
  CONSTRAINT `fk_park_card_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relative_park_card`
--

LOCK TABLES `relative_park_card` WRITE;
/*!40000 ALTER TABLE `relative_park_card` DISABLE KEYS */;
INSERT INTO `relative_park_card` VALUES (1,'Park Card 1','2022-01-01','2023-01-01',_binary '',100.00,1),(2,'Park Card 2','2022-02-01','2023-02-01',_binary '',120.00,2),(3,'Park Card 3','2022-03-01','2023-03-01',_binary '',110.00,3),(4,'Park Card 4','2022-04-01','2023-04-01',_binary '',130.00,4),(5,'Park Card 5','2022-05-01','2023-05-01',_binary '',115.00,5);
/*!40000 ALTER TABLE `relative_park_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` bit(1) DEFAULT b'0',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'Room A',_binary '','2024-04-19 21:11:28'),(2,'Room B',_binary '','2024-04-19 21:11:28'),(3,'Room C',_binary '','2024-04-19 21:11:28'),(4,'Room D',_binary '','2024-04-19 21:11:28'),(5,'Room E',_binary '','2024-04-19 21:11:28');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(59) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `unit` varchar(59) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `cost` decimal(10,2) NOT NULL,
  `active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Service Fee 1','Description 1','Unit 1','2024-04-19 21:12:20',50.00,_binary ''),(2,'Service Fee 2','Description 2','Unit 2','2024-04-19 21:12:20',70.00,_binary ''),(3,'Service Fee 3','Description 3','Unit 3','2024-04-19 21:12:20',60.00,_binary ''),(5,'Service Fee 5','Description 5','Unit 5','2024-04-19 21:12:20',55.00,_binary '');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_form`
--

DROP TABLE IF EXISTS `survey_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey_form` (
  `id` int NOT NULL AUTO_INCREMENT,
  `link` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_survey` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_form`
--

LOCK TABLES `survey_form` WRITE;
/*!40000 ALTER TABLE `survey_form` DISABLE KEYS */;
INSERT INTO `survey_form` VALUES (1,'https://survey.com/form1',_binary ''),(2,'https://survey.com/form2',_binary ''),(3,'https://survey.com/form3',_binary ''),(4,'https://survey.com/form4',_binary ''),(5,'https://survey.com/form5',_binary '');
/*!40000 ALTER TABLE `survey_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `use_service`
--

DROP TABLE IF EXISTS `use_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `use_service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `active` bit(1) DEFAULT b'0',
  `service_id` int NOT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_use_service_service` (`service_id`),
  KEY `fk_use_service_customer` (`customer_id`),
  CONSTRAINT `fk_use_service_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_use_service_service` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `use_service`
--

LOCK TABLES `use_service` WRITE;
/*!40000 ALTER TABLE `use_service` DISABLE KEYS */;
INSERT INTO `use_service` VALUES (1,'2024-04-19 21:12:25',_binary '',1,1),(2,'2024-04-19 21:12:25',_binary '',2,2),(3,'2024-04-19 21:12:25',_binary '',3,3),(5,'2024-04-19 21:12:25',_binary '',5,5);
/*!40000 ALTER TABLE `use_service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-20 19:57:46
