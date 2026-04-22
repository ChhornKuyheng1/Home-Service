-- MySQL dump 10.13  Distrib 9.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: camfix_db
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6i66ijb8twgcqtetl8eeeed6v` (`user_id`),
  CONSTRAINT `FK6i66ijb8twgcqtetl8eeeed6v` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'2026-02-17',13.0947,103.1964,'Home','Default',1,'Street 127, Sangkat Svay Pao, Battambang, 021402, Cambodia'),(2,'2026-02-17',13.091532632861238,103.20848632603884,'Home','Default',2,'Battambang, 021402, Cambodia'),(3,'2026-02-18',13.0947,103.1964,'Home','Default',3,'Street 127, Sangkat Svay Pao, Battambang, 021402, Cambodia'),(4,'2026-02-18',13.0947,103.1964,'Home','Default',4,'Street 127, Sangkat Svay Pao, Battambang, 021402, Cambodia'),(5,'2026-03-03',13.0951,103.2018,'Home','Default',5,'Street 202, Battambang, 021402, Cambodia'),(6,'2026-03-03',13.1084,103.1942,'Home','Default',6,'Battambang, 021402, Cambodia'),(7,'2026-03-03',13.0876,103.2135,'Home','Default',7,'Street 701, Battambang, 021402, Cambodia'),(8,'2026-03-03',13.1209,103.1824,'Home','Default',8,'Sangkat Chamkar Samraong, Battambang, 021402, Cambodia'),(9,'2026-03-03',13.0742,103.2213,'Home','Default',9,'Phum Ou Dambang, Sangkae, Battambang, 021402, Cambodia'),(10,'2026-03-03',13.1127,103.1896,'Home','Default',10,'Battambang, 021402, Cambodia'),(11,'2026-03-03',13.0993,103.1768,'Home','Default',11,'Street 403, Battambang, 021402, Cambodia'),(12,'2026-03-03',13.1331,103.2075,'Home','Default',12,'Battambang, 021402, Cambodia'),(13,'2026-03-03',13.0835,103.2159,'Home','Default',13,'Street 717, Sangkat Preaek Preah Sdaech, Battambang, 021402, Cambodia'),(14,'2026-03-03',13.1176,103.1921,'Home','Default',14,'Battambang, 021402, Cambodia'),(15,'2026-03-13',13.077518474341101,103.18403866142035,'WC','Default',15,'Battambang, 021402, Cambodia'),(16,'2026-03-14',13.095275006291867,103.21329720318317,'hpme','Active',15,'Battambang, 021402, Cambodia'),(17,'2026-03-18',13.089967742229058,103.20431347936392,'36R3+6M2, , Krong Battambang, , Cambodia','Active',16,'Street 301, Battambang, 021402, Cambodia'),(18,'2026-03-18',13.096738957669482,103.21728028357029,'teas','Active',17,'Sangkat Ratanak, Battambang, 021402, Cambodia'),(19,'2026-03-18',13.096737324893404,103.21723904460669,'uuu','Default',17,'Sangkat Ratanak, Battambang, 021402, Cambodia'),(20,'2026-03-18',67.60036709372262,78.50950978696346,'wat','Active',17,'Tazovsky Rayon, Yamalo-Nenets Autonomous Okrug, Ural Federal District, Russia'),(21,'2026-03-18',13.096734385896443,103.21723602712154,'isi','Default',18,'Sangkat Ratanak, Battambang, 021402, Cambodia'),(22,'2026-03-19',13.10210746687446,103.20384576916695,'Home','Default',19,'Wat Bovil, Street 203, Battambang, 021402, Cambodia'),(23,'2026-03-23',13.095103237168136,103.19449961185455,'teas','Default',25,'Battambang, 021402, Cambodia'),(24,'2026-03-23',11.556373974367345,104.92821011692286,'Teas2','Active',25,'Sangkat Boeng Keng Kang Ti Muoy, Khan Boeng Keng Kang, Phnom Penh, 120102, Cambodia'),(25,'2026-03-23',13.096732426565108,103.2172417268157,'jjj','Active',18,'Sangkat Ratanak, Battambang, 021402, Cambodia'),(26,'2026-03-24',13.096733079675548,103.21724507957697,'Work','Active',26,'Sangkat Ratanak, Battambang, 021402, Cambodia'),(27,'2026-03-24',13.096743529442444,103.2172417268157,'Home','Default',26,'Sangkat Ratanak, Battambang, 021402, Cambodia'),(28,'2026-03-24',13.087585444943066,103.19135203957558,'Home','Default',27,'National Road 57, Battambang, 021402, Cambodia'),(29,'2026-03-25',13.095649893621145,103.19548398256302,'Home','Default',28,'Street 100, Battambang, 021402, Cambodia'),(30,'2026-03-25',13.094053679502133,103.1965984404087,'Home','Default',29,'Street 127, Sangkat Svay Pao, Battambang, 021402, Cambodia'),(31,'2026-03-25',13.097906716347975,103.20294890552759,'Home','Default',30,'Wat Kandal, Street 159D, Battambang, 021402, Cambodia'),(32,'2026-04-04',13.112653438179706,103.20867843925953,'Home','Default',31,'Battambang, 021402, Cambodia');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `tel` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9m5ynywp07ppd197nb0nhhlh2` (`tel`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,NULL,'hh@gmail.com','Male','Chhorn Kuyheng','$2a$10$5CIyw3XP2nz2zbq0kd3DveC8JASRSyNfzGdPptNikGiDr2CmUXDOG','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/te0vqbny0juirsfz5ffy','086223129','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNzc0Mjg0NTcxLCJleHAiOjE3ODQ2NTI1NzF9.zaliLNYZA9zF-IcAIssI1HnX2L_8Wvf6uGB5W3FrtdE'),(2,NULL,'admin@gmail.com','Male','admin','$2a$10$fH9cvM.XiHfjElT.jD3XXexD/B6q/7KrGZXDWEJRcfoWG3ZxYQkua',NULL,'09782132','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNzc0MzY1MDg3LCJleHAiOjE3ODQ3MzMwODd9.FfiYdj7gaAfIjtMqzH0KiDq5iySlzB8tXCMJO12a_ao'),(3,NULL,'Vid@gmail.com','Male','Ly David','$2a$10$IvrgCRB5dkH3kzDIUasto.0M61hrEr5kE0QHWV79h3IyIHJDRszi6','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/kq7j6me9fzasxhunnhyi','0964557838','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNzc0ODc2NTc1LCJleHAiOjE3ODUyNDQ1NzV9.mTK2vpKbaAxflexqLa-s92Q-PDOQz9vVNVGytKQYnyw');
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `booking_date` datetime(6) NOT NULL,
  `complete_date` datetime(6) DEFAULT NULL,
  `final_price` double NOT NULL,
  `status` varchar(255) NOT NULL,
  `work_date` date NOT NULL,
  `work_time` time NOT NULL,
  `address_id` bigint NOT NULL,
  `job_focus_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `confirm_date` datetime(6) DEFAULT NULL,
  `in_progress_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoew0nh9dglxn24mxfvqei3yx6` (`address_id`),
  KEY `FK17qc53ceoa61vrybkr5yk8gp0` (`job_focus_id`),
  KEY `FKeyog2oic85xg7hsu2je2lx3s6` (`user_id`),
  CONSTRAINT `FK17qc53ceoa61vrybkr5yk8gp0` FOREIGN KEY (`job_focus_id`) REFERENCES `job_focus` (`id`),
  CONSTRAINT `FKeyog2oic85xg7hsu2je2lx3s6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKoew0nh9dglxn24mxfvqei3yx6` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,'2026-02-17 13:53:57.478753','2026-02-18 00:00:00.000000',70,'Completed','2026-02-19','13:00:00',1,1,2,'2026-02-17 14:00:00.000000','2026-02-19 13:00:00.000000'),(2,'2026-02-17 13:54:35.825440','2026-02-18 00:00:00.000000',50,'Completed','2026-02-18','13:00:00',1,2,3,'2026-02-17 14:00:00.000000','2026-02-18 13:05:00.000000'),(3,'2026-02-18 13:48:33.977471','2026-02-18 00:00:00.000000',50,'Completed','2026-02-17','13:00:00',3,3,4,'2026-02-17 14:00:00.000000','2026-02-17 13:09:00.000000'),(4,'2026-02-18 13:50:53.834183','2026-02-18 00:00:00.000000',50,'Completed','2026-03-01','13:00:00',4,4,3,'2026-02-17 14:00:00.000000','2026-03-16 13:20:00.000000'),(13,'2026-03-02 15:02:00.000000','2026-03-03 00:00:00.000000',50,'Completed','2026-03-03','13:00:00',1,5,3,'2026-03-02 15:25:00.000000','2026-03-03 13:10:00.000000'),(14,'2026-03-13 21:44:00.000000','2026-03-19 00:16:40.051410',50,'Completed','2026-05-20','13:00:00',1,5,3,NULL,NULL),(15,'2026-03-15 16:05:00.000000','2026-03-19 00:21:00.542771',50,'Completed','2026-04-01','08:00:00',1,1,2,NULL,NULL),(16,'2026-03-15 17:07:00.000000',NULL,0,'Completed','2026-04-01','10:00:00',1,2,2,NULL,NULL),(17,'2026-03-18 01:48:00.000000',NULL,0,'Requested','2026-03-19','08:30:00',1,2,2,NULL,NULL),(18,'2026-03-20 16:56:00.000000','2026-03-21 19:17:18.329426',20,'Completed','2026-03-19','08:30:00',1,6,2,NULL,NULL),(19,'2026-03-20 16:58:00.000000','2026-03-21 19:17:57.548382',20,'Completed','2026-03-19','08:30:00',1,6,2,NULL,NULL),(20,'2026-03-20 16:59:00.000000',NULL,0,'InProgress','2026-03-19','08:30:00',1,6,2,NULL,NULL),(21,'2026-03-20 17:01:00.000000',NULL,0,'Cancelled','2026-03-19','08:30:00',1,6,2,NULL,NULL),(22,'2026-03-20 17:05:00.000000',NULL,0,'Cancelled','2026-03-21','08:30:00',1,6,2,NULL,NULL),(23,'2026-03-20 17:06:00.000000',NULL,0,'Upcoming','2026-03-21','08:30:00',1,6,2,NULL,NULL),(24,'2026-03-20 17:33:00.000000',NULL,0,'Cancelled','2026-03-21','08:00:00',21,6,18,NULL,NULL),(25,'2026-03-20 19:32:00.000000','2026-03-28 20:03:36.407344',0,'Completed','2026-03-19','08:30:00',1,6,2,NULL,NULL),(26,'2026-03-20 19:52:00.000000',NULL,0,'Cancelled','2026-03-19','08:30:00',1,6,2,NULL,NULL),(27,'2026-03-20 19:58:00.000000',NULL,0,'InProgress','2026-03-19','08:30:00',1,6,2,NULL,NULL),(28,'2026-03-20 20:01:00.000000',NULL,0,'InProgress','2026-03-19','08:30:00',1,6,2,NULL,NULL),(29,'2026-03-22 18:46:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(30,'2026-03-22 19:05:00.000000',NULL,0,'Requested','2026-03-19','08:30:00',1,6,2,NULL,NULL),(31,'2026-03-23 16:24:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(32,'2026-03-23 16:28:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(33,'2026-03-23 16:29:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(34,'2026-03-23 17:08:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(35,'2026-03-23 17:11:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(36,'2026-03-23 17:11:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(37,'2026-03-23 17:11:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(38,'2026-03-23 17:11:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(39,'2026-03-23 17:30:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(40,'2026-03-23 17:30:00.000000',NULL,0,'Cancelled','2026-03-21','08:30:00',1,6,17,NULL,NULL),(41,'2026-03-23 17:30:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(42,'2026-03-23 17:30:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(43,'2026-03-23 17:30:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(44,'2026-03-23 17:30:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(45,'2026-03-23 17:31:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(46,'2026-03-23 17:31:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(47,'2026-03-23 17:31:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(48,'2026-03-23 17:32:00.000000',NULL,0,'Requested','2026-03-21','08:30:00',1,6,17,NULL,NULL),(49,'2026-03-23 20:06:00.000000','2026-03-23 20:10:03.250486',50,'Completed','2026-03-23','17:00:00',24,6,25,NULL,NULL),(50,'2026-03-25 13:15:00.000000','2026-03-31 19:43:56.460175',50,'Completed','2026-03-25','23:00:00',25,9,18,'2026-03-31 19:42:41.627807','2026-03-31 19:43:46.990064'),(53,'2026-03-25 19:54:00.000000','2026-03-30 21:57:12.341848',10,'Completed','2026-03-26','16:00:00',29,14,28,NULL,NULL),(54,'2026-03-25 20:37:00.000000','2026-03-25 21:21:18.655349',20,'Completed','2026-03-25','21:00:00',29,16,28,NULL,NULL),(55,'2026-03-25 21:23:00.000000',NULL,0,'Cancelled','2026-03-25','22:00:00',29,15,28,NULL,NULL),(56,'2026-03-25 22:23:00.000000',NULL,0,'Requested','2026-03-26','08:30:00',29,1,28,NULL,NULL),(57,'2026-03-25 22:23:00.000000',NULL,0,'Requested','2026-03-26','12:30:00',29,3,28,NULL,NULL),(58,'2026-03-25 22:27:00.000000',NULL,0,'Requested','2026-03-26','00:00:00',29,16,28,NULL,NULL),(59,'2026-03-25 22:59:00.000000',NULL,0,'Cancelled','2026-03-24','08:30:00',1,14,30,NULL,NULL),(60,'2026-03-25 23:01:00.000000','2026-03-28 15:18:42.099558',20,'Completed','2026-03-24','08:30:00',1,16,30,NULL,NULL),(61,'2026-03-25 23:05:00.000000',NULL,0,'Requested','2026-03-24','08:30:00',1,16,30,NULL,NULL),(62,'2026-03-25 23:05:00.000000',NULL,0,'Requested','2026-03-24','08:30:00',1,16,30,NULL,NULL),(63,'2026-03-26 15:40:00.000000',NULL,0,'Requested','2026-04-10','08:00:00',1,2,2,NULL,NULL),(64,'2026-03-28 20:21:00.000000',NULL,0,'Upcoming','2026-03-28','09:00:00',23,13,25,NULL,NULL),(65,'2026-03-28 20:39:00.000000',NULL,0,'Requested','2026-03-24','08:30:00',1,16,30,NULL,NULL),(66,'2026-03-28 20:44:00.000000',NULL,0,'Upcoming','2026-03-24','08:30:00',1,14,30,'2026-03-28 20:45:28.813944',NULL),(67,'2026-03-30 16:27:00.000000',NULL,0,'Requested','2026-03-30','17:00:00',16,14,15,NULL,NULL),(68,'2026-04-02 17:02:00.000000',NULL,0,'Upcoming','2026-04-03','13:00:00',22,13,19,'2026-04-02 17:12:21.943095',NULL),(69,'2026-04-02 17:22:00.000000','2026-04-04 11:01:03.409240',40,'Completed','2026-04-04','08:00:00',22,11,19,'2026-04-02 17:23:18.464411','2026-04-04 11:00:11.796636'),(70,'2026-04-04 13:51:00.000000',NULL,0,'Cancelled','2026-04-05','08:00:00',2,5,2,NULL,NULL),(71,'2026-04-04 14:11:00.000000','2026-04-04 15:18:51.660001',20,'Completed','2026-04-04','14:00:00',15,27,15,'2026-04-04 14:11:45.808524','2026-04-04 15:09:27.980278'),(72,'2026-04-04 15:22:00.000000',NULL,0,'Upcoming','2026-04-04','16:00:00',15,27,15,'2026-04-04 15:22:40.500496',NULL);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_focus`
