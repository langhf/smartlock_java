-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: smartlock
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `code_sms`
--

DROP TABLE IF EXISTS `code_sms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_sms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `action_sign` varchar(20) NOT NULL COMMENT '操作签名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `code` varchar(10) NOT NULL COMMENT '验证码',
  `valid_second` int(11) NOT NULL COMMENT '有效时间，单位：秒',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_sms`
--

LOCK TABLES `code_sms` WRITE;
/*!40000 ALTER TABLE `code_sms` DISABLE KEYS */;
/*!40000 ALTER TABLE `code_sms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `door_help_order`
--

DROP TABLE IF EXISTS `door_help_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `door_help_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `helper` varchar(255) DEFAULT NULL COMMENT '帮助者姓名',
  `reason` varchar(255) DEFAULT NULL COMMENT '帮助理由',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `help_token` varchar(255) DEFAULT NULL COMMENT '帮助口令',
  `auditor_id` bigint(20) DEFAULT NULL COMMENT '审核员',
  `status` int(1) DEFAULT NULL COMMENT '0：未审核  1：审核通过  2：审核拒绝',
  `getui_cid` varchar(255) DEFAULT NULL COMMENT '个推CID',
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `door_help_order`
--

LOCK TABLES `door_help_order` WRITE;
/*!40000 ALTER TABLE `door_help_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `door_help_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `door_info`
--

DROP TABLE IF EXISTS `door_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `door_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(15) DEFAULT NULL COMMENT '门编号',
  `BLT_MAC` varchar(100) DEFAULT NULL COMMENT '蓝牙MAC地址',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `pre_pwd` varchar(255) DEFAULT NULL COMMENT '上次密码',
  `next_pwd` varchar(255) DEFAULT NULL COMMENT '下次密码',
  `description` varchar(255) DEFAULT NULL COMMENT '门的描述',
  `tag` varchar(100) DEFAULT NULL COMMENT 'lab 实验室； meeting 会议室; hall 前工院，东南院等',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `door_info`
--

LOCK TABLES `door_info` WRITE;
/*!40000 ALTER TABLE `door_info` DISABLE KEYS */;
INSERT INTO `door_info` VALUES (1,'Q-gate',NULL,1,'100000','424917','前工院大门','hall',NULL,NULL,NULL,NULL),(2,'Q-401-0','78:04:73:CF:D4:EA',1,'100000','424917','前工院401西门','lab',NULL,NULL,NULL,NULL),(3,'Q-401-1','78:04:73:CE:F5:E4',1,'100000','424917','前工院401东门','lab',NULL,NULL,NULL,NULL),(4,'Q-402','78:04:73:CF:76:08',1,'100000','424917','前工院402','lab',NULL,NULL,NULL,NULL),(5,'Q-404-0','38:81:D7:1C:55:83',1,'100000','424917','前工院404西门','lab',NULL,NULL,NULL,NULL),(6,'Q-404-1','38:81:D7:1C:55:83',1,'100000','424917','前工院404东门','lab',NULL,NULL,NULL,NULL),(7,'Q-405-0',NULL,1,'100000','424917','前工院405西门','lab',NULL,NULL,NULL,NULL),(8,'Q-405-1','78:04:73:CE:F5:DD',1,'100000','424917','前工院405东门','lab',NULL,NULL,NULL,NULL),(9,'Q-406-0','38:81:D7:1C:55:EF',1,'100000','424917','前工院406西门','lab',NULL,NULL,NULL,NULL),(10,'Q-406-1','38:81:D7:1C:5A:27',1,'100000','424917','前工院406东门','lab',NULL,NULL,NULL,NULL),(11,'Q-407-0','38:81:D7:1C:5A:24',1,'100000','424917','前工院407西门','lab',NULL,NULL,NULL,NULL),(12,'Q-407-1','38:81:D7:1C:5F:EC',1,'100000','424917','前工院407东门','lab',NULL,NULL,NULL,NULL),(13,'Q-408-0',NULL,1,'100000','424917','前工院408西门','lab',NULL,NULL,NULL,NULL),(14,'Q-408-1','78:04:73:CF:AC:0E',1,'100000','424917','前工院408东门','lab',NULL,NULL,NULL,NULL),(15,'QM01',NULL,1,'100000','424917','会议室1','meeting',NULL,NULL,NULL,NULL),(16,'QM02',NULL,1,'100000','424917','会议室2','meeting',NULL,NULL,NULL,NULL),(17,'QM03',NULL,1,'100000','424917','会议室3','meeting',NULL,NULL,NULL,NULL),(18,'QF41','78:04:73:CF:AC:0E',1,'100000','424917','前工院西门','floor',NULL,NULL,NULL,NULL),(19,'QF42','78:04:73:CF:D4:A3',1,'100000','424917','前工院中门','floor',NULL,NULL,NULL,NULL),(20,'QF43','78:04:73:CF:B0:B5',1,'100000','424917','前工院东门','floor',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `door_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `door_open_log`
--

DROP TABLE IF EXISTS `door_open_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `door_open_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户名',
  `door_id` bigint(20) DEFAULT NULL COMMENT '门编号',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `created_by` varchar(255) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `door_open_log`
--

LOCK TABLES `door_open_log` WRITE;
/*!40000 ALTER TABLE `door_open_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `door_open_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (40);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'xiong lang yu','$2a$10$/msPD4DRu1d69f5LdoCS9.xk.gMjpgy7HMNrXM/F5BKCBGr1LPbMu',1,'2019-01-19 14:59:31','13156533607','2019-03-19 10:10:21');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_login_log`
--

DROP TABLE IF EXISTS `member_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_login_log`
--

LOCK TABLES `member_login_log` WRITE;
/*!40000 ALTER TABLE `member_login_log` DISABLE KEYS */;
INSERT INTO `member_login_log` VALUES (1,1,NULL,'0:0:0:0:0:0:0:1',NULL,'2019-01-19 15:00:20'),(2,1,NULL,'0:0:0:0:0:0:0:1',NULL,'2019-03-19 10:10:21');
/*!40000 ALTER TABLE `member_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_info`
--

DROP TABLE IF EXISTS `room_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(15) NOT NULL COMMENT '房间编号',
  `description` varchar(255) DEFAULT NULL COMMENT '房间描述',
  `owner` bigint(20) DEFAULT NULL COMMENT '房间的管理者的user_id',
  `tag` varchar(255) NOT NULL COMMENT '类别 lab, meeting, dining, activity',
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_info`
--

LOCK TABLES `room_info` WRITE;
/*!40000 ALTER TABLE `room_info` DISABLE KEYS */;
INSERT INTO `room_info` VALUES (1,'Q-401','前工院401',12,'lab',NULL,'2019-01-12 00:00:00',NULL,NULL),(2,'Q-402','前工院402',12,'lab',NULL,'2019-01-12 00:00:00',NULL,NULL),(3,'Q-403','前工院403',12,'lab',NULL,'2019-01-12 00:00:00',NULL,NULL),(4,'Q-404','前工院404',12,'lab',NULL,'2019-01-12 00:00:00',NULL,NULL),(5,'Q-405','前工院405',12,'lab',NULL,'2019-01-12 00:00:00',NULL,NULL),(6,'Q-406','前工院406',12,'lab',NULL,'2019-01-12 00:00:00',NULL,NULL),(7,'Q-407','前工院407',12,'lab',NULL,'2019-01-12 00:00:00',NULL,NULL),(8,'Q-408','前工院408',12,'lab',NULL,'2019-01-12 00:00:00',NULL,NULL),(9,'QM1','会议室1',12,'meeting',NULL,'2019-01-12 00:00:00',NULL,NULL),(10,'QM2','会议室2',12,'meeting',NULL,'2019-01-12 00:00:00',NULL,NULL),(11,'QM3','会议室3',12,'meeting',NULL,'2019-01-12 00:00:00',NULL,NULL),(12,'QF4','前工院4楼',12,'floor',NULL,'2019-01-12 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `room_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_order`
--

DROP TABLE IF EXISTS `room_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_id` bigint(20) DEFAULT NULL COMMENT '房间编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '预订的用户 id',
  `theme` varchar(255) DEFAULT NULL COMMENT '预订原因',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0: 待审核 1: 审核通过 2：审核未过 3:已过期',
  `auditor_id` bigint(20) DEFAULT NULL COMMENT '审核员',
  `start_time` datetime NOT NULL COMMENT '预定开始时间',
  `end_time` datetime NOT NULL COMMENT '预定结束时间 ',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `created_by` varchar(255) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_order`
--

LOCK TABLES `room_order` WRITE;
/*!40000 ALTER TABLE `room_order` DISABLE KEYS */;
INSERT INTO `room_order` VALUES (1,12,1,'开启4楼大门',3,1,'1970-01-19 05:54:53','1970-01-19 08:41:33','2019-01-19 15:17:52','2019-01-19 15:20:24','xiong lang yu','xiong lang yu'),(2,12,1,'开启4楼大门',0,1,'2019-01-12 14:00:00','2019-02-12 14:00:00','2019-01-19 15:19:13','2019-01-19 15:19:13','xiong lang yu','xiong lang yu');
/*!40000 ALTER TABLE `room_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tencent_cos`
--

DROP TABLE IF EXISTS `tencent_cos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tencent_cos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class` varchar(20) DEFAULT NULL COMMENT 'url 类型',
  `url` varchar(255) DEFAULT NULL COMMENT '文件url',
  `created_at` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tencent_cos`
--

LOCK TABLES `tencent_cos` WRITE;
/*!40000 ALTER TABLE `tencent_cos` DISABLE KEYS */;
INSERT INTO `tencent_cos` VALUES (42,'help','https://smartlock-1251883428.cos.ap-shanghai.myqcloud.com/Helper-0.1.0.0.apk',NULL),(43,'user','https://smartlock-1251883428.cos.ap-shanghai.myqcloud.com/Ulocked-0.1.0.0.apk',NULL),(44,'help','https://smartlock-1251883428.cos.ap-shanghai.myqcloud.com/Android_APK/Helper-0.1.0.0.apk',NULL),(45,'user','https://smartlock-1251883428.cos.ap-shanghai.myqcloud.com/Android_APK/Ulocked-0.1.0.0.apk',NULL),(46,'user','https://smartlock-1251883428.cos.ap-shanghai.myqcloud.com/Android_APK/Normal-0.1.1.apk',NULL),(47,'user','https://smartlock-1251883428.cos.ap-shanghai.myqcloud.com/Android_APK/Elock_normal-0.2.0.apk',NULL),(48,'user','https://smartlock-1251883428.cos.ap-shanghai.myqcloud.com/Android_APK/Elock_normal-v0.2.1.apk',NULL);
/*!40000 ALTER TABLE `tencent_cos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'smartlock'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-21 16:57:19
