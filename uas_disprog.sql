CREATE DATABASE  IF NOT EXISTS `uas_disprog` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci */;
USE `uas_disprog`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: uas_disprog
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id_account` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  PRIMARY KEY (`id_account`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (7,'rDz8WtCTRufgzsy9CGZjC9SzujXPT2m70LI2jj/O6Ts=','9LiUf3sQtNgPsK2kvO7MaQ==','L'),(8,'czEUN4V6Ej1jE+IhdRm/8mvMeNRe/CbqdRNQVilrhpM=','wWalMedwVxWhHnT5KWqPww==','L'),(9,'rql5sPhLL+SauPKAhkyqOg==','cxy2Fz/+Vb9T0ZilLxwIsQ==','L'),(10,'rQXP4JjYlXn5YboO0jQ1/Q==','oyfB0p2KmKSAhNB7S1klAg==','L');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cek_blok`
--

DROP TABLE IF EXISTS `cek_blok`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cek_blok` (
  `account_id_user1` int(11) NOT NULL,
  `account_id_user2` int(11) NOT NULL,
  `status` enum('Aktif','Block') NOT NULL,
  PRIMARY KEY (`account_id_user1`,`account_id_user2`),
  KEY `fk_account_account_account2_idx` (`account_id_user2`),
  KEY `fk_account_account_account1_idx` (`account_id_user1`),
  CONSTRAINT `fk_account_account_account1` FOREIGN KEY (`account_id_user1`) REFERENCES `account` (`id_account`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_account_account2` FOREIGN KEY (`account_id_user2`) REFERENCES `account` (`id_account`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cek_blok`
--

LOCK TABLES `cek_blok` WRITE;
/*!40000 ALTER TABLE `cek_blok` DISABLE KEYS */;
INSERT INTO `cek_blok` VALUES (7,9,'Block'),(9,10,'Block');
/*!40000 ALTER TABLE `cek_blok` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_bmi`
--

DROP TABLE IF EXISTS `history_bmi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history_bmi` (
  `id_bmi` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `berat_badan` double NOT NULL,
  `tinggi_badan` double NOT NULL,
  `hasil_bmi` double NOT NULL,
  `account_id` int(11) NOT NULL,
  PRIMARY KEY (`id_bmi`),
  KEY `fk_history_bmi_account_idx` (`account_id`),
  CONSTRAINT `fk_history_bmi_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id_account`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_bmi`
--

LOCK TABLES `history_bmi` WRITE;
/*!40000 ALTER TABLE `history_bmi` DISABLE KEYS */;
/*!40000 ALTER TABLE `history_bmi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_hitung_ideal`
--

DROP TABLE IF EXISTS `history_hitung_ideal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history_hitung_ideal` (
  `id_hitung_ideal` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `tinggi_badan` double NOT NULL,
  `berat_ideal` double NOT NULL,
  `account_id` int(11) NOT NULL,
  PRIMARY KEY (`id_hitung_ideal`),
  KEY `fk_history_hitung_ideal_account1_idx` (`account_id`),
  CONSTRAINT `fk_history_hitung_ideal_account1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id_account`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_hitung_ideal`
--

LOCK TABLES `history_hitung_ideal` WRITE;
/*!40000 ALTER TABLE `history_hitung_ideal` DISABLE KEYS */;
/*!40000 ALTER TABLE `history_hitung_ideal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-20 18:06:27
