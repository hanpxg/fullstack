/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : fullstack

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-06-05 12:01:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `board_of_directors` varchar(255) DEFAULT NULL,
  `brief_writeup` varchar(255) DEFAULT NULL,
  `ceo` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `sector_name` varchar(255) DEFAULT NULL,
  `turnover` float DEFAULT NULL,
  `sector_id` bigint DEFAULT NULL,
  `icon_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmumulhrkylrdxsl33736gudi6` (`sector_id`),
  CONSTRAINT `FKmumulhrkylrdxsl33736gudi6` FOREIGN KEY (`sector_id`) REFERENCES `sector` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for company_exchange
-- ----------------------------
DROP TABLE IF EXISTS `company_exchange`;
CREATE TABLE `company_exchange` (
  `exchange_id` bigint NOT NULL,
  `company_id` bigint NOT NULL,
  KEY `FKgjdu9lo327p4ifl5kyqyb3wdw` (`company_id`),
  KEY `FK2hakg3fmbitwina50le90spvo` (`exchange_id`),
  CONSTRAINT `FK2hakg3fmbitwina50le90spvo` FOREIGN KEY (`exchange_id`) REFERENCES `stock_exchange` (`id`),
  CONSTRAINT `FKgjdu9lo327p4ifl5kyqyb3wdw` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for iop_detail
-- ----------------------------
DROP TABLE IF EXISTS `iop_detail`;
CREATE TABLE `iop_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `exchange_name` varchar(255) DEFAULT NULL,
  `open_time` datetime DEFAULT NULL,
  `price_per_share` float DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `total_of_share` bigint DEFAULT NULL,
  `company_id` bigint NOT NULL,
  `exchange_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKechrs7hct6dbhs75vnshjt9pr` (`company_id`),
  KEY `FKd6gw1nrr40lkp9b96lvfvnoki` (`exchange_id`),
  CONSTRAINT `FKd6gw1nrr40lkp9b96lvfvnoki` FOREIGN KEY (`exchange_id`) REFERENCES `stock_exchange` (`id`),
  CONSTRAINT `FKechrs7hct6dbhs75vnshjt9pr` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sector
-- ----------------------------
DROP TABLE IF EXISTS `sector`;
CREATE TABLE `sector` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stock_exchange
-- ----------------------------
DROP TABLE IF EXISTS `stock_exchange`;
CREATE TABLE `stock_exchange` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `brief` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stock_price
-- ----------------------------
DROP TABLE IF EXISTS `stock_price`;
CREATE TABLE `stock_price` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `exchange_name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `company_id` bigint NOT NULL,
  `exchange_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjavcfro8axnb5qkgkyxfoobsm` (`company_id`),
  KEY `FKt9gdlb2cylesey611hifm0hpp` (`exchange_id`),
  CONSTRAINT `FKjavcfro8axnb5qkgkyxfoobsm` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKt9gdlb2cylesey611hifm0hpp` FOREIGN KEY (`exchange_id`) REFERENCES `stock_exchange` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `confirmed` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `update_ts` datetime DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