--

DROP TABLE IF EXISTS `job_focus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_focus` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` bigint NOT NULL,
  `price1` double NOT NULL,
  `price2` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `job_id` bigint NOT NULL,
  `skill_id` bigint NOT NULL,
  `top` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2bqrce92w2g997jn38k5o6x5u` (`job_id`),
  KEY `FKqy50cjghenj5ouwjuwvdgu870` (`skill_id`),
  CONSTRAINT `FK2bqrce92w2g997jn38k5o6x5u` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`id`),
  CONSTRAINT `FKqy50cjghenj5ouwjuwvdgu870` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_focus`
--

LOCK TABLES `job_focus` WRITE;
/*!40000 ALTER TABLE `job_focus` DISABLE KEYS */;
INSERT INTO `job_focus` VALUES (1,'2026-02-17','God job',16,20,50,'Active',1,1,_binary ''),(2,'2026-02-17','God job',24,30,50,'Active',2,2,_binary ''),(3,'2026-02-18','God job',2,30,50,'Active',3,3,_binary ''),(4,'2026-02-18','God job',3,30,50,'Active',4,5,_binary '\0'),(5,'2026-02-18','God job',4,30,50,'Active',5,8,_binary '\0'),(6,'2026-03-19','',1,20,40,'Active',6,13,_binary ''),(7,'2026-03-19','',1,20,40,'Active',6,1,_binary ''),(8,'2026-03-19','yf8tc7yc7tc',6,20,40,'Active',7,2,_binary ''),(9,'2026-03-19','Professional ',3,30,50,'Active',9,2,_binary ''),(10,'2026-03-20','ycycuc',2,20,40,'Active',6,35,_binary ''),(11,'2026-03-25','',60,20,40,'Active',11,3,_binary ''),(13,'2026-03-25','Professional repair for leaking or damaged pipes to restore proper water flow and prevent water damage in your home.',1,50,80,'Active',13,1,_binary ''),(14,'2026-03-25','Professional repair for leaking or damaged pipes to restore proper water flow and prevent water damage in your home.',1,50,80,'Active',13,35,_binary '\0'),(15,'2026-03-25','',1,20,40,'Active',14,1,_binary '\0'),(16,'2026-03-25','',1,20,40,'Active',14,35,_binary '\0'),(17,'2026-03-25','',1,20,40,'Active',14,36,_binary '\0'),(18,'2026-03-30','',1,20,40,'Active',13,37,_binary '\0'),(19,'2026-03-31','',1,20,40,'Active',10,48,_binary '\0'),(20,'2026-03-31','',2,50,80,'Active',16,13,_binary ''),(21,'2026-03-31','',2,100,150,'Active',16,38,_binary ''),(22,'2026-03-31','',1,20,40,'Active',18,3,_binary '\0'),(23,'2026-03-31','',1,20,40,'Active',18,3,_binary ''),(24,'2026-03-31','',1,20,40,'Active',8,3,_binary '\0'),(25,'2026-04-04','',1,20,40,'Active',25,1,_binary ''),(26,'2026-04-04','',1,20,40,'Active',26,44,_binary '\0'),(27,'2026-04-04','',4,20,40,'Active',28,44,_binary '\0');
/*!40000 ALTER TABLE `job_focus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` date NOT NULL,
  `status` varchar(255) NOT NULL,
  `provider_id` bigint NOT NULL,
  `service_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5jat0yyldeu7kjbycgkk96gcv` (`provider_id`),
  KEY `FKjg7q6wxh7rtlhbwdsfor9lbcs` (`service_id`),
  CONSTRAINT `FK5jat0yyldeu7kjbycgkk96gcv` FOREIGN KEY (`provider_id`) REFERENCES `providers` (`user_id`),
  CONSTRAINT `FKjg7q6wxh7rtlhbwdsfor9lbcs` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (1,'2026-02-17','Active',1,1),(2,'2026-02-17','Active',1,2),(3,'2026-02-18','Active',1,3),(4,'2026-02-18','Active',1,6),(5,'2026-02-18','Active',1,5),(6,'2026-03-19','Active',18,1),(7,'2026-03-19','Active',18,2),(8,'2026-03-19','Active',18,3),(9,'2026-03-19','Active',19,2),(10,'2026-03-19','Active',19,8),(11,'2026-03-25','Active',19,3),(13,'2026-03-25','Active',29,1),(14,'2026-03-25','Active',30,1),(15,'2026-03-30','Active',29,2),(16,'2026-03-31','Active',2,2),(17,'2026-03-31','Active',2,1),(18,'2026-03-31','Active',2,3),(19,'2026-03-31','Active',2,5),(20,'2026-03-31','Active',2,8),(21,'2026-03-31','Active',15,1),(22,'2026-03-31','Active',15,6),(23,'2026-03-31','Active',29,3),(24,'2026-03-31','Active',15,2),(25,'2026-04-04','Active',17,1),(26,'2026-04-04','Active',17,6),(27,'2026-04-04','Active',31,9),(28,'2026-04-04','Active',31,6);
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `message` varchar(255) NOT NULL,
  `reference_id` bigint NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9y21adhxn0ayjhfocscqox7bh` (`user_id`),
  CONSTRAINT `FK9y21adhxn0ayjhfocscqox7bh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'2026-03-02 15:02:14.011362',_binary '','',13,'Booking Requested','BOOKING/PROVIDER',1),(2,'2026-03-02 22:20:21.267198',_binary '','You pay1.0forLegacyplan',7,'PAYMENT','PAYMENT',2),(3,'2026-03-02 22:21:56.903728',_binary '','You pay708.0forStandardplan',8,'PAYMENT','PAYMENT',2),(4,'2026-03-13 21:44:41.114958',_binary '','Your Hav new booking',14,'Booking Requested','BOOKING/PROVIDER',1),(5,'2026-03-15 16:05:32.137144',_binary '\0','Your Hav new booking',15,'Booking Requested','BOOKING/PROVIDER',1),(6,'2026-03-15 17:07:49.633831',_binary '\0','Your Hav new booking',16,'Booking Requested','BOOKING/PROVIDER',1),(13,'2026-03-17 23:58:00.719435',_binary '\0','You pay49.9900016784668forStandardplan',19,'PAYMENT','PAYMENT',4),(14,'2026-03-18 00:39:26.474632',_binary '','You pay49.9900016784668forStandardplan',24,'PAYMENT','PAYMENT',7),(15,'2026-03-18 00:49:02.718861',_binary '\0','You pay49.9900016784668forStandardplan',25,'PAYMENT','PAYMENT',4),(16,'2026-03-18 00:49:23.225529',_binary '','You pay49.9900016784668forStandardplan',26,'PAYMENT','PAYMENT',7),(17,'2026-03-18 01:25:18.750374',_binary '\0','You pay49.9900016784668forStandardplan',2,'PAYMENT','PAYMENT',1),(18,'2026-03-18 01:25:48.359560',_binary '','You pay49.9900016784668forStandardplan',3,'PAYMENT','PAYMENT',2),(19,'2026-03-18 01:27:21.808011',_binary '\0','You pay49.9900016784668forStandardplan',4,'PAYMENT','PAYMENT',3),(20,'2026-03-18 01:28:28.330219',_binary '\0','You pay1000.0forPremiumplan',5,'PAYMENT','PAYMENT',6),(21,'2026-03-18 01:48:01.535837',_binary '\0','Your Hav new booking',17,'Booking Requested','BOOKING/PROVIDER',1),(22,'2026-03-18 01:49:07.427279',_binary '','You pay1000.0forPremiumplan',6,'PAYMENT','PAYMENT',7),(23,'2026-03-18 02:03:56.615020',_binary '\0','You pay1000.0forPremiumplan',7,'PAYMENT','PAYMENT',4),(34,'2026-03-18 23:54:38.397917',_binary '','Your subscription is within 5 day expired',18,'Subscription Expired','SUBSCRIPTION',18),(35,'2026-03-19 00:16:40.537700',_binary '\0','Your booking is Completed',3,'BOOKING','BOOKING/USER',3),(36,'2026-03-19 00:21:00.721798',_binary '','Your booking is Completed',2,'BOOKING','BOOKING/USER',2),(37,'2026-03-19 22:49:27.574451',_binary '','You pay0.0forBasic ( Free )plan',13,'PAYMENT','PAYMENT',19),(38,'2026-03-20 16:56:13.358534',_binary '','Your Hav new booking',18,'Booking Requested','BOOKING/PROVIDER',18),(39,'2026-03-20 16:58:31.173243',_binary '','Your Hav new booking',19,'Booking Requested','BOOKING/PROVIDER',18),(40,'2026-03-20 16:59:29.109162',_binary '','Your Hav new booking',20,'Booking Requested','BOOKING/PROVIDER',18),(41,'2026-03-20 17:01:39.287918',_binary '','Your Hav new booking',21,'Booking Requested','BOOKING/PROVIDER',18),(42,'2026-03-20 17:05:23.972623',_binary '','Your Hav new booking',22,'Booking Requested','BOOKING/PROVIDER',18),(43,'2026-03-20 17:06:15.738167',_binary '','Your Hav new booking',23,'Booking Requested','BOOKING/PROVIDER',18),(44,'2026-03-20 17:33:29.964099',_binary '','Your Hav new booking',24,'Booking Requested','BOOKING/PROVIDER',18),(45,'2026-03-20 19:32:26.773012',_binary '','Your Hav new booking',25,'Booking Requested','BOOKING/PROVIDER',18),(46,'2026-03-20 19:52:34.301491',_binary '','Your Hav new booking',26,'Booking Requested','BOOKING/PROVIDER',18),(47,'2026-03-20 19:58:31.028110',_binary '','Your Hav new booking',27,'Booking Requested','BOOKING/PROVIDER',18),(48,'2026-03-20 20:01:46.501956',_binary '','Your Hav new booking',28,'Booking Requested','BOOKING/PROVIDER',18),(49,'2026-03-21 17:21:09.321178',_binary '\0','Your booking is Upcoming',2,'BOOKING','BOOKING/USER',2),(50,'2026-03-21 17:38:40.467885',_binary '\0','Your booking is Upcoming',2,'BOOKING','BOOKING/USER',2),(51,'2026-03-21 17:42:12.126571',_binary '\0','Your booking is Upcoming',2,'BOOKING','BOOKING/USER',2),(52,'2026-03-21 17:57:27.212781',_binary '\0','Your booking is InProgress',2,'BOOKING','BOOKING/USER',2),(53,'2026-03-21 17:58:20.974428',_binary '\0','Your booking is Upcoming',2,'BOOKING','BOOKING/USER',2),(54,'2026-03-21 17:58:27.118092',_binary '\0','Your booking is Upcoming',2,'BOOKING','BOOKING/USER',2),(55,'2026-03-21 17:58:33.603205',_binary '\0','Your booking is Upcoming',2,'BOOKING','BOOKING/USER',2),(56,'2026-03-21 17:58:47.564144',_binary '\0','Your booking is InProgress',2,'BOOKING','BOOKING/USER',2),(57,'2026-03-21 17:58:52.208826',_binary '\0','Your booking is InProgress',2,'BOOKING','BOOKING/USER',2),(58,'2026-03-21 17:58:56.630683',_binary '\0','Your booking is InProgress',2,'BOOKING','BOOKING/USER',2),(59,'2026-03-21 18:52:04.779698',_binary '\0','Your booking is InProgress',2,'BOOKING','BOOKING/USER',2),(60,'2026-03-21 18:53:16.407779',_binary '\0','Your booking is InProgress',2,'BOOKING','BOOKING/USER',2),(61,'2026-03-21 18:56:27.850204',_binary '\0','Your booking is Upcoming',2,'BOOKING','BOOKING/USER',2),(62,'2026-03-21 18:56:35.213287',_binary '\0','Your booking is InProgress',2,'BOOKING','BOOKING/USER',2),(63,'2026-03-21 19:17:18.360502',_binary '\0','Your booking is Completed',2,'BOOKING','BOOKING/USER',2),(64,'2026-03-21 19:17:57.566254',_binary '\0','Your booking is Completed',2,'BOOKING','BOOKING/USER',2),(65,'2026-03-21 19:28:02.093273',_binary '','Your booking is Upcoming',2,'BOOKING','BOOKING/USER',2),(66,'2026-03-21 19:29:01.301849',_binary '\0','Your booking is Cancelled',2,'BOOKING','BOOKING/USER',2),(67,'2026-03-22 17:06:13.539790',_binary '','Your booking is Upcoming',2,'BOOKING','BOOKING/USER',2),(68,'2026-03-22 18:46:06.354912',_binary '','Your Hav new booking',29,'Booking Requested','BOOKING/PROVIDER',18),(69,'2026-03-22 19:05:41.029069',_binary '','Your Hav new booking',30,'Booking Requested','BOOKING/PROVIDER',18),(70,'2026-03-23 16:24:12.734305',_binary '','Your have a new booking request.',31,'BOOKING','BOOKING/PROVIDER',18),(71,'2026-03-23 16:28:39.930750',_binary '','Your have a new booking request.',32,'BOOKING','BOOKING/PROVIDER',18),(72,'2026-03-23 16:29:01.222660',_binary '','Your have a new booking request.',33,'BOOKING','BOOKING/PROVIDER',18),(73,'2026-03-23 17:08:23.943753',_binary '','Your have a new booking request.',34,'BOOKING','BOOKING/PROVIDER',18),(74,'2026-03-23 17:11:11.756803',_binary '','Your have a new booking request.',35,'BOOKING','BOOKING/PROVIDER',18),(75,'2026-03-23 17:11:16.673182',_binary '','Your have a new booking request.',36,'BOOKING','BOOKING/PROVIDER',18),(76,'2026-03-23 17:11:18.382656',_binary '','Your have a new booking request.',37,'BOOKING','BOOKING/PROVIDER',18),(77,'2026-03-23 17:11:21.161148',_binary '','Your have a new booking request.',38,'BOOKING','BOOKING/PROVIDER',18),(78,'2026-03-23 17:30:05.130821',_binary '','Your have a new booking request.',39,'BOOKING','BOOKING/PROVIDER',18),(79,'2026-03-23 17:30:15.962381',_binary '\0','Your have a new booking request.',40,'BOOKING','BOOKING/PROVIDER',18),(80,'2026-03-23 17:30:26.124813',_binary '\0','Your have a new booking request.',41,'BOOKING','BOOKING/PROVIDER',18),(81,'2026-03-23 17:30:38.327354',_binary '\0','Your have a new booking request.',42,'BOOKING','BOOKING/PROVIDER',18),(82,'2026-03-23 17:30:42.775891',_binary '\0','Your have a new booking request.',43,'BOOKING','BOOKING/PROVIDER',18),(83,'2026-03-23 17:30:48.035050',_binary '\0','Your have a new booking request.',44,'BOOKING','BOOKING/PROVIDER',18),(84,'2026-03-23 17:31:43.584742',_binary '','Your have a new booking request.',45,'BOOKING','BOOKING/PROVIDER',18),(85,'2026-03-23 17:31:46.125685',_binary '','Your have a new booking request.',46,'BOOKING','BOOKING/PROVIDER',18),(86,'2026-03-23 17:31:52.822390',_binary '','Your have a new booking request.',47,'BOOKING','BOOKING/PROVIDER',18),(87,'2026-03-23 17:32:14.946330',_binary '','Your have a new booking request.',48,'BOOKING','BOOKING/PROVIDER',18),(88,'2026-03-23 20:06:54.380243',_binary '','Your have a new booking request.',49,'BOOKING','BOOKING/PROVIDER',18),(89,'2026-03-23 20:07:18.441427',_binary '','Your booking is confirmed.',25,'BOOKING','BOOKING/USER',25),(90,'2026-03-23 20:09:28.389190',_binary '','Your service is now in InProgress.',25,'BOOKING','BOOKING/USER',25),(91,'2026-03-23 20:10:03.272462',_binary '','Your booking is Completed',25,'BOOKING','USER',25),(92,'2026-03-24 15:50:12.979637',_binary '\0','Your account is approved',25,'Register','APPROVED',25),(93,'2026-03-24 16:31:43.922396',_binary '','Your account is approved',25,'Register','APPROVED',25),(94,'2026-03-24 16:53:03.616856',_binary '','Your account is approved',25,'Register','APPROVED',25),(95,'2026-03-24 17:07:07.177053',_binary '','Your account is approved',25,'Register','APPROVED',25),(96,'2026-03-24 17:08:05.302818',_binary '','Your account is approved',25,'Register','APPROVED',25),(97,'2026-03-24 17:13:19.803896',_binary '\0','Payment completed successfully.',25,'PAYMENT','PAYMENT',25),(98,'2026-03-24 18:32:40.582690',_binary '\0','Your account is approved',25,'Register','APPROVED',25),(103,'2026-03-24 22:22:18.644403',_binary '\0','Your account is approved',27,'Register','APPROVED',27),(104,'2026-03-24 22:28:48.484602',_binary '','Your account is approved',15,'Register','APPROVED',15),(105,'2026-03-25 13:15:02.865281',_binary '','Your have a new booking request.',50,'BOOKING','BOOKING/PROVIDER',19),(106,'2026-03-25 14:10:30.975317',_binary '\0','Your booking is Cancelled',18,'BOOKING','USER/PROVIDER',18),(107,'2026-03-25 14:27:27.652930',_binary '','Your booking is Cancelled',19,'BOOKING','USER/PROVIDER',18),(108,'2026-03-25 14:49:20.614904',_binary '','Your account is approved',29,'Register','APPROVED',29),(109,'2026-03-25 15:04:09.210232',_binary '','Payment completed successfully.',26,'PAYMENT','PAYMENT',29),(110,'2026-03-25 16:20:40.856028',_binary '','Your account is approved',29,'Register','APPROVED',29),(111,'2026-03-25 16:21:45.125963',_binary '\0','Payment completed successfully.',28,'PAYMENT','PAYMENT',29),(114,'2026-03-25 19:54:34.284217',_binary '','Your have a new booking request.',53,'BOOKING','BOOKING/PROVIDER',29),(115,'2026-03-25 20:05:15.249577',_binary '\0','Your booking is confirmed.',28,'BOOKING','BOOKING/USER',28),(116,'2026-03-25 20:32:51.510590',_binary '','Your account is approved',30,'Register','APPROVED',30),(117,'2026-03-25 20:34:32.412646',_binary '\0','Payment completed successfully.',30,'PAYMENT','PAYMENT',30),(118,'2026-03-25 20:37:59.363684',_binary '\0','Your have a new booking request.',54,'BOOKING','BOOKING/PROVIDER',30),(119,'2026-03-25 20:38:19.951873',_binary '\0','Your booking is confirmed.',28,'BOOKING','BOOKING/USER',28),(120,'2026-03-25 21:20:26.770581',_binary '\0','Your service is now in InProgress.',28,'BOOKING','BOOKING/USER',28),(121,'2026-03-25 21:21:18.674193',_binary '\0','Your booking is Completed',28,'BOOKING','USER',28),(122,'2026-03-25 21:23:59.209585',_binary '\0','Your have a new booking request.',55,'BOOKING','BOOKING/PROVIDER',30),(123,'2026-03-25 21:24:05.206722',_binary '\0','Your booking is Cancelled',30,'BOOKING','USER/PROVIDER',28),(124,'2026-03-25 22:23:04.276925',_binary '\0','Your have a new booking request.',56,'BOOKING','BOOKING/PROVIDER',1),(125,'2026-03-25 22:23:42.707497',_binary '\0','Your have a new booking request.',57,'BOOKING','BOOKING/PROVIDER',1),(126,'2026-03-25 22:27:39.609018',_binary '\0','Your have a new booking request.',58,'BOOKING','BOOKING/PROVIDER',30),(128,'2026-03-25 22:46:59.511849',_binary '','Your account is approved',30,'Register','APPROVED',30),(131,'2026-03-25 22:52:26.804200',_binary '','Your account is approved',30,'Register','APPROVED',30),(132,'2026-03-25 22:52:46.066103',_binary '','Your account is approved',30,'Register','APPROVED',30),(133,'2026-03-25 22:53:44.952421',_binary '\0','Your account is approved',30,'Register','APPROVED',30),(134,'2026-03-25 22:59:12.548123',_binary '\0','Your have a new booking request.',59,'BOOKING','BOOKING/PROVIDER',29),(135,'2026-03-25 23:01:27.540706',_binary '','Your have a new booking request.',60,'BOOKING','BOOKING/PROVIDER',30),(136,'2026-03-25 23:05:37.870383',_binary '','Your have a new booking request.',61,'BOOKING','BOOKING/PROVIDER',30),(137,'2026-03-25 23:05:44.005483',_binary '','Your have a new booking request.',62,'BOOKING','BOOKING/PROVIDER',30),(138,'2026-03-25 23:06:22.867649',_binary '','Your account is approved',30,'Register','APPROVED',30),(140,'2026-03-25 23:08:04.732209',_binary '','Your account is approved',30,'Register','APPROVED',30),(144,'2026-03-26 15:40:49.089165',_binary '\0','Your have a new booking request.',63,'BOOKING','BOOKING/PROVIDER',1),(145,'2026-03-28 15:07:59.583299',_binary '','Your booking is Cancelled',29,'BOOKING','USER/PROVIDER',30),(146,'2026-03-28 15:18:42.133579',_binary '','Your booking is Completed',30,'BOOKING','USER',30),(151,'2026-03-28 16:12:10.144897',_binary '\0','Your account is approved',30,'Register','APPROVED',30),(159,'2026-03-28 16:34:43.606192',_binary '\0','Your account is approved',30,'Register','APPROVED',30),(181,'2026-03-28 19:51:15.609206',_binary '','This booking has been Cancelled',2,'BOOKING','BOOKING/USER',2),(182,'2026-03-28 19:51:42.376538',_binary '','This booking has been Cancelled',17,'BOOKING','BOOKING/USER',17),(184,'2026-03-28 20:03:36.432328',_binary '','Your booking is Completed',2,'BOOKING','USER',2),(185,'2026-03-28 20:03:54.107356',_binary '','This booking has been Cancelled',2,'BOOKING','BOOKING/USER',2),(186,'2026-03-28 20:08:35.517166',_binary '\0','Your account is approved',30,'Register','APPROVED',30),(187,'2026-03-28 20:08:44.201038',_binary '','Your account is approved',29,'Register','APPROVED',29),(188,'2026-03-28 20:11:48.601685',_binary '\0','Your service is now in InProgress.',28,'BOOKING','BOOKING/USER',28),(189,'2026-03-28 20:21:42.318995',_binary '','Your have a new booking request.',64,'BOOKING','BOOKING/PROVIDER',29),(190,'2026-03-28 20:21:55.881209',_binary '\0','Your booking is confirmed.',25,'BOOKING','BOOKING/USER',25),(191,'2026-03-28 20:24:04.077436',_binary '\0','Your booking is confirmed.',25,'BOOKING','BOOKING/USER',25),(192,'2026-03-28 20:39:48.225727',_binary '\0','Your have a new booking request.',65,'BOOKING','BOOKING/PROVIDER',30),(193,'2026-03-28 20:44:52.456385',_binary '\0','Your have a new booking request.',66,'BOOKING','BOOKING/PROVIDER',29),(194,'2026-03-28 20:45:28.907032',_binary '\0','Your booking is confirmed.',30,'BOOKING','BOOKING/USER',30),(195,'2026-03-30 15:27:56.223655',_binary '\0','Payment completed successfully.',33,'PAYMENT','PAYMENT',17),(196,'2026-03-30 16:27:36.782955',_binary '','Your have a new booking request.',67,'BOOKING','BOOKING/PROVIDER',29),(197,'2026-03-30 21:57:12.403270',_binary '\0','Your booking is Completed',28,'BOOKING','USER',28),(198,'2026-03-31 13:47:21.751236',_binary '\0','Payment completed successfully.',34,'PAYMENT','PAYMENT',15),(199,'2026-03-31 13:48:25.083514',_binary '\0','Payment completed successfully.',35,'PAYMENT','PAYMENT',15),(200,'2026-03-31 13:58:49.225250',_binary '\0','Payment completed successfully.',36,'PAYMENT','PAYMENT',15),(201,'2026-03-31 13:59:36.862785',_binary '\0','Payment completed successfully.',37,'PAYMENT','PAYMENT',15),(202,'2026-03-31 19:30:29.051781',_binary '\0','Payment completed successfully.',38,'PAYMENT','PAYMENT',15),(203,'2026-03-31 19:42:41.701552',_binary '\0','Your booking is confirmed.',18,'BOOKING','BOOKING/USER',18),(204,'2026-03-31 19:43:47.026786',_binary '\0','Your service is now in InProgress.',18,'BOOKING','BOOKING/USER',18),(205,'2026-03-31 19:43:56.475234',_binary '\0','Your booking is Completed',18,'BOOKING','USER',18),(206,'2026-04-02 17:02:18.202423',_binary '','Your have a new booking request.',68,'BOOKING','BOOKING/PROVIDER',29),(207,'2026-04-02 17:12:22.010201',_binary '','Your booking is confirmed.',19,'BOOKING','BOOKING/USER',19),(208,'2026-04-02 17:22:43.293915',_binary '','Your have a new booking request.',69,'BOOKING','BOOKING/PROVIDER',19),(209,'2026-04-02 17:23:18.493677',_binary '','Your booking is confirmed.',19,'BOOKING','BOOKING/USER',19),(210,'2026-04-04 11:00:11.872309',_binary '','Your service is now in InProgress.',19,'BOOKING','BOOKING/USER',19),(211,'2026-04-04 11:01:03.430511',_binary '','Your booking is Completed',19,'BOOKING','USER',19),(212,'2026-04-04 11:02:49.387828',_binary '','Your have a review',69,'Review','PROVIDER/BOOKING',19),(213,'2026-04-04 11:10:10.097362',_binary '\0','Payment completed successfully.',40,'PAYMENT','PAYMENT',18),(214,'2026-04-04 11:15:17.357067',_binary '\0','Your account is approved',18,'Register','APPROVED',18),(216,'2026-04-04 11:53:29.317860',_binary '\0','Your account is approved',31,'Register','APPROVED',31),(217,'2026-04-04 11:54:20.572034',_binary '\0','Your account is approved',31,'Register','APPROVED',31),(218,'2026-04-04 11:58:51.665175',_binary '\0','Payment completed successfully.',42,'PAYMENT','PAYMENT',31),(219,'2026-04-04 13:51:24.599684',_binary '\0','Your have a new booking request.',70,'BOOKING','BOOKING/PROVIDER',1),(220,'2026-04-04 13:51:48.618079',_binary '\0','Your booking is Cancelled',1,'BOOKING','USER/PROVIDER',2),(222,'2026-04-04 14:04:30.754907',_binary '\0','Your account is approved',31,'Register','APPROVED',31),(223,'2026-04-04 14:11:35.394313',_binary '','Your have a new booking request.',71,'BOOKING','BOOKING/PROVIDER',31),(224,'2026-04-04 14:11:45.840209',_binary '\0','Your booking is confirmed.',15,'BOOKING','BOOKING/USER',15),(225,'2026-04-04 15:09:28.333159',_binary '','Your service is now in InProgress.',15,'BOOKING','BOOKING/USER',15),(226,'2026-04-04 15:18:51.731854',_binary '\0','Your booking is Completed',15,'BOOKING','USER',15),(227,'2026-04-04 15:22:32.899271',_binary '','Your have a new booking request.',72,'BOOKING','BOOKING/PROVIDER',31),(228,'2026-04-04 15:22:40.527151',_binary '\0','Your booking is confirmed.',15,'BOOKING','BOOKING/USER',15);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plans`
