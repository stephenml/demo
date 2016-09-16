/*
 Source Server Type    : MySQL
 Source Database       : demo

 File Encoding         : utf-8

 Date: 09/10/2016 22:15:01 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `framework`
-- ----------------------------
DROP TABLE IF EXISTS `framework`;
CREATE TABLE `framework` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '框架id',
  `name` varchar(50) NOT NULL COMMENT '框架名称',
  `href` varchar(200) NOT NULL COMMENT '框架官网',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `tab` varchar(50) NOT NULL COMMENT '菜单hash',
  `title` varchar(100) NOT NULL COMMENT '菜单标题',
  `level` int(11) NOT NULL COMMENT '菜单级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `messages`
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `uuid` varchar(50) NOT NULL COMMENT '用户唯一标示',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `content` text NOT NULL COMMENT '消息内容',
  `commit_date` datetime NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;