-- MySQL dump 10.13  Distrib 8.1.0, for Win64 (x86_64)
--
-- Host: 103.221.221.44    Database: rryvhcjj_transporter
-- ------------------------------------------------------
-- Server version	5.5.5-10.6.15-MariaDB-cll-lve-log

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'2023-10-12 21:36:49.000000','2023-11-08 13:04:37.000000',5,'admin@gmail.com','admin','$2a$10$719RQvsW4r9siKFnJ3S2r.Tyi7qZHHK6h.52uyRMUdUL6GGhiLwia','1231231231','ADMIN',_binary '',NULL),(2,'2023-10-12 21:36:49.000000','2023-11-09 11:42:53.000000',2,'shipper@gmail.com','shipper','$2a$10$3u0mHnzmc7vhNe18Im1hreKMrNNjAYb7aHIffDWqzeC6PcpjxtMUm','1231231230','SHIPPER',_binary '',NULL),(3,'2023-10-12 21:36:49.000000','2023-10-12 21:36:49.000000',0,'shipper2@gmail.com','shipper 2','$2a$10$3u0mHnzmc7vhNe18Im1hreKMrNNjAYb7aHIffDWqzeC6PcpjxtMUm','1231231233','SHIPPER',_binary '',NULL),(4,'2023-11-01 23:15:43.000000','2023-11-10 15:02:15.000000',6,'user@gmail.com','user','$2a$10$uIyyiWvqmXS76H4Aap9KC.a5n4CXKgpY7iSXam5dz1wXnt00nhV9i','1231231276','USER',_binary '','HCM'),(5,'2023-11-01 23:20:02.000000','2023-11-09 11:44:05.000000',2,'shipper3@gmail.com','shipper 1212','$2a$10$JqdA/lrszFToTHmpoOjQceVEqWdMHItvR1QoR0j75xQ8SCyf4q/S2','0111113344','USER',_binary '',NULL),(6,'2023-11-03 23:06:35.000000','2023-11-13 22:21:46.000000',3,'user1@gmail.com','user1','$2a$10$wRHaw7m5KCA7UIurSwzrYOLc/rGzzbjbzmSVc6qvQ0zLYxWTt3bue','0123123132','USER',_binary '\0',NULL),(7,'2023-11-13 22:11:37.000000','2023-11-13 22:14:09.000000',1,'user2@gmail.com','user2','$2a$10$kbYuPfRLEvIg/EGxFzmtduTySIu202UieaRXQf8Q6cnOZz4SAtCAu','0975151698','USER',_binary '',NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4pljlvncf45mr98etwpubxvbt` (`account_id`),
  CONSTRAINT `FK4pljlvncf45mr98etwpubxvbt` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (2,'2023-10-27 11:13:27.000000','2023-10-27 11:13:27.000000',0,_binary '',5),(3,'2023-11-02 00:02:33.000000','2023-11-02 00:02:33.000000',0,_binary '',1),(4,'2023-11-02 00:11:07.000000','2023-11-02 00:11:07.000000',0,_binary '',4),(5,'2023-11-04 00:38:40.000000','2023-11-04 00:38:40.000000',0,_binary '',2),(6,'2023-11-04 13:06:14.000000','2023-11-04 13:06:14.000000',0,_binary '',6);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `cart_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (1,'2023-10-27 11:13:35.000000','2023-10-28 00:39:28.000000',2,6,2,100002,_binary '\0'),(3,'2023-10-27 12:37:55.000000','2023-10-28 12:30:06.000000',2,6,2,100008,_binary '\0'),(4,'2023-10-28 12:33:05.000000','2023-10-28 13:04:39.000000',2,2,2,100001,_binary '\0'),(5,'2023-10-28 13:02:06.000000','2023-10-28 13:04:39.000000',1,4,2,100002,_binary '\0'),(6,'2023-10-28 13:03:48.000000','2023-10-28 13:04:39.000000',1,2,2,100007,_binary '\0'),(7,'2023-10-28 13:05:01.000000','2023-10-28 13:05:01.000000',0,1,2,100008,_binary ''),(8,'2023-11-02 00:02:33.000000','2023-11-10 15:08:30.000000',2,2,3,100003,_binary '\0'),(9,'2023-11-02 00:11:07.000000','2023-11-02 01:30:25.000000',1,1,4,100004,_binary '\0'),(10,'2023-11-02 01:10:06.000000','2023-11-02 01:30:25.000000',1,1,4,100008,_binary '\0'),(11,'2023-11-02 01:35:35.000000','2023-11-02 01:35:45.000000',1,1,4,100008,_binary '\0'),(12,'2023-11-02 01:38:36.000000','2023-11-02 01:38:45.000000',1,1,4,100004,_binary '\0'),(13,'2023-11-04 00:38:45.000000','2023-11-04 00:38:45.000000',0,1,5,100002,_binary ''),(14,'2023-11-04 00:45:27.000000','2023-11-04 00:45:27.000000',0,1,5,100003,_binary ''),(15,'2023-11-04 13:06:14.000000','2023-11-04 16:45:16.000000',2,2,6,100002,_binary '\0'),(16,'2023-11-04 13:18:37.000000','2023-11-04 16:45:16.000000',2,3,6,100001,_binary '\0'),(17,'2023-11-04 17:03:31.000000','2023-11-04 17:03:48.000000',1,1,6,100008,_binary '\0'),(18,'2023-11-04 17:04:00.000000','2023-11-04 17:04:00.000000',0,1,6,100003,_binary ''),(19,'2023-11-04 19:02:25.000000','2023-11-04 19:02:25.000000',0,1,6,100002,_binary ''),(20,'2023-11-04 19:03:17.000000','2023-11-04 19:03:17.000000',0,1,6,100004,_binary ''),(21,'2023-11-04 19:03:51.000000','2023-11-04 19:15:15.000000',1,1,6,100007,_binary '\0'),(22,'2023-11-04 19:15:32.000000','2023-11-04 19:15:32.000000',0,1,6,100007,_binary ''),(23,'2023-11-05 22:07:28.000000','2023-11-05 22:10:44.000000',2,3,3,100002,_binary ''),(24,'2023-11-05 22:20:20.000000','2023-11-05 22:21:16.000000',2,2,4,100001,_binary '\0'),(25,'2023-11-05 22:26:57.000000','2023-11-05 22:28:25.000000',1,1,4,100002,_binary '\0'),(26,'2023-11-07 21:27:11.000000','2023-11-07 21:27:30.000000',1,3,4,100001,_binary '\0'),(27,'2023-11-07 21:27:38.000000','2023-11-07 21:50:00.000000',1,2,4,100001,_binary '\0'),(28,'2023-11-07 21:27:44.000000','2023-11-07 21:50:00.000000',1,1,4,100008,_binary '\0'),(29,'2023-11-07 21:28:58.000000','2023-11-07 21:50:00.000000',1,2,4,100002,_binary '\0'),(30,'2023-11-07 21:50:17.000000','2023-11-07 21:50:18.000000',1,1,4,100008,_binary '\0'),(31,'2023-11-07 21:53:56.000000','2023-11-07 22:18:57.000000',7,0,4,100008,_binary '\0'),(32,'2023-11-07 22:07:09.000000','2023-11-10 11:20:19.000000',5,3,4,100001,_binary '\0'),(34,'2023-11-10 11:20:07.000000','2023-11-10 11:20:22.000000',1,1,4,100008,_binary '\0'),(35,'2023-11-10 11:20:39.000000','2023-11-10 11:21:01.000000',1,1,4,100006,_binary '\0'),(36,'2023-11-10 15:12:45.000000','2023-11-10 15:13:34.000000',1,4,4,1000010,_binary '\0'),(37,'2023-11-10 15:13:47.000000','2023-11-10 15:14:29.000000',1,1,4,100008,_binary '\0'),(38,'2023-11-10 15:16:29.000000','2023-11-13 22:10:22.000000',4,2,4,100008,_binary '\0'),(39,'2023-11-10 15:18:06.000000','2023-11-10 15:18:16.000000',1,4,4,1000010,_binary '\0'),(40,'2023-11-13 22:08:25.000000','2023-11-13 22:08:45.000000',1,0,4,100002,_binary '\0'),(41,'2023-11-13 22:08:59.000000','2023-11-13 22:09:25.000000',2,2,4,100001,_binary '\0');
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'2023-10-22 15:09:32.000000','2023-11-07 21:24:29.000000',2,'Thời Trang',_binary '','Thời Trang'),(2,'2023-10-22 15:09:32.000000','2023-11-09 11:40:34.000000',2,'Đồng Hồ',_binary '','Đồng Hồ'),(3,'2023-10-22 15:09:32.000000','2023-10-22 15:09:35.000000',0,'Máy Tính',_binary '','Máy Tính'),(4,'2023-10-22 15:09:32.000000','2023-10-22 15:09:35.000000',0,'Sách Báo',_binary '','Sách Báo'),(5,'2023-10-22 15:09:32.000000','2023-10-22 15:09:35.000000',0,'Điện Thoại',_binary '','Điện Thoại'),(33,'2023-11-07 15:27:29.000000','2023-11-07 15:47:11.000000',1,'Balo',_binary '','BaLo'),(34,'2023-11-07 16:14:04.000000','2023-11-07 16:14:04.000000',0,'Đồng Phục',_binary '','Đồng Phục'),(35,'2023-11-08 09:56:49.000000','2023-11-13 22:22:10.000000',3,'agthsd',_binary '\0','test');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `discount` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,'2023-10-28 00:39:28.000000','2023-10-28 00:39:28.000000',0,20,350000,6,1,100002),(2,'2023-10-28 00:39:28.000000','2023-10-28 00:39:28.000000',0,50,290000,2,1,100008),(3,'2023-10-28 13:04:39.000000','2023-10-28 13:04:39.000000',0,10,500000,2,2,100001),(4,'2023-10-28 13:04:39.000000','2023-10-28 13:04:39.000000',0,20,350000,4,2,100002),(5,'2023-10-28 13:04:39.000000','2023-10-28 13:04:39.000000',0,30,190000,2,2,100007),(6,'2023-11-02 01:30:25.000000','2023-11-02 01:30:25.000000',0,10,390000,1,4,100004),(7,'2023-11-02 01:30:25.000000','2023-11-02 01:30:25.000000',0,50,290000,1,4,100008),(8,'2023-11-02 01:35:45.000000','2023-11-02 01:35:45.000000',0,50,290000,1,5,100008),(9,'2023-11-02 01:38:45.000000','2023-11-02 01:38:45.000000',0,10,390000,1,6,100004),(10,'2023-11-04 16:45:16.000000','2023-11-04 16:45:16.000000',0,20,350000,2,7,100002),(11,'2023-11-04 16:45:16.000000','2023-11-04 16:45:16.000000',0,10,500000,3,7,100001),(12,'2023-11-04 17:03:48.000000','2023-11-04 17:03:48.000000',0,50,290000,1,8,100008),(13,'2023-11-05 22:21:16.000000','2023-11-05 22:21:16.000000',0,10,500000,2,9,100001),(14,'2023-11-07 21:50:00.000000','2023-11-07 21:50:00.000000',0,10,500000,2,10,100001),(15,'2023-11-07 21:50:00.000000','2023-11-07 21:50:00.000000',0,50,290000,1,10,100008),(16,'2023-11-07 21:50:00.000000','2023-11-07 21:50:00.000000',0,20,350000,2,10,100002),(17,'2023-11-10 11:21:01.000000','2023-11-10 11:21:01.000000',0,10,290000,1,11,100006),(18,'2023-11-10 15:13:34.000000','2023-11-10 15:13:34.000000',0,0,10000,4,12,1000010),(19,'2023-11-10 15:14:29.000000','2023-11-10 15:14:29.000000',0,50,290000,1,13,100008),(20,'2023-11-13 22:10:21.000000','2023-11-13 22:10:21.000000',0,50,290000,2,14,100008);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `progress` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3c7gbsfawn58r27cf5b2km72f` (`account_id`),
  CONSTRAINT `FK3c7gbsfawn58r27cf5b2km72f` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2023-10-28 00:39:28.000000','2023-11-08 10:07:02.000000',8,'905 Kha Vạn Cân, Phường Linh Tây, TP. Thủ Đức','admin','','2023-10-28 00:00:00.000000','1231231231','SHIPPING',_binary '',1970000,1),(2,'2023-10-28 13:04:39.000000','2023-10-28 18:24:46.000000',1,'dasasdasd asdqwe123','admin','','2023-10-28 00:00:00.000000','1231231231','APPROVED',_binary '',2286000,1),(4,'2023-11-02 01:30:25.000000','2023-11-10 14:50:36.000000',2,'34 Ngô Nhân Tịnh, Phú Hiệp, Thành phố Huế, Thừa Thiên Huế','user','giao toi huế','2023-11-02 00:00:00.000000','0123456777','CANCELED',_binary '',496000,4),(5,'2023-11-02 01:35:45.000000','2023-11-02 01:35:45.000000',0,'Số 2A Nguyễn Oanh, Phường 7, Quận Gò Vấp, Thành phố Hồ Chí Minh','user','sdvs','2023-11-02 00:00:00.000000','0123456777','APPROVED',_binary '',145000,4),(6,'2023-11-02 01:38:45.000000','2023-11-16 20:10:29.000000',2,'123123','user','','2023-11-02 00:00:00.000000','0123456777','PENDING',_binary '',351000,4),(7,'2023-11-04 16:45:16.000000','2023-11-16 20:05:40.000000',1,'asd asd asd aa','user1','','2023-11-07 00:00:00.000000','0123123132','PENDING',_binary '',1910000,6),(8,'2023-11-04 17:03:48.000000','2023-11-04 17:03:48.000000',0,'asd 123 1 111','user1','','2023-11-04 00:00:00.000000','0123123132','PENDING',_binary '',145000,6),(9,'2023-11-05 22:21:16.000000','2023-11-06 23:16:27.000000',2,'486 Đường 30/4, Phường Rạch Dừa, Thành phố Vũng Tàu, Tỉnh Bà Rịa - Vũng Tàu','user','mua so luong lon','2023-11-05 00:00:00.000000','0123456777','COMPLETED',_binary '',900000,4),(10,'2023-11-07 21:50:00.000000','2023-11-07 21:50:00.000000',0,'Hà Nội','user','Không có ghi chú','2023-11-07 00:00:00.000000','1231231276','PENDING',_binary '',1605000,4),(11,'2023-11-10 11:21:01.000000','2023-11-10 11:21:01.000000',0,'Hà Nội','user','','2023-11-10 00:00:00.000000','1231231276','SHIPPING',_binary '',261000,4),(12,'2023-11-10 15:13:34.000000','2023-11-10 15:13:34.000000',0,'HCM','user','','2023-11-10 00:00:00.000000','1231231276','PENDING',_binary '',40000,4),(13,'2023-11-10 15:14:29.000000','2023-11-10 15:14:29.000000',0,'HCM','user','','2023-11-10 00:00:00.000000','1231231276','PENDING',_binary '',145000,4),(14,'2023-11-13 22:10:21.000000','2023-11-13 22:47:14.000000',3,'130 Minh Mạng, Thủy Xuân, Thành phố Huế, Thừa Thiên Huế, Vietnam','user','vận chuyển ha nội','2023-11-10 00:00:00.000000','1231231276','COMPLETED',_binary '',290000,4);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `description` longtext DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `detail` mediumtext DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000012 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (100001,'2023-10-22 15:29:58.000000','2023-11-13 22:23:41.000000',2,'Hướng dẫn sử dụng sản phẩm:\r\n- Giặt ở nhiệt độ bình thường, với đồ có màu tương tự.\r\n- Không được dùng hóa chất tẩy.\r\n- Hạn chế sử dụng máy sấy và ủi ở nhiệt độ thích hợp.',10,'https://down-vn.img.susercontent.com/file/vn-11134211-23030-9wg99pd50dov82',500000,_binary '','Áo Hoodie Nỉ Nam Thu Đông Họa Tiết Tay Đấm Zenkonam MEN TOP 185',1,'áo hiệu'),(100002,'2023-10-22 15:29:58.000000','2023-10-22 15:30:02.000000',0,'Hướng dẫn sử dụng sản phẩm:\n- Giặt ở nhiệt độ bình thường, với đồ có màu tương tự.\n- Không được dùng hóa chất tẩy.\n- Hạn chế sử dụng máy sấy và ủi ở nhiệt độ thích hợp.',20,'https://down-vn.img.susercontent.com/file/vn-11134211-23030-b8n2bz5mbeovd1',350000,_binary '','Áo Hoodie Nỉ Nam Thu Đông Họa Tiết Chữ W MEN TOP 187 B (Đen)',1,NULL),(100003,'2023-10-22 15:29:58.000000','2023-10-22 15:30:02.000000',0,'Hướng dẫn sử dụng sản phẩm:\n- Giặt ở nhiệt độ bình thường, với đồ có màu tương tự.\n- Không được dùng hóa chất tẩy.\n- Hạn chế sử dụng máy sấy và ủi ở nhiệt độ thích hợp.',10,'https://down-vn.img.susercontent.com/file/sg-11134201-7rblf-llx6emh4aupxc8',890000,_binary '','Đồng Hồ Điện Tử Màn Hình led Cảm Ứng Linh Hoạt Chống Thấm Nước Hỗ Trợ Đo Nhịp Tim Kết Nối bluetooth',2,NULL),(100004,'2023-10-22 15:29:58.000000','2023-10-22 15:30:02.000000',0,'Hướng dẫn sử dụng sản phẩm:\n- Giặt ở nhiệt độ bình thường, với đồ có màu tương tự.\n- Không được dùng hóa chất tẩy.\n- Hạn chế sử dụng máy sấy và ủi ở nhiệt độ thích hợp.',10,'https://down-vn.img.susercontent.com/file/05ac1ca3f70ea46866d4923843034f20',390000,_binary '','Đồng hồ điện tử thể thao mặt trong suốt cho nam và nữ',2,NULL),(100005,'2023-10-22 15:29:58.000000','2023-10-22 15:30:02.000000',0,'Hướng dẫn sử dụng sản phẩm:\n- Giặt ở nhiệt độ bình thường, với đồ có màu tương tự.\n- Không được dùng hóa chất tẩy.\n- Hạn chế sử dụng máy sấy và ủi ở nhiệt độ thích hợp.',10,'https://down-vn.img.susercontent.com/file/cn-11134207-7qukw-lj53jrsfcx2u2f',490000,_binary '','Đồng hồ thông minh đồng hồ nam Full Touch Sport Watch Đồng hồ thông minh không thấm nước cho Android iOS',2,NULL),(100006,'2023-10-22 15:29:58.000000','2023-10-22 15:30:02.000000',0,'Hướng dẫn sử dụng sản phẩm:\n- Giặt ở nhiệt độ bình thường, với đồ có màu tương tự.\n- Không được dùng hóa chất tẩy.\n- Hạn chế sử dụng máy sấy và ủi ở nhiệt độ thích hợp.',10,'https://down-vn.img.susercontent.com/file/495df53bdac205479836210a385108e1',290000,_binary '','Bộ Đồng Hồ Thông Minh ZHIHUI M6 6 Theo Dõi Sức Khỏe',2,NULL),(100007,'2023-10-22 15:29:58.000000','2023-10-22 15:30:02.000000',0,'Hướng dẫn sử dụng sản phẩm:\n- Giặt ở nhiệt độ bình thường, với đồ có màu tương tự.\n- Không được dùng hóa chất tẩy.\n- Hạn chế sử dụng máy sấy và ủi ở nhiệt độ thích hợp.',30,'https://down-vn.img.susercontent.com/file/a247ae622310edb2208e018d9b3580bc',190000,_binary '','Combo 5 cuốn Vở One Piece - B5 Kẻ Ngang Có Chấm 200 Trang -BOPP200 (Mẫu Màu Giao Ngẫu Nhiên)',4,NULL),(100008,'2023-10-22 15:29:58.000000','2023-10-22 15:30:02.000000',0,'Hướng dẫn sử dụng sản phẩm:\n- Giặt ở nhiệt độ bình thường, với đồ có màu tương tự.\n- Không được dùng hóa chất tẩy.\n- Hạn chế sử dụng máy sấy và ủi ở nhiệt độ thích hợp.',50,'https://down-vn.img.susercontent.com/file/vn-11134201-7qukw-lk1wehhb7x1efc',290000,_binary '','Áo Hoodie nỉ In Chữ Phong Cách Thời Trang Hàn nam nữ mặc đẹp chât dày vải nỉ bông',1,NULL),(100009,'2023-11-07 17:06:15.000000','2023-11-07 17:30:03.000000',4,'HƯỚNG DẪN VỆ SINH:\r\n\r\n- Không giặt tẩy bằng các chất tẩy rửa mạnh\r\n\r\n- Không nên giặt chung với các sản phẩm khác\r\n\r\n- Không nên sấy khô trong máy giặt sẽ làm ảnh hướng đến các phần cầu kì như móc, khoá cứng\r\n\r\n- Dùng chất tẩy rửa nhẹ như xà bông, sửa tắm chà đánh bọt lên xong chà nhẹ lên vải. Sau đó giặt nhẹ lại bằng nước sạch\r\n\r\n- Phơi mát tự nhiên tránh ánh nắng mặt trời làm ảnh hướng đến chất liệu và bạc màu vải\r\n\r\n TẠI SAO LẠI CHỌN BRAND BAMOZO\r\n\r\n- Đối với BAMOZO, làm sao để trải nghiệm của khách hàng được tốt nhất, trọn vẹn nhất luôn là ưu tiên hàng đầu mà chúng mình hướng tới\r\n\r\n- Khi mua hàng của BAMOZO, nếu sản phẩm chưa đáp ứng được yêu cầu bạn cần, đừng lo vì chúng mình luôn sẵn sàng Đồi/Trả hàng trong vòng 7 ngày\r\n\r\n- Bên cạnh đó, Chúng mình có dịch vụ Bảo hành miễn phí trọn đời tất cả nguyên phụ liệu, đường chỉ trên sản phẩm nên các bạn chỉ việc yên tâm, thoải mái sử dụng thôi\r\n\r\n- Và cuối cùng, tất cả các sản phẩm của BAMOZO đều có giao hàng Hoả tốc, nên nếu bạn cần gấp - đừng lo, chúng mình sẽ giao ngay trong vòng 1-2h ',45,'/resources/file//bce46529-ddd7-408f-84ba-a4e5ff2d8f4d.jpg',450000,_binary '','Balo Đi Học Nam Nữ Bamozo Basic Backpack, Balo Thời Trang Nam Nữ - Trượt Nước Tốt, Bảo Hành Trọn Đời',33,'BAMOZO được biết đến là hiện tại là đơn vị chuyên sản xuất gia công các mặt hàng alo đi học, đi chơi, laptop nam nữ… theo yêu cầu khách hàng trong & ngoài nước , phương châm “Uy tín nằm ở chất lượng”, BAMOZO luôn nỗ lực từng ngày để phát triển kỹ thuật, công nghệ sản xuất hiện đại để từng sản phẩm khi đến tay khách hàng chỉnh chu nhất.'),(1000010,'2023-11-08 09:58:41.000000','2023-11-08 09:59:19.000000',2,'ef qwq ',0,'/resources/file//303b39c3-30be-4ef8-a154-b00c8bc18b4a.jpg',10000,_binary '','test1',35,'eegwe'),(1000011,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping`
