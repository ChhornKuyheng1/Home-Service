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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (34,'2026-04-09',13.109163416962701,103.17547738552094,'Home','Default',32,'Battambang, 021402, Cambodia'),(35,'2026-04-09',13.09511499323366,103.20048529654741,'Home','Default',33,'Seoul, Street 209, Battambang, 021402, Cambodia'),(36,'2026-04-09',13.096919216081929,103.21716327220201,'Home','Active',34,'Sangkat Ratanak, Battambang, 021402, Cambodia'),(37,'2026-04-09',13.09817546986848,103.19257881492376,'Home','Default',34,'Battambang, 021402, Cambodia'),(38,'2026-04-09',13.0898456071915,103.1860027089715,'home','Default',35,'Sangkat Tuol Ta Aek, Battambang, 021402, Cambodia'),(39,'2026-04-09',13.091996677191888,103.20126179605722,'Home','Default',36,'Street 211, Battambang, 021402, Cambodia'),(40,'2026-04-19',13.116588469137367,103.19880355149508,'Home','Default',37,'Street 515, Battambang, 021402, Cambodia'),(41,'2026-04-20',13.095304722991216,103.19337408989668,'Home','Default',38,'Battambang, 021402, Cambodia'),(42,'2026-04-20',13.099568868898363,103.19809410721064,'Home','Default',39,'Lucky Laundry, Street 2, Battambang, 021402, Cambodia'),(43,'2026-04-20',13.093253935764567,103.2048187404871,'home','Default',40,'Mrs. Black Tuk Tuk Service, 85589793339, Street 208, Battambang, 021402, Cambodia'),(44,'2026-04-20',13.104865476630813,103.19381397217512,'HomE','Default',41,'Phsar Boeung Chhouk, Street 114, Battambang, 021402, Cambodia');
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
INSERT INTO `admins` VALUES (1,NULL,'hh@gmail.com','Male','Chhorn Kuyheng','$2a$10$5CIyw3XP2nz2zbq0kd3DveC8JASRSyNfzGdPptNikGiDr2CmUXDOG','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/te0vqbny0juirsfz5ffy','086223129','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNzc0Mjg0NTcxLCJleHAiOjE3ODQ2NTI1NzF9.zaliLNYZA9zF-IcAIssI1HnX2L_8Wvf6uGB5W3FrtdE'),(2,NULL,'admin@gmail.com','Male','admin','$2a$10$fH9cvM.XiHfjElT.jD3XXexD/B6q/7KrGZXDWEJRcfoWG3ZxYQkua','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/jp0sdbunmu8vxkbao9p1','09782132','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNzc2ODcwMzE4LCJleHAiOjE3ODcyMzgzMTh9.93ms2LM9RzrEI0dA1Mtvtin0pvhcIq8yup-kGi8ybfw'),(3,NULL,'Vid@gmail.com','Male','Ly David','$2a$10$IvrgCRB5dkH3kzDIUasto.0M61hrEr5kE0QHWV79h3IyIHJDRszi6','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/kq7j6me9fzasxhunnhyi','0964557838','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNzc2NTg1Mjg2LCJleHAiOjE3ODY5NTMyODZ9.OLd78nOfbU6QaJuCfzYhO74_-PZS-L001z8qjUK8KOg');
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
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (113,'2026-04-09 22:40:00.000000','2026-04-10 01:10:05.509536',20,'Completed','2026-04-10','08:00:00',35,41,33,'2026-04-09 22:42:41.039592','2026-04-10 00:58:30.756963'),(114,'2026-04-09 22:56:00.000000',NULL,0,'Cancelled','2026-04-11','08:00:00',34,43,32,'2026-04-09 22:59:19.957343',NULL),(115,'2026-04-09 23:03:00.000000','2026-04-17 10:44:36.644004',20,'Completed','2026-04-10','06:00:00',37,42,34,'2026-04-09 23:04:46.691013','2026-04-17 10:44:31.434486'),(116,'2026-04-09 23:16:00.000000',NULL,0,'Cancelled','2026-04-11','12:00:00',34,43,32,NULL,NULL),(117,'2026-04-09 23:23:00.000000','2026-04-17 10:44:22.031199',20,'Completed','2026-04-10','10:00:00',38,42,35,'2026-04-09 23:25:51.160072','2026-04-17 10:44:16.806151'),(118,'2026-04-10 01:29:00.000000','2026-04-10 01:36:19.250181',1.4,'Completed','2026-04-10','08:00:00',37,41,34,'2026-04-10 01:29:35.018965','2026-04-10 01:29:47.060332'),(119,'2026-04-10 01:46:00.000000',NULL,0,'Cancelled','2026-04-10','08:00:00',34,41,32,NULL,NULL),(120,'2026-04-18 14:53:00.000000',NULL,0,'Cancelled','2026-04-19','08:00:00',34,41,32,'2026-04-18 14:55:41.685344',NULL),(121,'2026-04-18 14:58:00.000000','2026-04-22 21:38:59.261399',20,'Completed','2026-04-19','08:00:00',34,41,32,'2026-04-18 15:01:32.727710','2026-04-22 21:38:34.201067'),(122,'2026-04-18 15:18:00.000000',NULL,0,'Cancelled','2026-04-18','16:00:00',35,46,33,NULL,NULL),(123,'2026-04-18 15:19:00.000000','2026-04-18 15:20:11.424106',30,'Completed','2026-04-18','16:00:00',35,46,33,'2026-04-18 15:19:33.739184','2026-04-18 15:19:54.936391'),(124,'2026-04-19 15:15:00.000000',NULL,0,'Cancelled','2026-04-20','08:00:00',35,41,33,NULL,NULL),(125,'2026-04-19 16:00:00.000000','2026-04-19 16:01:35.534210',60,'Completed','2026-04-19','16:00:00',39,48,36,'2026-04-19 16:00:48.270324','2026-04-19 16:01:09.898795'),(126,'2026-04-20 10:39:00.000000','2026-04-20 11:12:16.106649',45,'Completed','2026-04-20','11:00:00',39,47,36,'2026-04-20 10:39:36.307922','2026-04-20 10:40:43.625198'),(127,'2026-04-20 10:56:00.000000',NULL,0,'In Progress','2026-04-20','14:00:00',39,47,36,'2026-04-20 10:57:10.791067','2026-04-20 11:06:08.796076'),(128,'2026-04-20 10:56:00.000000',NULL,0,'In Progress','2026-04-20','17:00:00',39,47,36,'2026-04-20 11:01:09.918309','2026-04-20 11:02:50.740994'),(129,'2026-04-20 11:12:00.000000',NULL,0,'Upcoming','2026-04-20','20:00:00',39,47,36,'2026-04-20 11:13:02.773229',NULL),(130,'2026-04-22 21:37:00.000000',NULL,0,'Requested','2026-04-23','06:00:00',34,42,32,NULL,NULL),(131,'2026-04-22 22:04:00.000000',NULL,0,'Upcoming','2026-04-23','08:00:00',34,41,32,'2026-04-22 22:05:27.675984',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_focus`
--

LOCK TABLES `job_focus` WRITE;
/*!40000 ALTER TABLE `job_focus` DISABLE KEYS */;
INSERT INTO `job_focus` VALUES (41,'2026-04-09','Decoration Wall',2,10,60,'Active',33,3,_binary ''),(42,'2026-04-09','ជួសជុល',2,20,40,'Active',34,1,_binary ''),(43,'2026-04-09','',4,100,150,'Active',35,2,_binary ''),(44,'2026-04-11','',7,100,150,'Inactive',36,1,_binary '\0'),(45,'2026-04-11','',1,20,40,'Inactive',36,37,_binary '\0'),(46,'2026-04-17','',1,20,40,'Active',37,2,_binary ''),(47,'2026-04-18','',3,20,40,'Active',38,1,_binary '\0'),(48,'2026-04-19','super fixing',2,50,80,'Active',39,1,_binary '\0');
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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (33,'2026-04-09','Active',32,3),(34,'2026-04-09','Active',33,1),(35,'2026-04-09','Active',32,2),(36,'2026-04-11','Active',36,1),(37,'2026-04-17','Active',33,2),(38,'2026-04-18','Active',34,1),(39,'2026-04-19','Active',37,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=467 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (305,'2026-04-09 22:23:35.628900',_binary '','Your account is approved',32,'Register','APPROVED',32),(306,'2026-04-09 22:27:22.746961',_binary '','Your account is approved',33,'Register','APPROVED',33),(307,'2026-04-09 22:28:38.989650',_binary '','Payment completed successfully.',46,'PAYMENT','PAYMENT',32),(308,'2026-04-09 22:29:16.303971',_binary '','Payment completed successfully.',47,'PAYMENT','PAYMENT',33),(309,'2026-04-09 22:40:22.667959',_binary '','Your have a new booking request.',113,'BOOKING','BOOKING/PROVIDER',32),(310,'2026-04-09 22:42:41.078581',_binary '','Your booking is confirmed.',33,'BOOKING','BOOKING/USER',33),(311,'2026-04-09 22:56:13.646814',_binary '','Your have a new booking request.',114,'BOOKING','BOOKING/PROVIDER',32),(312,'2026-04-09 22:59:20.224527',_binary '','Your booking is confirmed.',32,'BOOKING','BOOKING/USER',32),(313,'2026-04-09 23:03:48.815623',_binary '','Your have a new booking request.',115,'BOOKING','BOOKING/PROVIDER',33),(314,'2026-04-09 23:04:46.711322',_binary '','Your booking is confirmed.',34,'BOOKING','BOOKING/USER',34),(315,'2026-04-09 23:16:49.046955',_binary '','Your have a new booking request.',116,'BOOKING','BOOKING/PROVIDER',32),(316,'2026-04-09 23:18:22.385907',_binary '','This booking has been Cancelled',32,'BOOKING','BOOKING/USER',32),(317,'2026-04-09 23:23:25.565653',_binary '','Your have a new booking request.',117,'BOOKING','BOOKING/PROVIDER',33),(318,'2026-04-09 23:25:51.194654',_binary '\0','Your booking is confirmed.',35,'BOOKING','BOOKING/USER',35),(320,'2026-04-09 23:53:59.206503',_binary '','Your account is approved',34,'Register','APPROVED',34),(322,'2026-04-09 23:55:50.500979',_binary '','Your account is approved',36,'Register','APPROVED',36),(323,'2026-04-10 00:00:13.931027',_binary '','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(360,'2026-04-10 00:32:34.954576',_binary '','Payment completed successfully.',54,'PAYMENT','PAYMENT',36),(361,'2026-04-10 00:58:31.523316',_binary '','Your service is now in InProgress.',33,'BOOKING','BOOKING/USER',33),(362,'2026-04-10 01:10:05.663847',_binary '','Your booking is Completed',33,'BOOKING','USER',33),(363,'2026-04-10 01:29:19.582194',_binary '','Your have a new booking request.',118,'BOOKING','BOOKING/PROVIDER',32),(364,'2026-04-10 01:29:35.069532',_binary '','Your booking is confirmed.',34,'BOOKING','BOOKING/USER',34),(365,'2026-04-10 01:29:47.078284',_binary '','Your service is now in InProgress.',34,'BOOKING','BOOKING/USER',34),(366,'2026-04-10 01:36:19.475197',_binary '','Your booking is Completed',34,'BOOKING','USER',34),(367,'2026-04-10 01:45:07.131601',_binary '','This booking has been Cancelled',32,'BOOKING','BOOKING/USER',32),(368,'2026-04-10 01:46:31.556452',_binary '','Your have a new booking request.',119,'BOOKING','BOOKING/PROVIDER',32),(369,'2026-04-10 01:47:16.793991',_binary '','This booking has been Cancelled',32,'BOOKING','BOOKING/USER',32),(370,'2026-04-11 12:15:16.862764',_binary '','Your subscription is expired',33,'SUBSCRIPTION','SUBSCRIPTION',33),(375,'2026-04-17 10:44:16.945580',_binary '\0','Your service is now in InProgress.',35,'BOOKING','BOOKING/USER',35),(376,'2026-04-17 10:44:22.062650',_binary '\0','Your booking is Completed',35,'BOOKING','USER',35),(377,'2026-04-17 10:44:31.467723',_binary '','Your service is now in InProgress.',34,'BOOKING','BOOKING/USER',34),(378,'2026-04-17 10:44:36.945640',_binary '','Your booking is Completed',34,'BOOKING','USER',34),(379,'2026-04-17 19:35:29.665321',_binary '\0','Your account is block',36,'BLOCK','PROVIDER',36),(380,'2026-04-17 19:56:34.988547',_binary '\0','Your account is unblock',36,'UNBLOCK','PROVIDER',36),(381,'2026-04-17 19:58:42.444239',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(382,'2026-04-17 20:00:28.192256',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(383,'2026-04-17 20:00:54.237722',_binary '','Your account is unblock',32,'UNBLOCK','PROVIDER',32),(384,'2026-04-17 20:01:11.067089',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(385,'2026-04-17 20:04:22.324061',_binary '','Your account is approved',32,'Register','APPROVED',32),(386,'2026-04-17 20:13:02.931149',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(387,'2026-04-17 20:23:42.011820',_binary '','Your account is unblock',32,'UNBLOCK','PROVIDER',32),(388,'2026-04-17 20:23:50.013832',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(389,'2026-04-17 20:29:38.840536',_binary '','Your account is unblock',32,'UNBLOCK','PROVIDER',32),(390,'2026-04-17 20:29:51.700892',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(391,'2026-04-17 20:30:49.080834',_binary '','Your account is unblock',32,'UNBLOCK','PROVIDER',32),(392,'2026-04-17 20:32:17.992184',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(393,'2026-04-17 20:32:49.633001',_binary '','Your account is unblock',32,'UNBLOCK','PROVIDER',32),(394,'2026-04-17 20:33:09.170255',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(395,'2026-04-17 20:33:46.687527',_binary '','Your account is unblock',32,'UNBLOCK','PROVIDER',32),(396,'2026-04-17 20:34:01.182359',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(397,'2026-04-17 20:34:43.502389',_binary '','Your account is unblock',32,'UNBLOCK','PROVIDER',32),(398,'2026-04-17 20:35:01.934169',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(399,'2026-04-17 20:38:32.879157',_binary '','Your account is unblock',32,'UNBLOCK','PROVIDER',32),(400,'2026-04-17 20:38:42.118795',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(401,'2026-04-17 20:51:05.950973',_binary '','Your account is unblock',32,'UNBLOCK','PROVIDER',32),(402,'2026-04-17 20:55:56.826728',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(403,'2026-04-17 20:56:02.545768',_binary '','Your account is unblock',32,'UNBLOCK','PROVIDER',32),(404,'2026-04-17 20:56:07.376193',_binary '','Your account is block',33,'BLOCK','PROVIDER',33),(405,'2026-04-17 20:56:56.915935',_binary '','Your account is unblock',33,'UNBLOCK','PROVIDER',33),(406,'2026-04-18 11:55:27.633687',_binary '','Your account is block',34,'BLOCK','PROVIDER',34),(407,'2026-04-18 12:53:10.519005',_binary '','Your account is unblock',34,'UNBLOCK','PROVIDER',34),(408,'2026-04-18 12:53:50.926123',_binary '','Payment completed successfully.',56,'PAYMENT','PAYMENT',34),(409,'2026-04-18 14:45:50.476745',_binary '','Payment completed successfully.',60,'PAYMENT','PAYMENT',34),(410,'2026-04-18 14:53:10.832949',_binary '','Your have a new booking request.',120,'BOOKING','BOOKING/PROVIDER',32),(411,'2026-04-18 14:54:53.722965',_binary '','Your booking is confirmed.',32,'BOOKING','BOOKING/USER',32),(412,'2026-04-18 14:54:59.350070',_binary '','Your booking is confirmed.',32,'BOOKING','BOOKING/USER',32),(413,'2026-04-18 14:55:23.244775',_binary '','Your booking is confirmed.',32,'BOOKING','BOOKING/USER',32),(414,'2026-04-18 14:55:38.822523',_binary '','Your booking is confirmed.',32,'BOOKING','BOOKING/USER',32),(415,'2026-04-18 14:55:41.719598',_binary '','Your booking is confirmed.',32,'BOOKING','BOOKING/USER',32),(416,'2026-04-18 14:57:26.229644',_binary '','This booking has been Cancelled',32,'BOOKING','BOOKING/USER',32),(417,'2026-04-18 14:58:01.825646',_binary '','Your have a new booking request.',121,'BOOKING','BOOKING/PROVIDER',32),(418,'2026-04-18 14:58:33.103037',_binary '','Your booking is confirmed.',32,'BOOKING','BOOKING/USER',32),(419,'2026-04-18 15:01:32.808745',_binary '','Your booking is confirmed.',32,'BOOKING','BOOKING/USER',32),(420,'2026-04-18 15:18:18.273977',_binary '','Your have a new booking request.',122,'BOOKING','BOOKING/PROVIDER',33),(421,'2026-04-18 15:18:31.554438',_binary '','Your booking is Cancelled',33,'BOOKING','USER/PROVIDER',33),(422,'2026-04-18 15:19:03.604012',_binary '','Your have a new booking request.',123,'BOOKING','BOOKING/PROVIDER',33),(423,'2026-04-18 15:19:33.763450',_binary '','Your booking is confirmed.',33,'BOOKING','BOOKING/USER',33),(424,'2026-04-18 15:19:54.960591',_binary '','Your service is now in InProgress.',33,'BOOKING','BOOKING/USER',33),(425,'2026-04-18 15:20:11.445809',_binary '','Your booking is Completed',33,'BOOKING','USER',33),(426,'2026-04-18 15:37:49.739310',_binary '','Payment completed successfully.',63,'PAYMENT','PAYMENT',34),(427,'2026-04-19 15:15:25.506247',_binary '','Your have a new booking request.',124,'BOOKING','BOOKING/PROVIDER',32),(428,'2026-04-19 15:26:50.753016',_binary '','Your account is block',32,'BLOCK','PROVIDER',32),(430,'2026-04-19 15:51:19.217958',_binary '\0','Your account is approved',37,'Register','APPROVED',37),(431,'2026-04-19 15:52:11.829368',_binary '\0','Payment completed successfully.',64,'PAYMENT','PAYMENT',37),(432,'2026-04-19 16:00:03.907643',_binary '','Your have a new booking request.',125,'BOOKING','BOOKING/PROVIDER',37),(433,'2026-04-19 16:00:48.302831',_binary '\0','Your booking is confirmed.',36,'BOOKING','BOOKING/USER',36),(434,'2026-04-19 16:01:09.937051',_binary '\0','Your service is now in InProgress.',36,'BOOKING','BOOKING/USER',36),(435,'2026-04-19 16:01:35.551777',_binary '\0','Your booking is Completed',36,'BOOKING','USER',36),(436,'2026-04-20 10:39:24.608180',_binary '','Your have a new booking request.',126,'BOOKING','BOOKING/PROVIDER',34),(437,'2026-04-20 10:39:36.359452',_binary '\0','Your booking is confirmed.',36,'BOOKING','BOOKING/USER',36),(438,'2026-04-20 10:40:43.648185',_binary '\0','Your service is now in InProgress.',36,'BOOKING','BOOKING/USER',36),(439,'2026-04-20 10:56:26.785148',_binary '\0','Your have a new booking request.',127,'BOOKING','BOOKING/PROVIDER',34),(440,'2026-04-20 10:56:54.030307',_binary '\0','Your have a new booking request.',128,'BOOKING','BOOKING/PROVIDER',34),(441,'2026-04-20 10:57:10.805900',_binary '\0','Your booking is confirmed.',36,'BOOKING','BOOKING/USER',36),(442,'2026-04-20 11:01:09.930284',_binary '\0','Your booking is confirmed.',36,'BOOKING','BOOKING/USER',36),(443,'2026-04-20 11:02:50.756760',_binary '\0','Your service is now in InProgress.',36,'BOOKING','BOOKING/USER',36),(444,'2026-04-20 11:06:08.809233',_binary '\0','Your service is now in InProgress.',36,'BOOKING','BOOKING/USER',36),(445,'2026-04-20 11:12:16.119929',_binary '\0','Your booking is Completed',36,'BOOKING','USER',36),(446,'2026-04-20 11:12:45.535498',_binary '\0','Your have a new booking request.',129,'BOOKING','BOOKING/PROVIDER',34),(447,'2026-04-20 11:13:02.792515',_binary '\0','Your booking is confirmed.',36,'BOOKING','BOOKING/USER',36),(450,'2026-04-20 15:29:28.633619',_binary '','Your account is approved',38,'Register','APPROVED',38),(451,'2026-04-20 15:31:04.618957',_binary '','Payment completed successfully.',65,'PAYMENT','PAYMENT',38),(452,'2026-04-20 16:01:18.551956',_binary '\0','Your account is approved',40,'Register','APPROVED',40),(453,'2026-04-20 16:19:34.748995',_binary '\0','Payment completed successfully.',66,'PAYMENT','PAYMENT',40),(458,'2026-04-20 19:28:12.338992',_binary '','Your account is approved',41,'Register','APPROVED',41),(459,'2026-04-20 19:28:32.466465',_binary '','Payment completed successfully.',67,'PAYMENT','PAYMENT',41),(460,'2026-04-22 21:37:18.137810',_binary '\0','Your have a new booking request.',130,'BOOKING','BOOKING/PROVIDER',33),(461,'2026-04-22 21:38:21.546637',_binary '\0','This booking has been Cancelled',33,'BOOKING','BOOKING/USER',33),(462,'2026-04-22 21:38:34.224513',_binary '','Your service is now in InProgress.',32,'BOOKING','BOOKING/USER',32),(463,'2026-04-22 21:38:59.282048',_binary '','Your booking is Completed',32,'BOOKING','USER',32),(464,'2026-04-22 21:39:27.838321',_binary '','Your account is unblock',32,'UNBLOCK','PROVIDER',32),(465,'2026-04-22 22:04:58.458067',_binary '','Your have a new booking request.',131,'BOOKING','BOOKING/PROVIDER',32),(466,'2026-04-22 22:05:27.704208',_binary '','Your booking is confirmed.',32,'BOOKING','BOOKING/USER',32);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plans`
--

LOCK TABLES `plans` WRITE;
/*!40000 ALTER TABLE `plans` DISABLE KEYS */;
INSERT INTO `plans` VALUES (1,'2026-02-15','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/bjrigwolhfywlqcsw6wu',3,3,'Basic ( Free )',0,'Active'),(2,'2026-02-15','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/drf9hqcnonl6fx1zslol',3,3,'Basic',9.99,'Active'),(3,'2026-02-15','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/hkucwobhefxceckiunfy',10,10,'Premium',99,'Active'),(4,'2026-03-13','','Yearly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/tkupgznzl0oo1g7saj23',6,6,'Standard',49.99,'Active'),(16,'2026-03-17','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/x86cmwkags5m03rflkge',5,10,'Legacy',0.1,'Active'),(18,'2026-04-22','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,h_600,w_600/bqyjy2mmxexij7mnrym2',5,5,'ASD',123,'Active');
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portfolios`
--

LOCK TABLES `portfolios` WRITE;
/*!40000 ALTER TABLE `portfolios` DISABLE KEYS */;
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
INSERT INTO `providers` VALUES ('Ah Mao Fix','2026-04-09 22:22:13.772405','bromao@gmail.com','FRIDAY','17:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/z2ios87lfcy7yepgqwhr',13.1091724,103.1754831,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/ndhfq9fbmebif6ecyj1s','MONDAY','08:00:00','Active',32,'Approved',15,'Fixing all stuff'),('Camfix Home Service','2026-04-09 22:26:30.028781','Chhorn Kuyheng@gmail.com','SUNDAY','21:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/ei7fwvxzeq1po9tyueyq',13.095871625492473,103.2019467651844,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/wxakc0oso7cysmljf2k4','MONDAY','06:00:00','Active',33,'Approved',9,'086223129,097322451 អាចទាកទង់សួរពត៌មានតាមលេងខាងលើបាន'),('BTB Fixing','2026-04-09 23:53:44.485100','btbfixing@gmail.com','SUNDAY','23:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/o83uzej8kp4y1tj6re21',13.077032195226499,103.15962184220552,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/yat4kagt8cymxsxxhzsw','MONDAY','06:00:00','Active',34,'Approved',15,NULL),('Sovath Fix','2026-04-09 23:54:53.983675','sovath@gmail.com','SUNDAY','19:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/gcha2mokuzyqhyogce9r',13.1090854,103.1754865,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/p0cqruzd397eqzgrlqw0','MONDAY','07:00:00','Active',36,'Approved',15,NULL),('Thang kimlong','2026-04-19 15:46:39.093238','thangkimlong@gmail.com','SUNDAY','18:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/bxy4qsschv9wxq8moypt',13.1165686,103.1987969,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/mdeog1dmrf8vwfxwr92p','TUESDAY','07:00:00','Active',37,'Approved',15,NULL),('testing','2026-04-20 15:26:35.395608','testing@gmail.com','SUNDAY','17:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fit,h_1000,w_1000/miomz4o4rb7cdo8gwvax',0,0,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fit,h_1000,w_1000/qzuordadoiv7olmxxeja','MONDAY','06:00:00','Inactive',38,'Approved',13,''),('testing2','2026-04-20 15:55:24.415678','testing2@gmail.com','TUESDAY','18:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fit,h_1000,w_1000/seuhn4symcjmxiz62kax',11.556374,104.92821,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fit,h_1000,w_1000/unyyoklam5ekjhkeqpu9','MONDAY','06:00:00','Active',40,'Approved',15,NULL),('testing','2026-04-20 19:27:55.907934','testing3@gmail.com','SUNDAY','17:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fit,h_1000,w_1000/kdvouhbaudqw87di2w7j',13.09236569141222,103.206219188869,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fit,h_1000,w_1000/wim8t17crjzmdf5oatql','MONDAY','06:00:00','Active',41,'Approved',15,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_images`
--

LOCK TABLES `review_images` WRITE;
/*!40000 ALTER TABLE `review_images` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'2026-02-15','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/v6vt7at661p3s0ss9kyd','Plumbing','ACTIVE'),(2,'2026-02-15','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/bieak5wrunu3c2pzkdfz','Electrical','ACTIVE'),(3,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/narentuo8iirr5r3mo7v','Painting','ACTIVE'),(5,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/t4j2cx8hudqybedfd3f4','Carpentry','ACTIVE'),(6,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/mvignhupbaa3dchtxtkc','Gardening','ACTIVE'),(7,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/sqse1jedksznlji0ipuc','Waterproofing','ACTIVE'),(8,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/v7xo8hbtdz6lgbq7paie','AC Repair','ACTIVE'),(9,'2026-03-10','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/p1vtjhb0l0svtom9g89k','Cleaning','ACTIVE'),(11,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/xxo5ryt8vnzmf6vmhejw','Flooring','ACTIVE'),(58,'2026-04-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/vvzfchk1pbfmvkjhl7l3','Leang Jan','ACTIVE');
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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (1,'2026-02-15','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248799/em3j6ukiezlxjjfzvtgn.jpg','Pipe Repair',1),(2,'2026-02-15','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248269/sshpcw9hy4fewqeruzkl.jpg','Electrical Installation',2),(3,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248339/zdqrv6haulnhd2xds49v.jpg','Wall Painting',3),(5,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773496856/zoqlows6cj32djqmwd6w.jpg','Garden Cutting',6),(6,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248607/a4hyqsa83rbgjn8xui3c.png','Roof Waterproofing',7),(7,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248565/wrg23fqq1paa7fabdjjz.png','AC Maintenance',8),(8,'2026-02-18','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248656/zuhjichueliseh8dghg6.png','Woodwork',5),(10,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/v1773336103/vb0exrdvnveqyjy1rgew.jpg','Deep Cleaning',9),(11,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248337/g3zw3rkbexijamcwszbj.jpg','Floor Cleaning',9),(12,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/v1773497861/nfaeo20rcjz5vc4lcs8q.jpg','Bathroom Cleaning',9),(13,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/v1773248303/kgscn0ghsqsilsaximsv.jpg','Cable Fixing',2),(14,'2026-03-11','http://res.cloudinary.com/dytxsxpue/image/upload/v1773763535/igsgbi3u1t3gqexz8wtp.jpg','Floor Installation',11),(33,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/osxeazwghujt6x3bpooa','Kitchen Cleaning',9),(34,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/lrilyzylczdaqlvtcxki','Window Cleaning',9),(35,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/eetajpl5esw6ptvzaqeh','Leak Repair',1),(36,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/jgcjfxjvd6lp6soo78pe','Drain Cleaning',1),(37,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/dvlycwbdkkemaldpo2kc','Toilet Repair & Installation',1),(38,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/tggo34kypzigk7b8t1se','Lighting Installation & Repair',2),(39,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/y8ohi3jah3kex1bwfisz','Switch & Socket Installation',2),(41,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/ug139iad3ifr1pqy8unm','Cabinet Installation',5),(42,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/guqkyywbsb0xvnnm8jig','Furniture Repair',5),(43,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/araxhynwfzqrsrojqejd','Garden Cleanup',6),(44,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/vi1qwcrtzqwmhyaqbrhf','Planting & Landscaping',6),(45,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/ytl3ivoklilcxlqowfbd','Bathroom Waterproofing',7),(46,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/cya71coulqbvxl1vat7m','Basement Waterproofing',7),(47,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/uyhmgvtu39gdwfu3irhz','AC Installation',8),(48,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/zvnhzjxef973aycxsbvj','AC Gas Refilling',8),(49,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/ao3qchza5rohiaf1lhbt','Floor Repair',11),(50,'2026-03-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/aanjpcorinjb2ocqztsr','Floor Polishing',11),(51,'2026-04-19','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/vwgsbtjkpyw2sousnqub','Leang Jan Dai',58);
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
INSERT INTO `subscriptions` VALUES (32,'2026-05-09','2026-04-09','Active',1),(33,'2026-05-09','2026-04-09','Active',1),(34,'2026-05-18','2026-04-18','Active',16),(36,'2026-05-10','2026-04-10','Active',16),(37,'2026-05-19','2026-04-19','Active',16),(38,'2026-05-20','2026-04-20','Active',1),(40,'2026-05-20','2026-04-20','Active',16),(41,'2026-04-20','2026-04-20','Active',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (46,'Monthly','2026-05-09','2026-04-09 22:28:38.933979','2026-04-09','Completed',0,1,32),(47,'Monthly','2026-05-09','2026-04-09 22:29:16.265776','2026-04-09','Completed',0,1,33),(48,'Monthly','2026-05-09','2026-04-09 23:56:22.509360','2026-04-09','Declined',1,16,36),(50,'Monthly','2026-05-09','2026-04-09 23:59:45.366426','2026-04-09','Declined',1,16,36),(51,'Monthly','2026-05-10','2026-04-10 00:07:08.605816','2026-04-10','Declined',0.1,16,36),(52,'Monthly','2026-05-10','2026-04-10 00:10:41.515426','2026-04-10','Declined',0.1,16,36),(53,'Monthly','2026-05-10','2026-04-10 00:29:04.579829','2026-04-10','Declined',0.1,16,36),(54,'Monthly','2026-05-10','2026-04-10 00:32:07.106461','2026-04-10','Completed',0.1,16,36),(55,'Monthly','2026-05-11','2026-04-11 14:58:19.133842','2026-04-11','Declined',0.1,16,34),(56,'Monthly','2026-05-18','2026-04-18 12:53:25.430938','2026-04-18','Completed',0.1,16,34),(57,'Monthly','2026-05-18','2026-04-18 14:36:31.020759','2026-04-18','Declined',9.99,2,34),(58,'Monthly','2026-05-18','2026-04-18 14:37:21.197836','2026-04-18','Declined',9.99,2,34),(59,'Monthly','2026-05-18','2026-04-18 14:44:58.578940','2026-04-18','Declined',0.1,16,34),(60,'Monthly','2026-05-18','2026-04-18 14:45:28.527147','2026-04-18','Completed',0.1,16,34),(61,'Monthly','2026-05-18','2026-04-18 15:35:57.482245','2026-04-18','Declined',0.1,16,34),(62,'Monthly','2026-05-18','2026-04-18 15:36:06.856789','2026-04-18','Declined',0.1,16,34),(63,'Monthly','2026-05-18','2026-04-18 15:37:18.271489','2026-04-18','Completed',0.1,16,34),(64,'Monthly','2026-05-19','2026-04-19 15:51:49.912498','2026-04-19','Completed',0.1,16,37),(65,'Monthly','2026-05-20','2026-04-20 15:31:04.428761','2026-04-20','Completed',0,1,38),(66,'Monthly','2026-05-20','2026-04-20 16:19:15.923550','2026-04-20','Completed',0.1,16,40),(67,'Monthly','2026-05-20','2026-04-20 19:28:32.394210','2026-04-20','Completed',0,1,41);
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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (32,'2026-04-09 22:03:09.199756','2001-04-29','Male','Keo Veasna','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/bphvrbi7m5rvvoplxhwk','0123123321','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMiIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc2ODcwMjgwLCJleHAiOjE3ODcyMzgyODB9.k4lwHIq7b1yomlCLa69jr-wKWhpK8xVpKJo_W2iKHZI','dw6yPeQmS5CNhF7cBsrz8E:APA91bFBEDhZS8TiITQyluNAdVZRNtKp3_JR-Z1XfKJKUq_GdcTQZaRV4G6Q0NRxs6PK-vUIRREBlHGSND3dlM6Q9yodqs08oDEwPMDmwBfkl8g8RZOVwrE'),(33,'2026-04-09 22:21:41.632678','2008-04-13','Male','Chhorn Kuyheng','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/knj79owt6uvvlu01hw4v','086223123','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMyIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc2NTg2NDk2LCJleHAiOjE3ODY5NTQ0OTZ9.pXYI65yHUFDpPG5OZB2HALqeOR2dRr3uPmnmOXabLKQ','dbC9MOZ5Sya8hqihNdQCvW:APA91bF1OK0iW-h_YAttoM4Tb94lURIbJLTaooLc62-uw1vaqSI4n_ROIYjAl83DzAfNSSwry3m5CjXimvgfgDbuuy-mFxn-AsEUWq-n1EpmIIf3AxTgiUQ'),(34,'2026-04-09 22:46:36.568036','2008-04-13','Male','Sarang Heyo',NULL,'090909090','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzNCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc2NjU4NTI4LCJleHAiOjE3ODcwMjY1Mjh9.XmP5jr7TWxhDlINKkFgo9nb76-iUcZOnvWVVFulyNyE','d1x-5KrtR4iobRbs4ipPXQ:APA91bFMt-H5axbo_yWiRv3R9SAZrhjG9voSPnUyjhmNxg2nch3rrsnZ77OYH2cgSMLUy60qk5v2_xazcvi80mghnE69PfTCdKmNg8VXQYOkMILjhoPwKN4'),(35,'2026-04-09 23:21:05.713818','2008-04-13','Male','jg mix lov','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/rpe6a7cx73ab3rldx1n1','099887766','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzNSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc2NjY0NDMzLCJleHAiOjE3ODcwMzI0MzN9.wDmMrBUz_XMEoWmUGtV3SF1IArCZ9jpSa7sF41yCo7I','d1x-5KrtR4iobRbs4ipPXQ:APA91bHN37XUmtSy5QfNXOmbsNW_LpxBeecwDXdRrv-S5LBVv4jEVBAosGTPo6rE7WCONZ1-kghdMhV7i1dmIEWcO5RSzqZ_lcvLiwD-ro0Gfp0dxJTKGAY'),(36,'2026-04-09 23:53:06.032399','2000-04-01','Male','Preap Sovath',NULL,'088888888','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzNiIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc2NjU2MzI5LCJleHAiOjE3ODcwMjQzMjl9.LG8Nk9iskNvv_TcKMCMSRu_NRdRQ25cfIr9nmD_RyhQ','f4GFEJP9R8GqBBRhuo1bYK:APA91bEucDeFmN2JmMWhcsN9FE_DnDKs_JnGDZ5K_i08XTFjYv7E90Qn3yfMFKBgRrLyI4uEbRVEGzPC6BjZgUzFJmV33XTa2douG6OC-az6anIwtCNORX0'),(37,'2026-04-19 15:41:11.677546','2008-04-22','Male','Thang Kimlong',NULL,'087946060','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzNyIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc2NjgyODg4LCJleHAiOjE3ODcwNTA4ODh9.dOepPNu-TOEG9yynnbmVouF5ZA1PWt5pjXBT2kY9IPU','dw6yPeQmS5CNhF7cBsrz8E:APA91bFBEDhZS8TiITQyluNAdVZRNtKp3_JR-Z1XfKJKUq_GdcTQZaRV4G6Q0NRxs6PK-vUIRREBlHGSND3dlM6Q9yodqs08oDEwPMDmwBfkl8g8RZOVwrE'),(38,'2026-04-20 13:01:08.889059','2008-04-24','Male','testing',NULL,'012121211','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzOCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc2NjczMTY3LCJleHAiOjE3ODcwNDExNjd9.9tV_2MIRlpbxIPwDzw_4NRQFjeEepEdGN1S0o8KFBr8','d1x-5KrtR4iobRbs4ipPXQ:APA91bFfsevRs5P1lxuh8Mf20S0qb3Ra08xv27szzV6v1jQP_gfz0p2BvM9nIRwo_w-345VMDGbrnmRM1TgrPgbnNwqeiEN5wmFHSMLLpCpumuy70zXz7cM'),(39,'2026-04-20 13:02:06.063902','2008-04-24','Male','Heng','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/ybhutfketiybmbwqvp4n','086223129','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzOSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc2Njg4NjY3LCJleHAiOjE3ODcwNTY2Njd9.Upa021DCI2aDVEwghrEhA8vEPR8hrLCsQMzlCHcJLTk','dbC9MOZ5Sya8hqihNdQCvW:APA91bF1OK0iW-h_YAttoM4Tb94lURIbJLTaooLc62-uw1vaqSI4n_ROIYjAl83DzAfNSSwry3m5CjXimvgfgDbuuy-mFxn-AsEUWq-n1EpmIIf3AxTgiUQ'),(40,'2026-04-20 15:51:02.592563','2008-04-24','Male','testing2',NULL,'0123123123','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc2Njc2NjM4LCJleHAiOjE3ODcwNDQ2Mzh9.ZXUBP4u6BqF3vt3lCzbterZgaTFKe1yGMhfZ3q5631Y','d1x-5KrtR4iobRbs4ipPXQ:APA91bFXJABCJZylp3LkAFahYWs6aGP7wrFEEzWdEUKH0lyN-nZeQE29QshU6K31XeXIbxa0GPB0arBxKGIbFlix6rYpmF67EIDkbZMmiQ1POkS1JvYjQJQ'),(41,'2026-04-20 18:32:58.669582','2008-04-30','Female','testing3',NULL,'0987654321','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc2NjkxNDExLCJleHAiOjE3ODcwNTk0MTF9.zWjPE2fRDMA2NFy9GvV8sfrOrmtyyJ9Ao0Sh9PJOK08','d1x-5KrtR4iobRbs4ipPXQ:APA91bFXJABCJZylp3LkAFahYWs6aGP7wrFEEzWdEUKH0lyN-nZeQE29QshU6K31XeXIbxa0GPB0arBxKGIbFlix6rYpmF67EIDkbZMmiQ1POkS1JvYjQJQ');
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

-- Dump completed on 2026-04-22 22:09:03