--

DROP TABLE IF EXISTS `plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plans` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `max_service` int NOT NULL,
  `max_skill` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plans`
--

LOCK TABLES `plans` WRITE;
/*!40000 ALTER TABLE `plans` DISABLE KEYS */;
INSERT INTO `plans` VALUES (1,'2026-02-15','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/bjrigwolhfywlqcsw6wu',3,3,'Basic ( Free )',0,'Active'),(2,'2026-02-15','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/drf9hqcnonl6fx1zslol',3,3,'Basic',9.99,'Active'),(3,'2026-02-15','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/hkucwobhefxceckiunfy',10,10,'Premium',99,'Active'),(4,'2026-03-13','','Yearly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/tkupgznzl0oo1g7saj23',6,6,'Standard',49.99,'Active'),(16,'2026-03-17','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/x86cmwkags5m03rflkge',5,10,'Legacy',0.1,'Inactive');
/*!40000 ALTER TABLE `plans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portfolios`
--

DROP TABLE IF EXISTS `portfolios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `portfolios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `provider_user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhoelrwjtt6eu09aie5yugwief` (`provider_user_id`),
  CONSTRAINT `FKhoelrwjtt6eu09aie5yugwief` FOREIGN KEY (`provider_user_id`) REFERENCES `providers` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portfolios`
