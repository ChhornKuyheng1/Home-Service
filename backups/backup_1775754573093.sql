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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (34,'2026-04-09',13.109163416962701,103.17547738552094,'Home','Default',32,'Battambang, 021402, Cambodia'),(35,'2026-04-09',13.09511499323366,103.20048529654741,'Home','Default',33,'Seoul, Street 209, Battambang, 021402, Cambodia'),(36,'2026-04-09',13.096919216081929,103.21716327220201,'Home','Active',34,'Sangkat Ratanak, Battambang, 021402, Cambodia'),(37,'2026-04-09',13.09817546986848,103.19257881492376,'Home','Default',34,'Battambang, 021402, Cambodia'),(38,'2026-04-09',13.0898456071915,103.1860027089715,'home','Default',35,'Sangkat Tuol Ta Aek, Battambang, 021402, Cambodia'),(39,'2026-04-09',13.091996677191888,103.20126179605722,'Home','Default',36,'Street 211, Battambang, 021402, Cambodia');
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
INSERT INTO `admins` VALUES (1,NULL,'hh@gmail.com','Male','Chhorn Kuyheng','$2a$10$5CIyw3XP2nz2zbq0kd3DveC8JASRSyNfzGdPptNikGiDr2CmUXDOG','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/te0vqbny0juirsfz5ffy','086223129','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNzc0Mjg0NTcxLCJleHAiOjE3ODQ2NTI1NzF9.zaliLNYZA9zF-IcAIssI1HnX2L_8Wvf6uGB5W3FrtdE'),(2,NULL,'admin@gmail.com','Male','admin','$2a$10$fH9cvM.XiHfjElT.jD3XXexD/B6q/7KrGZXDWEJRcfoWG3ZxYQkua',NULL,'09782132','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNzc1NzQxNjA5LCJleHAiOjE3ODYxMDk2MDl9.WD9m-GMWzKWV2DEm85egvSkakIhO-voAxK6bd5GsXQo'),(3,NULL,'Vid@gmail.com','Male','Ly David','$2a$10$IvrgCRB5dkH3kzDIUasto.0M61hrEr5kE0QHWV79h3IyIHJDRszi6','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/kq7j6me9fzasxhunnhyi','0964557838','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNzc0ODc2NTc1LCJleHAiOjE3ODUyNDQ1NzV9.mTK2vpKbaAxflexqLa-s92Q-PDOQz9vVNVGytKQYnyw');
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
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (113,'2026-04-09 22:40:00.000000',NULL,0,'Upcoming','2026-04-10','08:00:00',35,41,33,'2026-04-09 22:42:41.039592',NULL),(114,'2026-04-09 22:56:00.000000',NULL,0,'Upcoming','2026-04-11','08:00:00',34,43,32,'2026-04-09 22:59:19.957343',NULL),(115,'2026-04-09 23:03:00.000000',NULL,0,'Upcoming','2026-04-10','06:00:00',37,42,34,'2026-04-09 23:04:46.691013',NULL),(116,'2026-04-09 23:16:00.000000',NULL,0,'Cancelled','2026-04-11','12:00:00',34,43,32,NULL,NULL),(117,'2026-04-09 23:23:00.000000',NULL,0,'Upcoming','2026-04-10','10:00:00',38,42,35,'2026-04-09 23:25:51.160072',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_focus`
--

LOCK TABLES `job_focus` WRITE;
/*!40000 ALTER TABLE `job_focus` DISABLE KEYS */;
INSERT INTO `job_focus` VALUES (41,'2026-04-09','Decoration Wall',2,10,60,'Active',33,3,_binary ''),(42,'2026-04-09','ជួសជុល',3,20,40,'Active',34,1,_binary ''),(43,'2026-04-09','',4,100,150,'Active',35,2,_binary '');
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (33,'2026-04-09','Active',32,3),(34,'2026-04-09','Active',33,1),(35,'2026-04-09','Active',32,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=341 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (305,'2026-04-09 22:23:35.628900',_binary '','Your account is approved',32,'Register','APPROVED',32),(306,'2026-04-09 22:27:22.746961',_binary '','Your account is approved',33,'Register','APPROVED',33),(307,'2026-04-09 22:28:38.989650',_binary '','Payment completed successfully.',46,'PAYMENT','PAYMENT',32),(308,'2026-04-09 22:29:16.303971',_binary '','Payment completed successfully.',47,'PAYMENT','PAYMENT',33),(309,'2026-04-09 22:40:22.667959',_binary '','Your have a new booking request.',113,'BOOKING','BOOKING/PROVIDER',32),(310,'2026-04-09 22:42:41.078581',_binary '','Your booking is confirmed.',33,'BOOKING','BOOKING/USER',33),(311,'2026-04-09 22:56:13.646814',_binary '','Your have a new booking request.',114,'BOOKING','BOOKING/PROVIDER',32),(312,'2026-04-09 22:59:20.224527',_binary '','Your booking is confirmed.',32,'BOOKING','BOOKING/USER',32),(313,'2026-04-09 23:03:48.815623',_binary '','Your have a new booking request.',115,'BOOKING','BOOKING/PROVIDER',33),(314,'2026-04-09 23:04:46.711322',_binary '','Your booking is confirmed.',34,'BOOKING','BOOKING/USER',34),(315,'2026-04-09 23:16:49.046955',_binary '','Your have a new booking request.',116,'BOOKING','BOOKING/PROVIDER',32),(316,'2026-04-09 23:18:22.385907',_binary '','This booking has been Cancelled',32,'BOOKING','BOOKING/USER',32),(317,'2026-04-09 23:23:25.565653',_binary '','Your have a new booking request.',117,'BOOKING','BOOKING/PROVIDER',33),(318,'2026-04-09 23:25:51.194654',_binary '\0','Your booking is confirmed.',35,'BOOKING','BOOKING/USER',35),(319,'2026-04-09 23:29:01.020318',_binary '','Your subscription is within 5 day expired',32,'Subscription Expired','SUBSCRIPTION',32),(320,'2026-04-09 23:53:59.206503',_binary '\0','Your account is approved',34,'Register','APPROVED',34),(322,'2026-04-09 23:55:50.500979',_binary '\0','Your account is approved',36,'Register','APPROVED',36),(323,'2026-04-10 00:00:13.931027',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(324,'2026-04-10 00:00:13.936703',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(325,'2026-04-10 00:00:13.936703',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(326,'2026-04-10 00:00:13.926996',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(327,'2026-04-10 00:00:13.946704',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(328,'2026-04-10 00:00:13.952747',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(329,'2026-04-10 00:00:13.925876',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(330,'2026-04-10 00:00:13.924350',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(331,'2026-04-10 00:00:13.931027',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(332,'2026-04-10 00:06:47.553146',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(333,'2026-04-10 00:06:47.553146',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(334,'2026-04-10 00:06:47.553146',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(335,'2026-04-10 00:06:47.553146',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(336,'2026-04-10 00:06:47.551523',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(337,'2026-04-10 00:06:47.553146',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(338,'2026-04-10 00:06:47.553146',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(339,'2026-04-10 00:06:47.551523',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32),(340,'2026-04-10 00:06:47.553146',_binary '\0','Your subscription is expired now',32,'Subscription Expired','SUBSCRIPTION',32);
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
INSERT INTO `plans` VALUES (1,'2026-02-15','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/bjrigwolhfywlqcsw6wu',3,3,'Basic ( Free )',0,'Active'),(2,'2026-02-15','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/drf9hqcnonl6fx1zslol',3,3,'Basic',9.99,'Active'),(3,'2026-02-15','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/hkucwobhefxceckiunfy',10,10,'Premium',99,'Active'),(4,'2026-03-13','','Yearly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/tkupgznzl0oo1g7saj23',6,6,'Standard',49.99,'Active'),(16,'2026-03-17','','Monthly','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/x86cmwkags5m03rflkge',5,10,'Legacy',0.1,'Active');
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
INSERT INTO `portfolios` VALUES (31,'2026-04-09','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_600,q_auto,w_600/m6uqssy83uj34bxjzz4h',32);
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
INSERT INTO `providers` VALUES ('Ah Mao Fixing','2026-04-09 22:22:13.772405','bromao@gmail.com','FRIDAY','17:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/z2ios87lfcy7yepgqwhr',13.1091724,103.1754831,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/ndhfq9fbmebif6ecyj1s','MONDAY','08:00:00','Active',32,'Approved',10,'Fixing all stuff'),('Camfix Home Service','2026-04-09 22:26:30.028781','Chhorn Kuyheng@gmail.com','SUNDAY','21:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/ei7fwvxzeq1po9tyueyq',13.095871625492473,103.2019467651844,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/wxakc0oso7cysmljf2k4','MONDAY','06:00:00','Active',33,'Approved',9,'086223129,097322451 អាចទាកទង់សួរពត៌មានតាមលេងខាងលើបាន'),('BTB Fixing','2026-04-09 23:53:44.485100','btbfixing@gmail.com','SUNDAY','23:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/o83uzej8kp4y1tj6re21',13.077032195226499,103.15962184220552,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/yat4kagt8cymxsxxhzsw','MONDAY','06:00:00','Active',34,'Approved',15,NULL),('Sovath Fix','2026-04-09 23:54:53.983675','sovath@gmail.com','SUNDAY','19:00:00','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/gcha2mokuzyqhyogce9r',13.1090854,103.1754865,'http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/p0cqruzd397eqzgrlqw0','MONDAY','07:00:00','Active',36,'Approved',15,NULL);
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
INSERT INTO `subscriptions` VALUES (32,'2026-04-09','2026-04-09','Expired',1),(33,'2026-05-09','2026-04-09','Active',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (46,'Monthly','2026-05-09','2026-04-09 22:28:38.933979','2026-04-09','Completed',0,1,32),(47,'Monthly','2026-05-09','2026-04-09 22:29:16.265776','2026-04-09','Completed',0,1,33),(48,'Monthly','2026-05-09','2026-04-09 23:56:22.509360','2026-04-09','Declined',1,16,36),(49,'Monthly','2026-05-09','2026-04-09 23:57:11.090137','2026-04-09','Pending',1,16,34),(50,'Monthly','2026-05-09','2026-04-09 23:59:45.366426','2026-04-09','Declined',1,16,36),(51,'Monthly','2026-05-10','2026-04-10 00:07:08.605816','2026-04-10','Pending',0.1,16,36);
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (32,'2026-04-09 22:03:09.199756','2001-04-29','Male','Keo Veasna','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/bphvrbi7m5rvvoplxhwk','0123123321','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMiIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1NzUyNzMxLCJleHAiOjE3ODYxMjA3MzF9.6fviraGxooQb9rhuViOC_n_Joup6Apy1nzZFBZ9uphE','fl3SuXhgTySBva2Ov3Hi3-:APA91bEKpOah6YrGMUjVSf3kD8Z7uaqldx7WXQR_WXOyfLFcXDYxxU6ZXqhPWgRJhL06m3gG8tiZmpMGXq8_l_6xALrb3X4724VCi0uVHzM2T2z9_pVFPyI'),(33,'2026-04-09 22:21:41.632678','2008-04-13','Male','ឆន គុយហេង',NULL,'086223129','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMyIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1NzU0MDQ3LCJleHAiOjE3ODYxMjIwNDd9.ogoy8smM11O8RxYeF9VPZlj_SHwFodvN0CN8F_DNuR4','dbC9MOZ5Sya8hqihNdQCvW:APA91bF1OK0iW-h_YAttoM4Tb94lURIbJLTaooLc62-uw1vaqSI4n_ROIYjAl83DzAfNSSwry3m5CjXimvgfgDbuuy-mFxn-AsEUWq-n1EpmIIf3AxTgiUQ'),(34,'2026-04-09 22:46:36.568036','2008-04-13','Male','Sarang Heyo',NULL,'090909090','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzNCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1NzUzNjI0LCJleHAiOjE3ODYxMjE2MjR9.2XYjSNlSJombH8SnCPl3murFz0koKdz1LDoKn9LiObw','dw6yPeQmS5CNhF7cBsrz8E:APA91bGDFHo4UYmJB70Lve_DCmnyOUykxb8OS5Z-XYxJZVSN4Q7qjPRsCDIZSUKOcUpuol5omnqWZ5HaMK2xN1JCI11YLBPlWAIEcjmcpmWv4MF8Y4C7rtY'),(35,'2026-04-09 23:21:05.713818','2008-04-13','Male','jg mix lov','http://res.cloudinary.com/dytxsxpue/image/upload/c_fill,f_auto,g_auto,h_800,q_auto,w_800/rpe6a7cx73ab3rldx1n1','099887766','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzNSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1NzUyMDY0LCJleHAiOjE3ODYxMjAwNjR9.OORIx9O2VSXWWxjG92yZRku0Y7_xJeuL9JSmuT6DYbs','chZK1KPkSfuUiGGAnPbNrE:APA91bHp0TXa68rxj8NWl4Cd5hz5ADDL61Lcq7GqQhNPDtqKQ6N60OfqY6l2-GSTG-wyFKsaG9lK08Yt5H6kauEtzJJ_AnaK40fZN94kZiYiQIZ57RKWK6Q'),(36,'2026-04-09 23:53:06.032399','2000-04-01','Male','Preap Sovath',NULL,'088888888','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzNiIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzc1NzU0NTY3LCJleHAiOjE3ODYxMjI1Njd9.QKlfRRaQ84mFChuTTV1QVEli5Kz_nywPBfmUGIrPotE','fmstEiiXQ-m4Hy8bVVRBJ7:APA91bHcEJQL03zJhA8bebKhnoWUvUnYNZ-DUpxRxlldYy_XZgn2nzD-dWt3_1SJ3AufJM8FcIfmVH6qNdJN1o55nTZtSe0Nz3eiGy1GJ8HVVofUVa1Mbq8');
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

-- Dump completed on 2026-04-10  0:09:34
