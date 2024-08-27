-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: consumer_db
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','123'),(2,'admin1','565');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `song_id` int unsigned DEFAULT NULL,
  `song_list_id` int unsigned DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `type` tinyint NOT NULL,
  `up` int unsigned NOT NULL DEFAULT '0',
  `song_list_consumer_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (3,1,0,1,'里面乱乱糟糟\n我们别再闹了\n这个冬天已然很冷了\n我们靠在一起。好吗','2019-01-06 16:12:13',1,8,NULL),(5,1,21,NULL,'允儿牵动我的心!!!','2019-01-06 18:12:53',0,0,NULL),(9,1,22,NULL,'林允儿这个人，饭她真的很骄傲。韩国人说汉语总会带着地域性极强的泡菜味，可是林允儿真的很用心在把准每一个汉字，从咬字到发音，再加上轻柔干净的嗓音加持，将柔美与舒缓表达到极致，将歌里想诉说的那种感情娓娓道来。','2019-01-06 19:36:01',0,0,NULL),(10,1,21,NULL,'像我们之间一段长久未诉的告白，被你这样娓娓道来，你问我爱你有多深，我爱你有几分，我的情不移我的爱不变，月亮代表我的心。','2019-01-06 19:44:37',0,4,NULL),(11,1,21,NULL,'当听这首歌曲的时候，看看天上的月亮。美爆了！','2019-01-06 19:45:51',0,2,NULL),(12,1,23,NULL,'太尼马好听了！堂堂正正的林歌手！！','2019-01-06 19:48:25',0,0,NULL),(13,1,23,NULL,'林允儿啊，真的唱的很标准，很动人，我的同学都没想到是林允儿唱的，呜呜呜，爱死你了林允儿','2019-01-06 19:54:01',0,0,NULL),(14,1,22,NULL,'真的好棒，我只听她这个版本的','2019-01-06 19:55:43',0,0,NULL),(16,1,5,NULL,'好听啊','2019-01-06 19:56:52',0,0,NULL),(17,1,22,NULL,'我的允宝啊，努力演戏想让我们看到一样的你，努力学中文唱给我们听越来越爱你了','2019-01-06 19:58:53',0,0,NULL),(18,1,22,NULL,'好听啊','2019-01-06 20:01:46',0,0,NULL),(19,1,23,NULL,'好听啊','2019-01-06 20:03:59',0,0,NULL),(20,1,21,NULL,'好听啊','2019-01-06 20:04:22',0,0,NULL),(23,1,NULL,5,'赞！！','2019-01-08 01:05:27',1,2,NULL),(24,5,NULL,1,'超喜欢！','2019-01-08 21:46:29',1,3,NULL),(25,5,NULL,5,'大爱我林！','2019-01-08 21:47:45',1,1,NULL),(26,5,NULL,2,'nice','2019-01-08 22:11:23',1,1,NULL),(27,1,NULL,0,'很有感觉','2019-01-08 22:32:51',1,2,NULL),(28,5,26,NULL,'好听','2019-01-08 22:42:07',0,0,NULL),(29,5,21,NULL,'nice!','2019-01-08 22:57:08',0,0,NULL),(30,5,15,NULL,'好听！','2019-01-08 23:03:43',0,0,NULL),(31,1,13,NULL,'rrrr','2019-01-15 16:28:03',0,0,NULL),(32,1,19,NULL,'赞','2019-03-07 16:34:12',0,0,NULL),(33,1,6,NULL,'赞','2019-03-12 09:06:21',0,0,NULL),(34,1,NULL,1,'hao','2019-03-16 21:07:01',1,3,NULL),(35,1,NULL,38,'hao','2019-03-24 01:39:06',1,0,NULL),(36,1,NULL,0,'妙！','2019-03-24 01:48:56',1,1,NULL),(37,1,NULL,80,'好听','2019-03-24 01:51:02',1,0,NULL),(38,1,NULL,80,'好听！！！','2019-03-24 01:52:20',1,0,NULL),(39,1,NULL,80,'好听','2019-03-24 01:53:06',1,0,NULL),(40,1,NULL,80,'good','2019-03-24 01:53:45',1,0,NULL),(41,1,NULL,80,'nice','2019-03-24 01:55:04',1,0,NULL),(42,1,NULL,80,'nice','2019-03-24 01:57:02',1,0,NULL),(43,1,NULL,82,'success','2019-03-24 01:57:40',1,0,NULL),(45,1,NULL,1,'啦啦啦(*≧∀≦)ﾉ','2019-04-25 21:24:43',1,0,NULL),(47,1,NULL,1,'222','2019-04-26 01:01:27',1,0,NULL),(48,5,NULL,10,'我喜欢你','2019-04-26 01:03:12',1,0,NULL),(49,1,NULL,0,'','2019-05-23 21:35:47',1,0,NULL),(50,1,NULL,51,'好听','2019-05-23 21:38:04',1,0,NULL),(51,1,NULL,5,'好听','2019-05-23 21:39:55',1,0,NULL),(52,1,NULL,5,'好听','2019-05-23 21:40:25',1,0,NULL),(53,1,107,NULL,'I love you！！！','2019-06-03 02:16:23',0,0,NULL),(54,1,95,NULL,'好听','2020-03-14 16:14:53',0,0,NULL),(55,1,28,NULL,'?','2020-03-14 16:19:11',0,0,NULL),(56,26,69,NULL,'good!','2020-03-22 02:19:03',0,0,NULL),(57,26,10,NULL,'good','2020-03-22 03:40:10',0,5,NULL),(58,1,NULL,3,'1111111','2022-02-28 01:14:56',1,0,NULL),(59,1,28,NULL,'11111','2022-03-05 16:54:31',0,0,NULL),(60,1,NULL,15,'111','2022-04-17 13:28:08',1,0,NULL),(61,1,NULL,15,'222','2022-04-17 13:28:17',1,0,NULL),(62,1,NULL,15,'33','2022-04-17 13:30:19',1,0,NULL),(63,1,NULL,15,'里面乱乱糟糟 我们别再闹了 这个冬天已然很冷了 我们靠在一起。好吗.里面乱乱糟糟 我们别再闹了 这个冬天已然很冷了 我们靠在一起。好吗.里面乱乱糟糟 我们别再闹了 这个冬天已然很冷了 我们靠在一起。好吗.里面乱乱糟糟 我们别再闹了 这个冬天已然很冷了 我们靠在一起。好吗','2022-04-17 22:57:06',1,0,NULL),(64,1,NULL,1,'456','2022-04-21 21:41:43',1,0,NULL),(68,59,NULL,1,'345','2022-04-22 00:57:07',1,0,NULL),(69,61,NULL,1,'yin','2022-10-26 22:01:49',1,0,NULL),(70,61,9,NULL,'123','2023-01-16 00:36:21',0,0,NULL),(71,61,9,NULL,'1111','2023-01-16 00:36:49',0,0,NULL),(72,61,NULL,1,'11111','2023-02-27 22:03:01',1,0,NULL),(73,63,NULL,NULL,'有眼光！','2024-05-30 13:27:21',2,0,23),(74,63,36,NULL,'一条精彩的评论','2024-06-05 01:42:22',0,0,NULL),(75,63,NULL,NULL,'好好好','2024-06-05 04:27:09',2,0,3),(76,63,11,NULL,'你好','2024-06-07 10:54:32',0,0,NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumer`