--

LOCK TABLES `portfolios` WRITE;
/*!40000 ALTER TABLE `portfolios` DISABLE KEYS */;
INSERT INTO `portfolios` VALUES (5,'2026-03-17','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',1),(6,'2026-03-17','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723663/qubrxnaif4qg9auxezwd.jpg',1),(7,'2026-03-17','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723675/gqabjawcyop9jvdthjhr.jpg',1),(8,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/qkonazvpl2fpvhywuapu',18),(9,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/knrzsitzu9mnlz64o5md',18),(10,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/fvvq2zr01kz1trluzprq',18),(11,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/tbimdbrtahdztlz5zrz9',18),(12,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/zvyeihlsxtusulonk4hc',18),(13,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/mf3fde8vuli9hlsdzxpr',18),(14,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/zejjr7zxilsfco4eg92q',18),(15,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/cpd36rmwj6mhql4eir2k',18),(16,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/hejlw7zj0pmw3bsaz3nr',18),(21,'2026-03-25','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/pyq26jci3ituo999iulj',29),(22,'2026-03-25','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/g5vhjfiqgnflwn3cf7ow',29),(23,'2026-03-25','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/amuwept77jovbbu2i0uj',29),(24,'2026-03-25','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/n5zmy8ibtnj2wo1ldafa',29),(25,'2026-03-25','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/gvh1tkn5onjafnp46rbn',30),(26,'2026-03-25','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/bydxq13rqqhvk2skowkv',30),(27,'2026-03-25','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/msuisabljaj7l07rq6yy',30);
/*!40000 ALTER TABLE `portfolios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `providers`
--

DROP TABLE IF EXISTS `providers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `providers` (
  `business_name` varchar(255) DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `end_date` varchar(255) NOT NULL,
  `end_time` time NOT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `start_time` time NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `job_status` varchar(255) DEFAULT NULL,
  `service_radius` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK7ant8u8y5lel5bd0d6hdexmde` (`email`),
  CONSTRAINT `FKlymp42tvombik3t66um6ocsba` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `providers`
--

LOCK TABLES `providers` WRITE;
/*!40000 ALTER TABLE `providers` DISABLE KEYS */;
INSERT INTO `providers` VALUES ('Nhanh Sokein ComFix','2026-02-15 00:00:00.000000','sokein@gmail.com','FRIDAY','17:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/vyjtgvmcieor5ouxrp2j',13.0947,103.1964,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/ipiu6cgn2mt8rn0vxpsw','MONDAY','08:00:00','Active',1,'Approved',2,'Good Provider'),('Vite Boy Loy Camfix','2026-03-02 00:00:00.000000','lydavit@gmail.com','FRIDAY','17:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/v1773305361/ppuumiwx6niftf1vnsny.jpg',13.0947,103.1964,'http://res.cloudinary.com/dytxsxpue/image/upload/v1773305364/dufjg6nju32fkfqsseeo.jpg','MONDAY','08:00:00','Active',2,'Approved',3,'Im a good man'),('Smart Tech Provider','2026-03-12 00:00:00.000000','Nhanh Sokein@gmail.com','FRIDAY','17:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/v1773305189/fwybn5nbfdgtp0gllt3w.png',13.0947,103.1964,'http://res.cloudinary.com/dytxsxpue/image/upload/v1773305191/hl3krenewr9zcpf0fanu.png','MONDAY','08:00:00','Inactive',3,'Approved',4,'Im a good man'),('Alpha Service Provider','2026-03-12 00:00:00.000000','Seng Sayheang@gmail.com','FRIDAY','17:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/v1773305448/awof5o5nltroudwqq3hq.jpg',13.0947,103.1964,'http://res.cloudinary.com/dytxsxpue/image/upload/v1773305451/wlesupmx1jxjpw6auser.jpg','MONDAY','10:00:00','Inactive',4,'Approved',5,NULL),('LinaCam Fix','2026-03-16 19:48:15.627779','Lina Chan@gmail.com','FRIDAY','17:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/v1773665351/hcqgyxizu8jjxdmxwxqp.png',0,0,'http://res.cloudinary.com/dytxsxpue/image/upload/v1773665353/vk5oam8qcoxayi76qk3t.jpg','MONDAY','08:00:00','Inactive',6,'Approved',0,NULL),(NULL,'2026-03-16 19:51:16.744948','Heng@gmail.com','Wed','17:00:00',NULL,0,0,NULL,'Mon','07:00:00','Inactive',7,'Approved',0,NULL),(NULL,'2026-03-18 14:36:11.036608','tuki@gmail.com','TUESDAY','02:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/v1773819374/f6gidl4escflge3admow.jpg',13.0967426,103.2172886,'http://res.cloudinary.com/dytxsxpue/image/upload/v1773819377/xdfob7emfiih9kroafew.jpg','MONDAY','00:00:00','Inactive',15,'Approved',5000,NULL),(NULL,'2026-03-18 19:45:02.532782','asdaasd@gmail.com','Friday','21:00:00',NULL,0,0,NULL,'Monday','12:00:00','Active',16,'Approved',0,NULL),('tuki','2026-03-18 19:35:23.063673','asda@gmail.com','Friday','21:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/g9yhgmlwp9kp1dymyoez',13.096743,103.217287,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/lkmxzsohsbju1x8phagf','Monday','12:00:00','Active',17,'Approved',1000,'kjnbbfyz6rz75x'),('BTB Meow','2026-03-18 20:15:49.802482','oaisjs@gmail.com','SUNDAY','22:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/q7zzivqq082f97sglbev',11.5564,104.9282,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/tvuh0la10vpyvkcynlhs','TUESDAY','07:00:00','Active',18,'Approved',16,''),('Home Service Cambodia','2026-03-19 22:46:59.163411','Kuyheng@gmail.com','FRIDAY','19:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/fzoewiesd8hecv2wzoun',13.0947,103.1964,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/vxz9pfbyo7l8a2pupk1q','MONDAY','08:00:00','Active',19,'Approved',6,'Good'),('tuki','2026-03-24 14:43:25.723585','tukffi@gmail.com','SUNDAY','23:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/tmbfmvqcxpgukwtnayyu',13.0967355,103.2172374,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/a47m7fmdvlidgiliyjus','MONDAY','00:00:00','Active',25,'Approved',50000,NULL),('Thang Kimlong','2026-03-24 20:28:30.606117','long@gmail.com','SUNDAY','17:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/eylvl59rcxtwyaadpb6f',13.0967375,103.217233,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/yksb3rnnzntr33puhz8w','MONDAY','07:00:00','Active',27,'Approved',5000,NULL),('BTB PipeMaster Syyyyy','2026-03-25 16:19:43.074281','thangkimlong@gmail.com','SUNDAY','18:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/fdqnbddritmjexutslgl',13.093769573204264,103.1827237084508,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/cixf2j3zmb0sfkeouyyk','MONDAY','09:00:00','Active',29,'Approved',10,'Professional plumbing service specializing in pipe repair, installation, and maintenance to keep your home’s water system safe and running smoothly.'),('BTB Home Repair','2026-03-25 20:32:16.583557','homerepair@gmail.com','SUNDAY','23:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/v0iw621kdvzlckodqkrj',13.0967358,103.2172388,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/buzkqqbatce7l0yldfef','MONDAY','00:00:00','Active',30,'Approved',11,NULL),('testing','2026-04-04 11:51:00.404678','testing@gmail.com','SUNDAY','23:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/ht3iwsfha9ku4plf6yha',13.108044039641555,103.17403066903353,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/hynxsewb03qce21pbdlo','MONDAY','00:00:00','Active',31,'Approved',15,NULL);
/*!40000 ALTER TABLE `providers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_images`
--

DROP TABLE IF EXISTS `review_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `review_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3aayo5bjciyemf3bvvt987hkr` (`review_id`),
  CONSTRAINT `FK3aayo5bjciyemf3bvvt987hkr` FOREIGN KEY (`review_id`) REFERENCES `reviews` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_images`
--

LOCK TABLES `review_images` WRITE;
/*!40000 ALTER TABLE `review_images` DISABLE KEYS */;
INSERT INTO `review_images` VALUES (1,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',1),(2,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',1),(3,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',1),(4,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',2),(5,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',2),(6,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',2),(7,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',3),(8,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',3),(9,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',3),(10,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',4),(11,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',4),(12,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773723649/o572cmpi9lwipbhpzvjt.png',4),(13,'2026-04-04','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/yzydxigldw9wc4j9cmda',69),(14,'2026-04-04','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/mdmtyho9z2nh1nboshdf',69);
/*!40000 ALTER TABLE `review_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `date` date DEFAULT NULL,
  `rate` int NOT NULL,
  `response` varchar(255) DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `booking_id` bigint NOT NULL,
  PRIMARY KEY (`booking_id`),
  CONSTRAINT `FK28an517hrxtt2bsg93uefugrm` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES ('2026-02-18',5,NULL,'Good work but provider face not cute',1),('2026-02-18',5,NULL,'Good work but provider face not cute',2),('2026-02-18',2,NULL,'Good work but provider face not cute',3),('2026-02-18',3,NULL,'Good work but provider face not cute',4),('2026-03-28',5,NULL,'',60),('2026-04-04',5,NULL,'good work',69);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` date NOT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'2026-02-15','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/v6vt7at661p3s0ss9kyd','Plumbing','ACTIVE'),(2,'2026-02-15','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/bieak5wrunu3c2pzkdfz','Electrical','ACTIVE'),(3,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/narentuo8iirr5r3mo7v','Painting','ACTIVE'),(5,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/t4j2cx8hudqybedfd3f4','Carpentry','ACTIVE'),(6,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/mvignhupbaa3dchtxtkc','Gardening','ACTIVE'),(7,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/sqse1jedksznlji0ipuc','Waterproofing','ACTIVE'),(8,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/v7xo8hbtdz6lgbq7paie','AC Repair','ACTIVE'),(9,'2026-03-10','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/p1vtjhb0l0svtom9g89k','Cleaning','ACTIVE'),(11,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/xxo5ryt8vnzmf6vmhejw','Flooring','ACTIVE');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skills` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` date NOT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `service_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8pqk1g3tquplgfrvayq2dnaaw` (`service_id`),
  CONSTRAINT `FK8pqk1g3tquplgfrvayq2dnaaw` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (1,'2026-02-15','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248799/em3j6ukiezlxjjfzvtgn.jpg','Pipe Repair',1),(2,'2026-02-15','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248269/sshpcw9hy4fewqeruzkl.jpg','Electrical Installation',2),(3,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248339/zdqrv6haulnhd2xds49v.jpg','Wall Painting',3),(5,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773496856/zoqlows6cj32djqmwd6w.jpg','Garden Cutting',6),(6,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248607/a4hyqsa83rbgjn8xui3c.png','Roof Waterproofing',7),(7,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248565/wrg23fqq1paa7fabdjjz.png','AC Maintenance',8),(8,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248656/zuhjichueliseh8dghg6.png','Woodwork',5),(10,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/v1773336103/vb0exrdvnveqyjy1rgew.jpg','Deep Cleaning',9),(11,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248337/g3zw3rkbexijamcwszbj.jpg','Floor Cleaning',9),(12,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/v1773497861/nfaeo20rcjz5vc4lcs8q.jpg','Bathroom Cleaning',9),(13,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248303/kgscn0ghsqsilsaximsv.jpg','Cable Fixing',2),(14,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/v1773763535/igsgbi3u1t3gqexz8wtp.jpg','Floor Installation',11),(33,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/osxeazwghujt6x3bpooa','Kitchen Cleaning',9),(34,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/lrilyzylczdaqlvtcxki','Window Cleaning',9),(35,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/eetajpl5esw6ptvzaqeh','Leak Repair',1),(36,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/jgcjfxjvd6lp6soo78pe','Drain Cleaning',1),(37,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/dvlycwbdkkemaldpo2kc','Toilet Repair & Installation',1),(38,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/tggo34kypzigk7b8t1se','Lighting Installation & Repair',2),(39,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/y8ohi3jah3kex1bwfisz','Switch & Socket Installation',2),(41,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/ug139iad3ifr1pqy8unm','Cabinet Installation',5),(42,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/guqkyywbsb0xvnnm8jig','Furniture Repair',5),(43,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/araxhynwfzqrsrojqejd','Garden Cleanup',6),(44,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/vi1qwcrtzqwmhyaqbrhf','Planting & Landscaping',6),(45,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/ytl3ivoklilcxlqowfbd','Bathroom Waterproofing',7),(46,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/cya71coulqbvxl1vat7m','Basement Waterproofing',7),(47,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/uyhmgvtu39gdwfu3irhz','AC Installation',8),(48,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/zvnhzjxef973aycxsbvj','AC Gas Refilling',8),(49,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/ao3qchza5rohiaf1lhbt','Floor Repair',11),(50,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/aanjpcorinjb2ocqztsr','Floor Polishing',11);
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscriptions` (
  `provider_id` bigint NOT NULL,
  `end_date` date NOT NULL,
  `start_date` date NOT NULL,
  `status` varchar(255) NOT NULL,
  `plan_id` bigint NOT NULL,
  PRIMARY KEY (`provider_id`),
  KEY `FKb1uf5qnxi6uj95se8ykydntl1` (`plan_id`),
  CONSTRAINT `FK7246y96961isnbfo656mbc9et` FOREIGN KEY (`provider_id`) REFERENCES `providers` (`user_id`),
  CONSTRAINT `FKb1uf5qnxi6uj95se8ykydntl1` FOREIGN KEY (`plan_id`) REFERENCES `plans` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptions`
--

LOCK TABLES `subscriptions` WRITE;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
INSERT INTO `subscriptions` VALUES (1,'2027-03-18','2026-03-18','Active',4),(2,'2027-03-18','2026-03-18','Active',4),(3,'2027-03-18','2026-03-18','Active',4),(4,'2026-04-17','2026-03-18','Active',3),(15,'2026-04-30','2026-03-31','Active',1),(17,'2026-04-29','2026-03-30','Active',1),(18,'2026-05-04','2026-04-04','Active',1),(19,'2026-04-18','2026-03-19','Active',1),(25,'2026-04-23','2026-03-24','Active',2),(29,'2026-04-24','2026-03-25','Active',2),(30,'2026-04-24','2026-03-25','Active',2),(31,'2026-05-04','2026-04-04','Active',1);
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  `end_date` date NOT NULL,
  `pay_date` datetime(6) NOT NULL,
  `start_date` date NOT NULL,
  `status` varchar(255) NOT NULL,
  `total` float NOT NULL,
  `plan_id` bigint NOT NULL,
  `provider_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmp8i5piccwrx5m90bi6su5y90` (`plan_id`),
  KEY `FK1fn1xmkx0nba5w3eo503444ab` (`provider_id`),
  CONSTRAINT `FK1fn1xmkx0nba5w3eo503444ab` FOREIGN KEY (`provider_id`) REFERENCES `providers` (`user_id`),
  CONSTRAINT `FKmp8i5piccwrx5m90bi6su5y90` FOREIGN KEY (`plan_id`) REFERENCES `plans` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (2,'Yearly','2027-03-18','2026-03-18 00:00:00.000000','2026-03-18','Completed',49.99,4,1),(3,'Yearly','2027-03-18','2026-03-18 00:00:00.000000','2026-03-18','Completed',49.99,4,2),(4,'Yearly','2027-03-18','2026-03-18 00:00:00.000000','2026-03-18','Completed',49.99,4,3),(7,'Monthly','2026-04-17','2026-03-18 00:00:00.000000','2026-03-18','Completed',1000,3,4),(12,'Monthly','2026-04-17','2026-03-18 00:00:00.000000','2026-03-18','Completed',0,1,18),(13,'Monthly','2026-04-18','2026-03-19 00:00:00.000000','2026-03-19','Completed',0,1,19),(14,'Monthly','2026-04-23','2026-03-24 16:33:00.937589','2026-03-24','Declined',9.99,2,25),(15,'Monthly','2026-04-23','2026-03-24 16:33:14.018330','2026-03-24','Declined',9.99,2,25),(16,'Monthly','2026-04-23','2026-03-24 16:33:28.302557','2026-03-24','Declined',9.99,2,25),(17,'Monthly','2026-04-23','2026-03-24 16:34:31.292623','2026-03-24','Declined',9.99,2,25),(18,'Monthly','2026-04-23','2026-03-24 16:54:58.268922','2026-03-24','Declined',9.99,2,25),(19,'Monthly','2026-04-23','2026-03-24 16:56:25.989186','2026-03-24','Declined',9.99,2,25),(20,'Monthly','2026-04-23','2026-03-24 16:56:38.951219','2026-03-24','Declined',9.99,2,25),(21,'Monthly','2026-04-23','2026-03-24 17:00:17.275582','2026-03-24','Declined',9.99,2,25),(22,'Monthly','2026-04-23','2026-03-24 17:08:13.088325','2026-03-24','Declined',9.99,2,25),(23,'Monthly','2026-04-23','2026-03-24 17:08:22.478480','2026-03-24','Declined',9.99,2,25),(24,'Monthly','2026-04-23','2026-03-24 17:09:02.278794','2026-03-24','Declined',9.99,2,25),(25,'Monthly','2026-04-23','2026-03-24 17:12:39.046276','2026-03-24','Completed',9.99,2,25),(27,'Monthly','2026-04-24','2026-03-25 16:21:05.511478','2026-03-25','Declined',9.99,2,29),(28,'Monthly','2026-04-24','2026-03-25 16:21:23.241640','2026-03-25','Completed',9.99,2,29),(29,'Monthly','2026-04-24','2026-03-25 20:32:57.935371','2026-03-25','Declined',0,1,30),(30,'Monthly','2026-04-24','2026-03-25 20:33:28.628082','2026-03-25','Completed',9.99,2,30),(31,'Monthly','2026-04-27','2026-03-28 20:42:54.926491','2026-03-28','Pending',0,1,29),(32,'Monthly','2026-04-29','2026-03-30 15:23:18.290511','2026-03-30','Pending',0,1,17),(33,'Monthly','2026-04-29','2026-03-30 15:27:56.039529','2026-03-30','Completed',0,1,17),(34,'Monthly','2026-04-30','2026-03-31 13:47:21.537362','2026-03-31','Completed',0,1,15),(35,'Monthly','2026-04-30','2026-03-31 13:48:24.992603','2026-03-31','Completed',0,1,15),(36,'Monthly','2026-04-30','2026-03-31 13:58:49.088409','2026-03-31','Completed',0,1,15),(37,'Monthly','2026-04-30','2026-03-31 13:59:36.822274','2026-03-31','Completed',0,1,15),(38,'Monthly','2026-04-30','2026-03-31 19:30:28.948218','2026-03-31','Completed',0,1,15),(39,'Monthly','2026-04-30','2026-03-31 19:30:57.818107','2026-03-31','Pending',9.99,2,15),(40,'Monthly','2026-05-04','2026-04-04 11:10:10.030464','2026-04-04','Completed',0,1,18),(41,'Monthly','2026-05-04','2026-04-04 11:10:36.964947','2026-04-04','Pending',9.99,2,18),(42,'Monthly','2026-05-04','2026-04-04 11:58:51.625576','2026-04-04','Completed',0,1,31),(43,'Monthly','2026-05-04','2026-04-04 12:00:04.889248','2026-04-04','Declined',9.99,2,31),(44,'Monthly','2026-05-04','2026-04-04 12:00:20.582020','2026-04-04','Pending',9.99,2,31);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `dob` date NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `tel` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `fcm_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKnekwtc70sk6c2dofve0axwnhb` (`tel`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2026-02-15 00:00:00.000000','2003-01-05','Male','heng','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250152/bqf2mvit62h5v948si9f.jpg','0862231445','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NzMzMDk4NTksImV4cCI6MTc3MzkxNDY1OX0.Aym2H3WPT6DQQSMChIYQ_6CGAk3gu0dj37gHNngsWUs',NULL),(2,'2026-02-17 00:00:00.000000','1990-10-29','Male','Ly Davit','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250172/lyr4tz6qsr3telnqkat6.jpg','0964557838','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NzUyODUyODAsImV4cCI6MTc4NTY1MzI4MH0.5IO3V_1uvo2RG5md4GzEGihd0ln4fro1sbjetblCU7c','fl3SuXhgTySBva2Ov3Hi3-:APA91bEHN7OkTZXYb3zbo6_hp8NxL5FhmZcj_uxxWQiTh62Ny00WciUWH-ETqj0v7qJ-Pl9l3XvKLImrRmcNii03i6dqsA0JNh2PmxYL-_Qw9b9_DMJIEO0'),(3,'2026-02-17 00:00:00.000000','1990-06-06','Male','Nhanh Sokein','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250190/ul4zoarbiinjkhnhtcf3.jpg','087812643','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NzQ2ODQyMzgsImV4cCI6MTc4NTA1MjIzOH0.fNZUKFDn10qKsLy3lvkVR2hTEy7F65sXhN2PKVV7_vo',NULL),(4,'2026-02-18 00:00:00.000000','2003-03-26','Male','Seng Say heang','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250212/rhy4y97vsl4tw9ndphln.jpg','0966132785','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0Iiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NzEzOTY5OTYsImV4cCI6MTc3MjAwMTc5Nn0.iZpLnLy6lDop0xzy5GkOvfm8BPHZ6DH0KL25cDAoqMA',NULL),(5,'2026-03-03 00:00:00.000000','1998-02-14','Male','David Sok','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250229/jfsejpo7dp3dahvzxg1q.png','097 345 1123','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1Iiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NzI1MzI3MzIsImV4cCI6MTc3MzEzNzUzMn0.WB4stMXFXC17E2HysIk9jW4ZxI5s7VHIPrDqUFXHujA',NULL),(6,'2026-03-03 00:00:00.000000','1997-06-30','Male','Lina Chan','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250243/h6pyhzjoaqncccnhzss7.png','088 456 2234','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Iiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NzI1MzMyNDcsImV4cCI6MTc3MzEzODA0N30.-Q1cw_PKNeJUofHFnZ8rLSD2VUS9Oj1Q79PObjnwd5w',NULL),(7,'2026-03-03 00:00:00.000000','1995-09-12','Male','Michael Dara','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250260/fxavgs1hozcmqsyfv2o1.png','096 567 3345','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI3Iiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NzM4MTQzODYsImV4cCI6MTc3NDQxOTE4Nn0.T3UjQYFA5pYi-38aGySK025YYtKRLFnBQomAtz2VA10',NULL),(8,'2026-03-03 00:00:00.000000','2000-01-25','Male','Sreyneang Kim','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250277/lc5chqym338kz5yyywkw.png','097 678 4456','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI4Iiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NzI1MzM0NjAsImV4cCI6MTc3MzEzODI2MH0.p6MdbbxopKimoYSpTpeRo4KnV1ILoWQd8XP0e-0Bv9s',NULL),(9,'2026-03-03 00:00:00.000000','1994-11-08','Male','John Vireak','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250294/tzzpcsw4yp6skppzmlyf.jpg','031 789 5567','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5Iiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NzI1MzM1MzMsImV4cCI6MTc3MzEzODMzM30.qPwdLC0wgKNfhfVKllSyogQM-wII69xAnw2ICHnxAnE',NULL),(10,'2026-03-03 00:00:00.000000','1999-03-19','Male','Sophea Ly','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250314/t4ctqzgyz1t9ihn3pqsf.jpg','099 891 6678','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzcyNTMzNTk2LCJleHAiOjE3NzMxMzgzOTZ9.rlWDQ1u1rW1aWQw0sIrJzxXEDubCyzPjktd7ZL0HcDU',NULL),(11,'2026-03-03 00:00:00.000000','1996-07-04','Male','Daniel Ratanak','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250352/iw81txzj050fk1tn9cad.jpg','071 912 7789','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzcyNTMzNjc0LCJleHAiOjE3NzMxMzg0NzR9.jBeN-Ryt0FUTCdJ3Xv0FCmVfd8jIjrE7edi9OwGqF20',NULL),(12,'2026-03-03 00:00:00.000000','2001-05-16','Male','Mali Phan','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250367/suyze4inb9xjqnpohgey.png','088 123 8890','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMiIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzcyNTMzNzM2LCJleHAiOjE3NzMxMzg1MzZ9.AUwAZHL3NcLTeGL0f5ipFfFbyZdktDeMtX8S69vhoG8',NULL),(13,'2026-03-03 00:00:00.000000','1993-12-22','Male','Kevin Panhha','http://res.cloudinary.com/dytxsxpue/image/upload/v1773250381/r4p6lqrici0gq4tngfld.png','097 234 9901','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMyIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzcyNTMzNzk0LCJleHAiOjE3NzMxMzg1OTR9.Lw27zPSz07ZpsqttcwpxnvyW3zlokDnbQLhZxyn5E8U',NULL),(14,'2026-03-03 00:00:00.000000','1998-08-10','Male','Anna Sokha','http://res.cloudinary.com/dytxsxpue/image/upload/v1773256984/tbyomvizggbnpb7tvbfa.jpg','096 345 1012','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzcyNTMzODYzLCJleHAiOjE3NzMxMzg2NjN9.O-fIE6wyZXMQIlUyJSX1vZyd86Mou67mWUzw_wfoUVU',NULL),(15,'2026-03-13 00:00:00.000000','2008-03-17','Male','tuki','http://res.cloudinary.com/dytxsxpue/image/upload/v1773405862/pbi5bmadm0dwdfd5d3vd.jpg','086609012','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1MjkwMDIxLCJleHAiOjE3ODU2NTgwMjF9.cRfA9HdUJBpmfGrOJfynCLd5EZWqPVcFJZHXLJ4E9-k','fmstEiiXQ-m4Hy8bVVRBJ7:APA91bGTi4hcsItv3MZ0FdG3uuvSXQmU_eHwNHtRfE1dPNb0miD4oudqSDELFkGDlgoK-7fUsen88PRbSKrB-mZttm68_x2RZ1rKI65o5xi8qoaM6bTcffc'),(16,'2026-03-18 00:00:00.000000','2008-03-22','Other','testing',NULL,'012345678','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNiIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzczOTk5NTk2LCJleHAiOjE3ODQzNjc1OTZ9.uYZFgrvYb5UIY1VlwhrLxMZMhuC_zPjsuLB8cVyLMXM',NULL),(17,'2026-03-18 00:00:00.000000','2008-03-20','Female','tesing2',NULL,'090909090','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNyIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1Mjc2NTgzLCJleHAiOjE3ODU2NDQ1ODN9._XdT1TuG9C2tnQEo9AX5gIs0HV5mMwYuQ7gWqIDKygU','dw6yPeQmS5CNhF7cBsrz8E:APA91bHRTOkjJgT3_1Ap9ce2OBgU4-seGPpB2RnNx1lw8RmEAXPmgwddow1H5ppn2ZzE-WtDtiE10uzFifMuZvsUmdbXiLETcn-HJXciGac5GBe_5dF4MiU'),(18,'2026-03-18 00:00:00.000000','2008-03-22','Male','jack meow','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/rlyvq7jnbwxqeyjp9etq','0987654321','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxOCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1Mjc1NzIwLCJleHAiOjE3ODU2NDM3MjB9.LPQuOXeCqQ2BcTHXSbpwFKBg1z8fH6HvligEdCBRzR8','fmstEiiXQ-m4Hy8bVVRBJ7:APA91bGTi4hcsItv3MZ0FdG3uuvSXQmU_eHwNHtRfE1dPNb0miD4oudqSDELFkGDlgoK-7fUsen88PRbSKrB-mZttm68_x2RZ1rKI65o5xi8qoaM6bTcffc'),(19,'2026-03-19 00:00:00.000000','2003-01-05','Male','Chhorn Kuyheng','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/kt0ola2pruxyxm4uwcw8','086223129','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxOSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1Mjg5NjQ3LCJleHAiOjE3ODU2NTc2NDd9.Ji1QWfk08nHOttsHSRAs_DRseHp9C2oYVc-P0soVb3k','cXwpUjucRCuyyL2Wmlimj8:APA91bFgyzZoF2ziiGp9OpKWI5SVMtLCnl9nHehCqtyOvQay4ILq8RpK_bn-Fl2WCSOdzs2UkEpI5FZNPWdtbFrGkhAnzJRALGww-74biLpLkExSHuDRWi8'),(20,'2026-03-21 20:42:50.215592','1999-01-05','Male','Oggy',NULL,'097213428','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc0MTAwNTcwLCJleHAiOjE3ODQ0Njg1NzB9.vZCwLuIK9toyEJCp1WVjRU6hQIryhboYVZibFSptGTU',NULL),(21,'2026-03-21 20:43:34.269579','1999-01-05','Male','Oggy-ggg',NULL,'097213497','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc0MTAwNjE0LCJleHAiOjE3ODQ0Njg2MTR9.G7VrgXNmB80HxJrUyDeHf8VnDaR2HQKLu7kOvFjrPdc',NULL),(22,'2026-03-21 20:43:52.185556','1999-01-05','Male','Oggy-ggg',NULL,'097213490','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMiIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc0MTAwNjMyLCJleHAiOjE3ODQ0Njg2MzJ9.JGNdAG8NXx0_iUoMvske2yxN5_zMq_t97vOy4RNcw1Y',NULL),(23,'2026-03-21 20:44:10.138607','1999-01-05','Male','Oggy-ggg',NULL,'097213491','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMyIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc0MTAwNjUwLCJleHAiOjE3ODQ0Njg2NTB9.zOMo87NBad1_HTg5BSl_JPWyOY0cM49msolsL3Salfw',NULL),(24,'2026-03-21 21:01:31.246540','1999-01-05','Male','Oggy-ggg',NULL,'097213492','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc0MTAxNjkxLCJleHAiOjE3ODQ0Njk2OTF9.MW7V3PWTzKpusW-1DV5NeKrpNuxeAXwbCV_Mhfr2zME',NULL),(25,'2026-03-23 19:19:26.830174','2026-03-12','Other','tuki',NULL,'0123123123','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1MjczMjU0LCJleHAiOjE3ODU2NDEyNTR9.BtsSxFPEeIOZyfdZQQsLdQJm63N_BKWa4ii3iGmSlJc','d_Bre1D-STmb3JGm4oU9ZR:APA91bHesAxPaN8o0xXJdkL9d4z_pR9XR6H7yoxXgz6E5tiGsqYNHJhz-Mh10RrQqkJNyAnD2TgTMcK7c3e-w1hirYyev03fegLL1qjrjA72rdM-ECFKUvY'),(26,'2026-03-24 20:10:04.484991','2001-08-05','Male','Thang Kimlong',NULL,'099273090','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNiIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc0MzY0NDMxLCJleHAiOjE3ODQ3MzI0MzF9.iCMNsgC-jkTgso9TP37eOAuZDULyGLtX45TjwtiBT_Y','cqj_C20pTvWQLKvNR268v6:APA91bHaKRdCup-7LH9OT-kZZ9CJf0hZ4t7jNRJ03l-K3VW6gMJezAwGio7m-_k1dkS_65vRLQb5-j-dhNZlCKRhpsm78nyFionRWUflCEQGSaU6xJzp_P8'),(27,'2026-03-24 20:25:08.212515','1998-12-24','Male','Leng Putrea',NULL,'010203034','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNyIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc0NDI0MjEzLCJleHAiOjE3ODQ3OTIyMTN9.pi7HJ_Pb6ciHh8oxEGaPOhMFbkKd9bDsy8uvWiwgsSw','cqj_C20pTvWQLKvNR268v6:APA91bHaKRdCup-7LH9OT-kZZ9CJf0hZ4t7jNRJ03l-K3VW6gMJezAwGio7m-_k1dkS_65vRLQb5-j-dhNZlCKRhpsm78nyFionRWUflCEQGSaU6xJzp_P8'),(28,'2026-03-25 11:15:20.884321','2001-08-05','Male','Thang Kimlong',NULL,'0889836964','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyOCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc0OTY5ODY3LCJleHAiOjE3ODUzMzc4Njd9.cEHkvkJ5gpFY9OBBlIPh-rA-wEGBd27csLeD_mO-j3I','fmstEiiXQ-m4Hy8bVVRBJ7:APA91bGTi4hcsItv3MZ0FdG3uuvSXQmU_eHwNHtRfE1dPNb0miD4oudqSDELFkGDlgoK-7fUsen88PRbSKrB-mZttm68_x2RZ1rKI65o5xi8qoaM6bTcffc'),(29,'2026-03-25 14:45:15.471739','1995-03-17','Male','San Putrea',NULL,'0884850906','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyOSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1MjczMzI1LCJleHAiOjE3ODU2NDEzMjV9.2HPy_MCeSTFB-lkiyuBHBxNV5QAhR0PUBtRWFL9pGkw','eQHkUWEURmarYNzwI8FaMk:APA91bHkhgscITWqBtstPMX5UwzUzM1gPxkr2L13qKzr7IXyIvisKOPqSADEKhnSVEtPXNsU1QB8YjkYrWay82z0sybcM79vAmdcBQ8QU0fcWjweXYYtezY'),(30,'2026-03-25 20:30:54.286529','2001-03-10','Male','Seang Chanthhea',NULL,'012121211','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc0NzAwMDc0LCJleHAiOjE3ODUwNjgwNzR9.w2NUd_5jrFkAOAPTT6rPiZjsT5FzLWHzOGtqOIqqmyA','fmstEiiXQ-m4Hy8bVVRBJ7:APA91bGTi4hcsItv3MZ0FdG3uuvSXQmU_eHwNHtRfE1dPNb0miD4oudqSDELFkGDlgoK-7fUsen88PRbSKrB-mZttm68_x2RZ1rKI65o5xi8qoaM6bTcffc'),(31,'2026-04-04 11:17:11.431245','2008-04-08','Male','testiing',NULL,'088888888','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1MjkwNzAzLCJleHAiOjE3ODU2NTg3MDN9.ZKlXLs7p5QcEpDP-Rqp7oAgcr7ckR-lkSnJfUdJabIA','fmstEiiXQ-m4Hy8bVVRBJ7:APA91bGTi4hcsItv3MZ0FdG3uuvSXQmU_eHwNHtRfE1dPNb0miD4oudqSDELFkGDlgoK-7fUsen88PRbSKrB-mZttm68_x2RZ1rKI65o5xi8qoaM6bTcffc');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-04 16:22:32
