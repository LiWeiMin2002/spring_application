/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : testbank

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 04/08/2023 18:30:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts`  (
  `accountId` int NOT NULL,
  `balance` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`accountId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES (31, 666.00);
INSERT INTO `accounts` VALUES (32, 888.00);

-- ----------------------------
-- Table structure for oprecord
-- ----------------------------
DROP TABLE IF EXISTS `oprecord`;
CREATE TABLE `oprecord`  (
  `id` int NOT NULL,
  `accountId` int NULL DEFAULT NULL,
  `opmoney` decimal(10, 2) NULL DEFAULT NULL,
  `optime` datetime NULL DEFAULT NULL,
  `optype` enum('deposite','withdraw','transfer') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `transferId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `PK_AccountId_oprecord`(`accountId` ASC) USING BTREE,
  CONSTRAINT `PK_AccountId_oprecord` FOREIGN KEY (`accountId`) REFERENCES `accounts` (`accountId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oprecord
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
