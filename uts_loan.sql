-- MySQL dump 10.13  Distrib 8.0.12, for Linux (x86_64)
--
-- Host: localhost    Database: uts_loan
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admin` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','123456'),(2,'adminTest','111');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `application` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `status` varchar(10) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL,
  `submit_date` date DEFAULT NULL,
  `result_date` date DEFAULT NULL,
  `content` varchar(5000) DEFAULT NULL,
  `student_id` int(10) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `pay_back_years` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `sum` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKigm5jb0xdqnqjelaagm14dcva` (`student_id`),
  KEY `FKc9isrtiiftpc70gviknc26635` (`manager_id`),
  CONSTRAINT `FKc9isrtiiftpc70gviknc26635` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`),
  CONSTRAINT `FKigm5jb0xdqnqjelaagm14dcva` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `application_manager__fk` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`),
  CONSTRAINT `application_student__fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (10,'Refused',1,'2018-09-26','2018-08-01','Hi, I want to apply for financial support because I am poor.',12345678,'666',NULL,0,0,0),(11,'Accepted',1,'2018-06-05','2018-09-04','Hi ...................',12345678,'not null',NULL,0,0,0),(12,'Accepted',2,'2018-05-07','2018-08-10','jdpaoidjapsoidfjaodsijf',12345678,'uts online',NULL,0,0,0),(13,'Refused',1,'2018-09-26','2018-10-01','',12345678,'668','Please provide more information.',0,0,0),(14,'Submitted',NULL,'2018-09-26',NULL,'Yes I am',12345678,'886',NULL,0,0,0),(34,'Submitted',1,'2018-10-02',NULL,'adsafsds',12345678,'Test Draft',NULL,0,0,0),(35,'Submitted',5,'2018-10-02',NULL,'Hello, I am Jiangjianshun',12345678,'Test Draft',NULL,0,0,0),(36,'Submitted',4,'2018-10-02',NULL,'asdfa',12345678,'Test Draft',NULL,0,0,0),(37,'Submitted',6,'2018-10-14',NULL,'Nothing.',12345678,'Title',NULL,6,101,113.12);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `attachment` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `draft_id` int(10) DEFAULT NULL,
  `application_id` int(10) DEFAULT NULL,
  `upload_date` date DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6v9kv390t8e3ljkbs3gjfy3pe` (`application_id`),
  KEY `FK7tvrig72fsw5pcpde3vm4x1yx` (`draft_id`),
  CONSTRAINT `FK6v9kv390t8e3ljkbs3gjfy3pe` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`),
  CONSTRAINT `FK7tvrig72fsw5pcpde3vm4x1yx` FOREIGN KEY (`draft_id`) REFERENCES `draft` (`id`),
  CONSTRAINT `attachment_application__fk` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`) ON DELETE CASCADE,
  CONSTRAINT `attachment_draft__fk` FOREIGN KEY (`draft_id`) REFERENCES `draft` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `draft`
--

DROP TABLE IF EXISTS `draft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `draft` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(5000) DEFAULT NULL,
  `last_edit` date DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `pay_back_years` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `sum` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `draft`
--

LOCK TABLES `draft` WRITE;
/*!40000 ALTER TABLE `draft` DISABLE KEYS */;
/*!40000 ALTER TABLE `draft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `manager` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(10) DEFAULT NULL,
  `lastname` varchar(10) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `admin_id` int(10) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FKk32qe1qwpdvedrn489nfhppkx` (`admin_id`),
  CONSTRAINT `FKk32qe1qwpdvedrn489nfhppkx` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`),
  CONSTRAINT `manager_admin__fk` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'Manager','Test','12416881@student.uts.edu.au','123445567','111',1,0),(2,'Olo','New','leighton070103@gmail.com','1234555','111',1,0),(3,'Manager','Jiang','shunj110@gmail.com','3456789','111',1,0),(4,'Manager','Qi','qiwenbo1996@gmail.com','098765456','111',1,0),(5,'Manager','Zhan','jenny.zhan.yh@gmail.com','923884939','111',1,0),(6,'Manager','Lu','0424390218lu@gmail.com','23412341','111',1,0),(7,'Manager','Eric','stradlin0518@gmail.com','2341324','111',1,0);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `id` int(10) NOT NULL,
  `password` varchar(50) NOT NULL,
  `bankaccount` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `faculty` varchar(4) DEFAULT NULL,
  `firstname` varchar(10) DEFAULT NULL,
  `lastname` varchar(10) DEFAULT NULL,
  `course` varchar(6) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `nationality` varchar(20) DEFAULT NULL,
  `start_year` varchar(4) DEFAULT NULL,
  `draft_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_draft__fk` (`draft_id`),
  CONSTRAINT `FK2el0f631jhyc46ocyyjsk4h53` FOREIGN KEY (`draft_id`) REFERENCES `draft` (`id`),
  CONSTRAINT `student_draft__fk` FOREIGN KEY (`draft_id`) REFERENCES `draft` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (12201846,'12201846','china number one','12201846@student.uts.edu.au','42679513','FEIT','Jianshun','Jiang','C3838','1983-10-13','Female','America','2011',NULL),(12345678,'12345678','00000000','leighton070103@gmail.com','1111111','FEIT','Ayman','Jiang','C8990','1989-08-18','Male','mongo','2015',NULL),(12665396,'12665396','Badminton Love','12665396@student.uts.edu.au','88887777','LOL','Wenbo','Qi','U666','1995-10-06','Female','England','2007',NULL),(12701759,'12701759','Niubility','12701759@student.uts.edu.au','999000','FEIT','Shan','Lu','a888','1987-10-02','Male','Brazil','2016',NULL),(12840211,'12840211','Yunhan Zhan','12840211@student.uts.edu.au','68237461','SOSO','Yunhan','Zhan','D999','1997-10-16','Female','China','2013',NULL),(87654321,'87654321','Tree new bee','shunj110@gmail.com','202929292','FASS','Spring','Test','D909','2015-08-07','Female','Australia','2011',NULL),(99196305,'99196305','I love Germany','99196305@student.uts.edu.au','88888888','FEIT','Xiaoyang','Wang','I000','1990-10-05','Male','Japan','2009',NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-14 17:29:30