--

DROP TABLE IF EXISTS `consumer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumer` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(100) NOT NULL,
  `sex` tinyint DEFAULT NULL,
  `phone_num` char(15) DEFAULT NULL,
  `email` char(30) DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `avator` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `phone_num_UNIQUE` (`phone_num`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumer`
--

LOCK TABLES `consumer` WRITE;
/*!40000 ALTER TABLE `consumer` DISABLE KEYS */;
INSERT INTO `consumer` VALUES (1,'Yin33','123',0,'13776412237','yoona@qq.com','2019-05-21 00:00:00','好好吃饭,我哈哈哈哈哈哈哈哈','山西','/img/avatorImages/1556201886030L1.jpg','2019-01-04 21:42:24','2022-04-18 01:19:12'),(2,'012','012',0,'13754803255','love@gmail.com','2019-04-24 00:00:00','我就喜欢吃','北京','/img/avatorImages/1649527868607author.jpg','2019-01-05 15:02:45','2020-03-23 01:24:59'),(5,'789','789',0,'13634377258','666@126.com','2019-01-08 00:00:00','今天很开心啊','山西','/img/avatorImages/1646506637695IMG_4801.jpg','2019-01-07 16:16:42','2022-04-10 01:59:13'),(8,'tawuhen','123',0,'','192673541@qq.com','2019-04-25 18:58:39','你好','北京','/img/avatorImages/user.jpg','2019-04-25 00:28:58','2019-04-25 18:58:39'),(12,'yoona','123',0,'13854173655','1236795@qq.com','2019-04-25 00:00:00','好好吃饭','北京','/img/avatorImages/1588094989449L1.jpg','2019-04-25 10:56:54','2020-04-29 01:28:37'),(16,'1234321','123',1,'13754803257','123@qq.com','2020-03-08 17:25:45','','','/img/avatorImages/user.jpg','2020-03-08 17:25:45','2020-03-08 17:25:45'),(24,'yoonaAA','123',1,NULL,NULL,'2020-03-04 00:00:00','','','/img/avatorImages/user.jpg','2020-03-21 22:20:27','2020-03-21 22:20:27'),(25,'yoonaAB','123',1,NULL,NULL,'2020-03-02 00:00:00','','','/img/avatorImages/user.jpg','2020-03-21 22:21:50','2020-03-21 22:21:50'),(26,'yoonaAC','123',1,'null','null','2020-03-11 00:00:00','','','/img/avatorImages/user.jpg','2020-03-21 22:23:43','2020-05-14 21:12:56'),(27,'yoonaAD','123',1,NULL,NULL,'2020-03-11 00:00:00','','','/img/avatorImages/user.jpg','2020-03-21 22:24:47','2020-03-21 22:24:47'),(28,'yoona90','123',0,NULL,NULL,'2020-04-28 00:00:00','','','/img/avatorImages/user.jpg','2020-04-02 22:10:34','2020-04-02 22:10:34'),(29,'test','123',0,'15666412237','1239679@qq.com','2020-04-16 11:29:43','','','/img/avatorImages/user.jpg','2020-04-16 11:29:43','2020-04-16 11:29:43'),(30,'Yoona001','123',0,NULL,NULL,'2020-07-27 00:00:00','','','/img/avatorImages/user.jpg','2020-07-01 19:54:44','2020-07-01 19:54:44'),(31,'yuner','123',0,NULL,NULL,'2015-02-10 00:00:00','','','/img/avatorImages/user.jpg','2021-07-17 18:18:40','2021-07-17 18:18:40'),(33,'qqq','111',1,NULL,NULL,'2022-03-14 00:00:00','','','/img/avatorImages/user.jpg','2022-03-14 10:29:23','2022-03-14 10:29:23'),(34,'qwe','123',0,NULL,NULL,'2022-04-05 00:00:00','','','/img/avatorImages/user.jpg','2022-04-05 22:56:00','2022-04-05 22:56:00'),(35,'1234','',1,NULL,NULL,'2022-04-12 00:00:00','','','/img/avatorImages/user.jpg','2022-04-12 23:05:52','2022-04-12 23:05:52'),(57,'1234d','123',1,NULL,NULL,'2022-04-14 00:00:00','','','/img/avatorImages/user.jpg','2022-04-14 00:57:21','2022-04-14 00:57:21'),(59,'zxc','123',1,NULL,NULL,'2022-04-21 00:00:00','','','/img/avatorImages/user.jpg','2022-04-21 22:54:19','2022-04-21 22:54:19'),(61,'Yin','24c14f112462b440c3de9523b05c4c59',0,NULL,NULL,'2019-12-01 00:00:00','早起早睡','山西','img/avatorImages/user.jpg','2022-09-23 22:41:52','2022-09-23 22:41:52'),(62,'Yin1','123',1,NULL,NULL,'2023-04-18 00:00:00','','','/img/avatorImages/user.jpg','2023-04-18 21:44:44','2023-04-18 21:44:44'),(63,'TSY','24c14f112462b440c3de9523b05c4c59',1,NULL,NULL,'2024-05-26 04:45:39','buaa best singer!','','/img/avatorImages/1556201886030L1.jpg','2024-05-26 04:46:04','2024-06-02 05:38:55'),(64,'111','24c14f112462b440c3de9523b05c4c59',0,NULL,NULL,'2024-05-30 10:06:10','','','/img/avatorImages/qingtian.jpg','2024-05-30 10:06:22','2024-05-30 11:14:33'),(65,'dadiao','24c14f112462b440c3de9523b05c4c59',0,NULL,NULL,'2024-06-01 00:00:00','','','img/avatorImages/user.jpg','2024-06-01 17:06:12','2024-06-01 17:06:12');
/*!40000 ALTER TABLE `consumer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `play_history`
--

DROP TABLE IF EXISTS `play_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `play_history` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `song_id` int unsigned NOT NULL,
  `play_count` int unsigned NOT NULL,
  `play_time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_play_history_user` (`user_id`),
  KEY `fk_play_history_song` (`song_id`),
  CONSTRAINT `fk_play_history_user` FOREIGN KEY (`user_id`) REFERENCES `consumer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_history`
--

LOCK TABLES `play_history` WRITE;
/*!40000 ALTER TABLE `play_history` DISABLE KEYS */;
INSERT INTO `play_history` VALUES (1,63,15,3,'2024-06-07 05:40:49'),(2,63,13,21,'2024-06-07 07:42:17'),(3,63,101,4,'2024-06-07 07:45:09'),(4,63,21,3,'2024-06-07 07:54:54'),(5,63,22,1,'2024-06-07 08:01:31'),(6,63,23,5,'2024-06-07 08:01:12'),(7,63,107,0,'2024-06-07 16:01:20'),(8,63,95,0,'2024-06-07 16:01:40'),(9,63,97,0,'2024-06-07 16:01:52'),(10,63,105,0,'2024-06-07 16:01:59'),(11,63,106,1,'2024-06-07 08:03:37'),(12,63,98,3,'2024-06-07 08:16:19'),(13,63,109,0,'2024-06-07 16:16:34'),(14,63,120,2,'2024-06-07 23:11:35'),(15,63,36,5,'2024-06-08 00:22:43'),(16,63,44,0,'2024-06-08 08:08:17');
/*!40000 ALTER TABLE `play_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weekly_report`
--

DROP TABLE IF EXISTS `weekly_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weekly_report` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `play_count` int NOT NULL,
  `play_total_time` int NOT NULL,
  `week_start_date` date NOT NULL,
  `week_end_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_weekly_report_user` (`user_id`),
  CONSTRAINT `fk_weekly_report_user` FOREIGN KEY (`user_id`) REFERENCES `consumer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weekly_report`
--

LOCK TABLES `weekly_report` WRITE;
/*!40000 ALTER TABLE `weekly_report` DISABLE KEYS */;
INSERT INTO `weekly_report` VALUES (1,63,100,100,'2024-04-20','2024-04-27');
/*!40000 ALTER TABLE `weekly_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'consumer_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-24 22:39:55
