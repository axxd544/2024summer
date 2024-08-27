-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: social_db
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
-- Table structure for table `appeals`
--
-- social-service.sql
CREATE DATABASE IF NOT EXISTS `social-service`;
USE `social-service`;

-- SQL statements for creating tables, inserting data, etc.

DROP TABLE IF EXISTS `appeals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appeals` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `complaint_id` int unsigned NOT NULL,
  `user_id` int unsigned NOT NULL,
  `reason` text NOT NULL,
  `status` enum('PENDING','REVIEWED','DISMISSED') NOT NULL DEFAULT 'PENDING',
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_appeals_user` (`user_id`),
  KEY `fk_appeals_complaint` (`complaint_id`),
  CONSTRAINT `fk_appeals_complaint` FOREIGN KEY (`complaint_id`) REFERENCES `complaints` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appeals`
--

LOCK TABLES `appeals` WRITE;
/*!40000 ALTER TABLE `appeals` DISABLE KEYS */;
INSERT INTO `appeals` VALUES (1,12,63,'一点都不难听','DISMISSED','2024-06-08 07:48:59','2024-06-08 13:57:15');
/*!40000 ALTER TABLE `appeals` ENABLE KEYS */;
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
-- Table structure for table `complaints`
--

DROP TABLE IF EXISTS `complaints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaints` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `target_type` enum('SONG','PLAYLIST') NOT NULL,
  `target_id` int unsigned NOT NULL,
  `reason` text NOT NULL,
  `status` enum('PENDING','REVIEWED','DISMISSED') NOT NULL DEFAULT 'PENDING',
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_complaints_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
INSERT INTO `complaints` VALUES (9,63,'PLAYLIST',3,'难听的要死','REVIEWED','2024-06-08 06:09:36','2024-06-08 12:57:51'),(10,63,'SONG',120,'难听','DISMISSED','2024-06-08 07:07:20','2024-06-08 13:45:06'),(11,65,'PLAYLIST',32,'疑似嘲讽雷军','PENDING','2024-06-08 07:20:23','2024-06-08 07:20:23'),(12,65,'PLAYLIST',23,'沙比','REVIEWED','2024-06-08 07:34:01','2024-06-08 13:57:15'),(13,63,'SONG',36,'难听','PENDING','2024-06-08 07:52:26','2024-06-08 07:52:26');
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `follower_id` int unsigned NOT NULL,
  `followed_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_follow_follower` (`follower_id`),
  KEY `fk_follow_followed` (`followed_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (19,1,63),(24,63,1),(25,63,61),(27,65,63);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned DEFAULT NULL,
  `user_type` varchar(45) NOT NULL,
  `message` varchar(255) NOT NULL,
  `type` int unsigned NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_read` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_notifications_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,1,'1','hhh',1,'2024-06-08 02:17:50',1),(2,63,'consumer','sdfafasd',1,'2024-06-08 02:26:45',1),(3,63,'consumer','nihao',1,'2024-06-08 02:39:11',1),(6,63,'consumer','您关注的用户 \"TSY\" 添加了歌单 \"1\"，快来看看吧！',1,'2024-06-08 03:00:52',1),(7,1,'consumer','您关注的用户 \"TSY\" 添加了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 03:01:10',0),(8,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 03:01:10',0),(9,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 03:01:10',0),(10,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 03:01:10',0),(11,1,'consumer','您关注的用户 \"TSY\" 添加了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(12,65,'consumer','您关注的用户 \"TSY\" 添加了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(13,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(14,65,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(15,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(16,65,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(17,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(18,65,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(19,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(20,65,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(21,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(22,65,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"粤语live||赛赢录音棚流行live\"，快来看看吧！',1,'2024-06-08 03:09:28',0),(23,1,'consumer','您关注的用户 \"TSY\" 添加了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:31:36',0),(24,65,'consumer','您关注的用户 \"TSY\" 添加了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:31:36',0),(25,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:31:36',0),(26,65,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:31:36',0),(27,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:31:36',0),(28,65,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:31:36',0),(29,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:31:36',0),(30,65,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:31:36',0),(31,1,'consumer','您关注的用户 \"TSY\" 添加了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:52:59',0),(32,65,'consumer','您关注的用户 \"TSY\" 添加了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:52:59',0),(33,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:52:59',0),(34,65,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:52:59',0),(35,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:52:59',0),(36,65,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:52:59',0),(37,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:52:59',0),(38,65,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 04:52:59',0),(39,1,'consumer','您关注的用户 \"TSY\" 删除了歌单 \"粤语live||赛赢录音棚流行live\"。',1,'2024-06-08 05:23:31',0),(41,1,'consumer','您关注的用户 \"TSY\" 删除了歌单 \"sakura\"。',1,'2024-06-08 05:23:34',0),(43,1,'consumer','您关注的用户 \"TSY\" 删除了歌单 \"sakura\"。',1,'2024-06-08 05:23:36',0),(45,1,'consumer','您关注的用户 \"TSY\" 添加了歌单 \"sakura\"，快来看看吧！',1,'2024-06-08 05:23:41',0),(53,1,'consumer','您关注的用户 \"TSY\" 添加了歌单 \"test\"，快来看看吧！',1,'2024-06-08 06:01:03',0),(59,1,'consumer','您对歌单 \"1\"的投诉已提交',2,'2024-06-08 06:08:23',0),(62,63,'consumer','您对歌单 \"test\"的投诉已提交',2,'2024-06-08 06:09:36',1),(65,63,'consumer','您对歌曲 \"TSY-玫瑰少年\"的投诉已提交',2,'2024-06-08 07:07:20',1),(68,1,'consumer','您关注的用户 \"TSY\" 添加了歌单 \"年轻之歌 有关爱与挑衅\"，快来看看吧！',1,'2024-06-08 07:10:19',0),(78,1,'consumer','您关注的用户 \"TSY\" 更新了歌单 \"test\"，快来看看吧！',1,'2024-06-08 07:10:27',0),(82,65,'consumer','您对歌单 \"hello，thankyou\"的投诉已提交',2,'2024-06-08 07:20:23',0),(83,NULL,'manager','用户 \"65-dadiao\" 投诉了歌单 \"hello，thankyou\"。',2,'2024-06-08 07:20:23',0),(84,63,'consumer','您的歌单 \"hello，thankyou\" 被投诉！',2,'2024-06-08 07:20:23',1),(85,65,'consumer','您对歌单 \"1\"的投诉已提交',2,'2024-06-08 07:34:01',0),(86,NULL,'manager','投诉ID:12用户 \"65-dadiao\" 投诉了歌单 \"1\"。',2,'2024-06-08 07:34:01',0),(87,63,'consumer','投诉ID:12您的歌单 \"1\" 被投诉！',2,'2024-06-08 07:34:01',0),(88,NULL,'manager','用户 \"63-TSY\" 对投诉信息12提出申诉。',3,'2024-06-08 07:48:59',0),(89,63,'consumer','您的申诉信息已提交。',3,'2024-06-08 07:48:59',0),(90,63,'consumer','您对歌曲 \"Ennio Morricone-Once Upon a Time in the West\"的投诉已提交',2,'2024-06-08 07:52:26',1),(91,NULL,'manager','投诉ID:13用户 \"63-TSY\" 投诉了歌曲 \"Ennio Morricone-Once Upon a Time in the West\"。',2,'2024-06-08 07:52:26',0),(96,63,'consumer','您的投诉被驳回，原因：好听',2,'2024-06-08 13:45:06',0),(97,1,'consumer','您关注的用户 \"null\" 添加了歌单 \"八戒\"，快来看看吧！',1,'2024-08-24 13:19:13',0),(98,65,'consumer','您关注的用户 \"null\" 添加了歌单 \"八戒\"，快来看看吧！',1,'2024-08-24 13:19:13',0);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'social_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-24 22:56:13