--

DROP TABLE IF EXISTS `shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `address_from` varchar(255) DEFAULT NULL,
  `address_to` varchar(255) DEFAULT NULL,
  `progress` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `shipper_id` bigint(20) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2umyblvwmvm2ju0be634j89x4` (`order_id`),
  KEY `FKcifrwrqmobknyfxbaxo40o054` (`shipper_id`),
  CONSTRAINT `FK2umyblvwmvm2ju0be634j89x4` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKcifrwrqmobknyfxbaxo40o054` FOREIGN KEY (`shipper_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping`
--

LOCK TABLES `shipping` WRITE;
/*!40000 ALTER TABLE `shipping` DISABLE KEYS */;
INSERT INTO `shipping` VALUES (1,'2023-10-12 21:36:49.000000','2023-11-13 16:07:32.000000',14,' quận 9','905 Kha Vạn Cân, Phường Linh Tây, TP. Thủ Đức','CANCEL',1,2,'KH từ chối nhận hàng'),(2,'2023-11-04 18:32:31.000000','2023-11-18 01:21:49.000000',1,'162 - 164 Thái Hà, Phường Trung Liệt, Đống Đa, Hà Nội.','1081 - 1083 Trần Hưng Đạo, Phường 5, Quận 5','PENDING',4,3,''),(3,'2023-11-05 22:22:57.000000','2023-11-18 01:42:22.000000',3,'162 - 164 Thái Hà, Phường Trung Liệt, Đống Đa, Hà Nội.','59 Nguyễn Tất Thành, Phường, Thị xã Hương Thủy, Thừa Thiên Huế','COMPLETED',9,2,''),(4,'2023-11-13 22:27:36.000000','2023-11-13 22:44:20.000000',2,'162 - 164 Thái Hà, Phường Trung Liệt, Đống Đa, Hà Nội.','130 Minh Mạng, Thủy Xuân, Thành phố Huế, Thừa Thiên Huế, Vietnam','COMPLETED',14,2,'tphcm'),(5,'2023-11-18 01:41:18.000000','2023-11-19 22:02:49.000000',1,'59 Nguyễn Tất Thành, Phường, Thị xã Hương Thủy, Thừa Thiên Huế','132E Cách Mạng Tháng 8, Phường 10, Quận 3, TP. Hồ Chí Minh','COMPLETED',9,3,''),(6,'2023-11-19 22:03:19.000000','2023-11-20 20:47:44.000000',2,'132E Cách Mạng Tháng 8, Phường 10, Quận 3, TP. Hồ Chí Minh','1081 - 1083 Trần Hưng Đạo, Phường 5, Quận 5','COMPLETED',9,2,''),(7,'2023-11-20 20:48:14.000000','2023-11-20 20:48:14.000000',0,'1081 - 1083 Trần Hưng Đạo, Phường 5, Quận 5','486 Đường 30/4, Phường Rạch Dừa, Thành phố Vũng Tàu, Tỉnh Bà Rịa - Vũng Tàu','PENDING',9,3,'');
/*!40000 ALTER TABLE `shipping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `location_lat` varchar(255) DEFAULT NULL,
  `location_lng` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (1,'2023-10-28 00:00:00.000000','2023-11-07 18:50:08.000000',2,'162 - 164 Thái Hà, Phường Trung Liệt, Đống Đa, Hà Nội.',_binary '','Đống Đa, Hà Nội','21.012622029000056','105.82061192700007'),(2,'2023-10-28 00:00:00.000000','2023-10-28 00:00:00.000000',0,'02 Hoàng Hoa Thám, Phường 12, Quận Tân Bình, Thành phố Hồ Chí Minh',_binary '','Tân Bình, TP. Hồ Chí Minh','10.79657832600003','106.64709085700008'),(3,'2023-11-08 10:07:41.000000','2023-11-08 10:07:54.000000',1,'132E Cách Mạng Tháng 8, Phường 10, Quận 3, TP. Hồ Chí Minh',_binary '','Quận 3, TP. Hồ Chí Minh','10.778465106000056','106.68058979800009'),(4,'2023-11-08 10:08:07.000000','2023-11-08 10:08:07.000000',0,'1081 - 1083 Trần Hưng Đạo, Phường 5, Quận 5',_binary '','Quận 5, TP. Hồ Chí Minh','10.7531979','106.674176'),(5,'2023-11-08 10:08:07.000000','2023-11-08 10:08:07.000000',0,'59 Nguyễn Tất Thành, Phường, Thị xã Hương Thủy, Thừa Thiên Huế',_binary '','Hương Thủy, Thừa Thiên Huế','16.4256238','107.6553132'),(6,'2023-11-08 10:08:07.000000','2023-11-08 10:08:07.000000',0,'380 Lê Văn Hiến, Khuê Mỹ, Ngũ Hành Sơn, Đà Nẵng',_binary '','Ngũ Hành Sơn, Đà Nẵng','16.025157227000022','108.24942365600003');
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'rryvhcjj_transporter'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-26 22:10:32
