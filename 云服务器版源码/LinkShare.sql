/*
 Navicat Premium Data Transfer

 Source Server         : Aliyun_mysql
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 39.108.55.167:3306
 Source Schema         : LinkShare

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 07/06/2020 13:39:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for json_string
-- ----------------------------
DROP TABLE IF EXISTS `json_string`;
CREATE TABLE `json_string`  (
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yun_data
-- ----------------------------
DROP TABLE IF EXISTS `yun_data`;
CREATE TABLE `yun_data`  (
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `shareid` bigint(20) NULL DEFAULT NULL,
  `dataid` bigint(20) NULL DEFAULT NULL,
  `sharename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `uk` bigint(20) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `updatetime` datetime(0) NULL DEFAULT NULL,
  `sharetime` datetime(0) NULL DEFAULT NULL,
  `avatarurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `version` int(11) NULL DEFAULT NULL,
  `mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `size` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `Resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `subnodes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `updatedat` datetime(0) NULL DEFAULT NULL,
  `createdat` datetime(0) NULL DEFAULT NULL,
  `referrers` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `invalidcount` int(11) NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `accesscode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_yun_data_uuid`(`uuid`) USING BTREE,
  UNIQUE INDEX `index_yun_data_shareid`(`shareid`) USING BTREE,
  INDEX `index_yun_data_uk`(`uk`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yun_data
-- ----------------------------
INSERT INTO `yun_data` VALUES ('BDY-wqQhsc18Fsqovvgct50AzA', 1, NULL, NULL, 'B873', NULL, NULL, '2020-04-17 20:26:00', '2019-11-07 10:22:00', 'https://pan.baidu.com/s/1wqQhsc18Fsqovvgct50AzA', NULL, 'DIR', 'B873', '0', 'null', '马*装', '{\"mode\":\"DIR\",\"share_time\":1573093320000,\"name\":\"\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"马*装\"}', '[{\"name\":\"B873\",\"mode\":\"DIR\",\"size\":\"0\"}]', 'wqQhsc18Fsqovvgct50AzA', '2020-04-17 20:26:00', '2020-04-17 08:45:00', '[{\"url\":\"undefined\",\"title\":\"undefined\"}]', 0, 'VALID', 'dff8', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-uqUgonrPD7EiWBFJ4X2Qfg', 2, NULL, NULL, '2019最新 Java商城秒杀系统的设计与实战视频教程（SpringBoot版）', NULL, NULL, '2020-05-26 14:26:00', '2019-09-01 21:01:00', 'https://pan.baidu.com/s/1uqUgonrPD7EiWBFJ4X2Qfg', NULL, 'DIR', '2019最新 Java商城秒杀系统的设计与实战视频教程（SpringBoot版）', '0', 'null', '在水****iqi', '{\"mode\":\"DIR\",\"share_time\":1567342860000,\"name\":\"2019最新 Java商城秒杀系统的设计与实战视频教程（SpringBoot版）\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"在水****iqi\"}', '[{\"name\":\"2019最新 Java商城秒杀系统的设计与实战视频教程（SpringBoot版）\",\"mode\":\"DIR\",\"size\":\"0\"}]', 'uqUgonrPD7EiWBFJ4X2Qfg', '2020-05-26 14:26:00', '2020-04-17 10:52:00', '[{\"url\":\"undefined\",\"title\":\"undefined\"}]', 0, 'VALID', 'r48w', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-5VaRSPOoL6v8r_RAyqnhOA', 3, NULL, NULL, NULL, NULL, NULL, '2020-04-18 01:12:00', NULL, 'https://pan.baidu.com/s/15VaRSPOoL6v8r_RAyqnhOA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5VaRSPOoL6v8r_RAyqnhOA', '2020-04-18 01:12:00', '2020-04-18 01:12:00', NULL, 0, 'VALID', NULL, 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-TYQxJTwMJfYOjYi47A93gQ', 4, NULL, NULL, '黑马 JavaEE 57期 推荐 IDEA版（B战的SSM在这）', NULL, NULL, '2020-06-06 17:29:00', '2019-12-23 10:05:00', 'https://pan.baidu.com/s/1TYQxJTwMJfYOjYi47A93gQ', NULL, 'DIR', '黑马 JavaEE 57期 推荐 IDEA版（B战的SSM在这）', '0', 'null', '相逢**陌路', '{\"mode\":\"DIR\",\"share_time\":1577066700000,\"name\":\"黑马 JavaEE 57期 推荐 IDEA版（B战的SSM在这）\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"相逢**陌路\"}', '[{\"name\":\"黑马 JavaEE 57期 推荐 IDEA版（B战的SSM在这）\",\"mode\":\"DIR\",\"size\":\"0\"}]', 'TYQxJTwMJfYOjYi47A93gQ', '2020-06-06 17:29:00', '2020-04-18 04:02:00', '[{\"url\":\"undefined\",\"title\":\"undefined\"}]', 0, 'VALID', 'ruwg', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-x0681Z3ihl22-64iWqG6eQ', 5, NULL, NULL, NULL, NULL, NULL, '2020-04-18 04:13:00', NULL, 'https://pan.baidu.com/s/1x0681Z3ihl22-64iWqG6eQ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'x0681Z3ihl22-64iWqG6eQ', '2020-04-18 04:13:00', '2020-04-18 04:13:00', NULL, 0, 'VALID', NULL, 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-5-3E2zCNmGibzvDBD4nzWw', 6, NULL, NULL, '尚硅谷SpringBoot整合篇', NULL, NULL, '2020-04-18 04:41:00', '2019-09-27 11:40:00', 'https://pan.baidu.com/s/15-3E2zCNmGibzvDBD4nzWw', NULL, 'DIR', '尚硅谷SpringBoot整合篇', '0', 'null', '剪贴***本求', '{\"mode\":\"DIR\",\"share_time\":1569555600000,\"name\":\"\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"剪贴***本求\"}', '[{\"name\":\"尚硅谷SpringBoot整合篇\",\"mode\":\"DIR\",\"size\":\"0\"}]', '5-3E2zCNmGibzvDBD4nzWw', '2020-04-18 04:41:00', '2020-04-18 04:41:00', NULL, 0, 'VALID', NULL, 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-otG4XGEDK9WmZsqDFxcDFw', 7, NULL, NULL, '【千锋Ja-va】3天学会SpringMVC框架教程（35集）', NULL, NULL, '2020-04-18 04:46:00', '2018-11-26 16:40:00', 'https://pan.baidu.com/s/1otG4XGEDK9WmZsqDFxcDFw', NULL, 'DIR', '【千锋Ja-va】3天学会SpringMVC框架教程（35集）', '0', 'null', '千*教育', '{\"mode\":\"DIR\",\"share_time\":1543221600000,\"name\":\"\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"千*教育\"}', '[{\"name\":\"【千锋Ja-va】3天学会SpringMVC框架教程（35集）\",\"mode\":\"DIR\",\"size\":\"0\"}]', 'otG4XGEDK9WmZsqDFxcDFw', '2020-04-18 04:46:00', '2020-04-18 04:42:00', NULL, 0, 'VALID', NULL, 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-ntFs9kP', 8, NULL, NULL, NULL, NULL, NULL, '2020-04-18 04:47:00', NULL, 'https://pan.baidu.com/s/1ntFs9kP', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ntFs9kP', '2020-04-18 04:47:00', '2020-04-18 04:47:00', NULL, 0, 'VALID', NULL, 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-mJez22rDYCRHNp1yidwd1g', 9, NULL, NULL, '[2019-11-06]Warcraft.III.Test.Battlenet', NULL, '', '2020-04-18 10:41:00', '2019-11-06 17:08:00', 'https://pan.baidu.com/s/1mJez22rDYCRHNp1yidwd1g', NULL, 'DIR', '[2019-11-06]Warcraft.III.Test.Battlenet', '0', 'null', '94****831', '{\"mode\":\"DIR\",\"share_time\":1573031280000,\"name\":\"\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"94****831\"}', '[{\"name\":\"[2019-11-06]Warcraft.III.Test.Battlenet\",\"mode\":\"DIR\",\"size\":\"0\"}]', 'mJez22rDYCRHNp1yidwd1g', '2020-04-18 10:41:00', '2020-04-18 10:41:00', '', 0, 'VALID', '', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-JdI_zvJ24T_ECRkpHprb8A', 10, NULL, NULL, 'Netty Springboot仿微信聊天全栈实战-Java仿微信全栈 高性能后台 移动客户端', NULL, '', '2020-06-06 17:34:00', '2018-10-09 15:51:00', 'https://pan.baidu.com/s/1JdI_zvJ24T_ECRkpHprb8A', NULL, 'DIR', 'Netty+Springboot仿微信聊天全栈实战-Java仿微信全栈  高性能后台+移动客户端', '0', 'null', 'wus****on2', '{\"mode\":\"DIR\",\"share_time\":1539071460000,\"name\":\"Netty+Springboot仿微信聊天全栈实战-Java仿微信全栈  高性能后台+移动客户端\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"wus****on2\"}', '', 'JdI_zvJ24T_ECRkpHprb8A', '2020-06-06 17:34:00', NULL, '[{\"url\":\"undefined\",\"title\":\"undefined\"}]', 0, 'VALID', '8cx1', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-jGFQ96Y', 11, NULL, NULL, 'txt全文阅读龙使_全本打包《龙使》.rar', NULL, '', NULL, '2020-04-19 06:45:00', 'https://pan.baidu.com/s/1jGFQ96Y', NULL, '', '', '0', '', NULL, '', '', 'jGFQ96Y', NULL, NULL, '', NULL, 'VALID', '', '');
INSERT INTO `yun_data` VALUES ('BDY-mgiggYG', 12, NULL, NULL, '传智播客Spring2.5视频教程', NULL, '', '2020-04-19 09:20:00', '2014-12-28 13:35:00', 'https://pan.baidu.com/s/1mgiggYG', NULL, 'DIR', '传智播客Spring2.5视频教程', '0', 'null', 'de****ter', '{\"mode\":\"DIR\",\"share_time\":1419744900000,\"name\":\"\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"de****ter\"}', '[{\"name\":\"传智播客Spring2.5视频教程\",\"mode\":\"DIR\",\"size\":\"0\"}]', 'mgiggYG', '2020-04-19 09:20:00', '2020-04-19 09:20:00', '', 0, 'VALID', '', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-c08mOms', 13, NULL, NULL, '易语言大型3D游戏开发 源码 开发资源包.zip', NULL, '', NULL, '2020-04-21 09:20:00', 'https://pan.baidu.com/s/1c08mOms', NULL, '', '', '0', '', NULL, '', '', 'c08mOms', NULL, NULL, '', NULL, 'VALID', NULL, '');
INSERT INTO `yun_data` VALUES ('BDY-dhlmVTvpEyLd9c59MurKgA', 14, NULL, NULL, '', NULL, '', '2020-04-24 23:04:00', NULL, 'https://pan.baidu.com/s/1dhlmVTvpEyLd9c59MurKgA', NULL, '', '', '', '', '', '', '', 'dhlmVTvpEyLd9c59MurKgA', '2020-04-24 23:04:00', '2020-04-24 23:04:00', '', 0, 'VALID', '', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-c1dPkdU', 15, NULL, NULL, '', NULL, '', '2020-05-26 13:41:00', '2016-01-27 18:02:00', 'https://pan.baidu.com/s/1c1dPkdU', NULL, 'FILE', 'redis从入门到精通视频教程等', '0', 'null', 'ta****ear', '{\"mode\":\"FILE\",\"share_time\":1453888920000,\"name\":\"redis从入门到精通视频教程等\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"ta****ear\"}', '', 'c1dPkdU', '2020-05-26 13:41:00', '2020-04-25 11:47:00', '', 0, 'VALID', '', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-3E2jkPHtC578o_Rn-FiywA', 16, NULL, NULL, 'Springcloud微服务框架体系详解集合 Eureka+Ribbon+Feign+HyStrix+Zuul+ConfigServer', NULL, '', '2020-04-26 08:50:00', '2020-04-25 12:08:00', 'https://pan.baidu.com/s/13E2jkPHtC578o_Rn-FiywA', NULL, 'DIR', 'Springcloud微服务框架体系详解集合 Eureka+Ribbon+Feign+HyStrix+Zuul+ConfigServer', '0', 'null', '水瓶**DP', '{\"mode\":\"DIR\",\"share_time\":1587787680000,\"name\":\"\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"水瓶**DP\"}', '[{\"name\":\"Springcloud微服务框架体系详解集合 Eureka+Ribbon+Feign+HyStrix+Zuul+ConfigServer\",\"mode\":\"DIR\",\"size\":\"0\"}]', '3E2jkPHtC578o_Rn-FiywA', '2020-04-26 08:50:00', '2020-04-25 13:22:00', '[{\"url\":\"undefined\",\"title\":\"undefined\"}]', 0, 'VALID', 'clk3', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-AWQ_OjhA2T7Iq_8idbRfxg', 17, NULL, NULL, 'Java高级开发工程师', NULL, '', '2020-05-26 15:58:00', '2019-03-12 16:28:00', 'https://pan.baidu.com/s/1AWQ_OjhA2T7Iq_8idbRfxg', NULL, 'DIR', 'Java高级开发工程师', '0', 'null', 'S*_橋', '{\"mode\":\"DIR\",\"share_time\":1552379280000,\"name\":\"Java高级开发工程师\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"S*_橋\"}', '', 'AWQ_OjhA2T7Iq_8idbRfxg', '2020-05-26 15:58:00', NULL, '[{\"url\":\"undefined\",\"title\":\"undefined\"}]', 0, 'VALID', '67nf', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-bktTVK', 18, NULL, NULL, '精通spring - Mastering Spring.pdf', NULL, '', NULL, '2020-05-26 11:23:00', 'https://pan.baidu.com/s/1bktTVK', NULL, '', '', '0', '', NULL, '', '', 'bktTVK', NULL, NULL, '', NULL, 'VALID', NULL, '');
INSERT INTO `yun_data` VALUES ('BDY-skDR7op', 19, NULL, NULL, 'Spring SpringMVC MyBatis', NULL, '', NULL, '2020-05-26 11:23:00', 'https://pan.baidu.com/s/1skDR7op', NULL, '', '', '0', '', NULL, '', '', 'skDR7op', NULL, NULL, '', NULL, 'VALID', '', '');
INSERT INTO `yun_data` VALUES ('BDY-OeCcX_IxuFPsOAu4bOnGtw', 20, NULL, NULL, 'Spring源码深度解析.pdf', NULL, '', '2020-05-26 13:25:00', '2019-10-31 17:06:00', 'https://pan.baidu.com/s/1OeCcX_IxuFPsOAu4bOnGtw', NULL, 'FILE', 'Spring源码深度解析.pdf', '0', 'null', '28****452', '{\"mode\":\"FILE\",\"share_time\":1572512760000,\"name\":\"Spring源码深度解析.pdf\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"28****452\"}', '', 'OeCcX_IxuFPsOAu4bOnGtw', '2020-05-26 13:25:00', NULL, '', 0, 'VALID', NULL, 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-_Fk34bBIEgTv-TLM0PDNfA', 21, NULL, NULL, '', NULL, '', '2020-05-26 15:55:00', '2019-01-29 20:42:00', 'https://pan.baidu.com/s/1_Fk34bBIEgTv-TLM0PDNfA', NULL, 'FILE', 'Netty进阶之路 跟着案例学Netty@www.java1234.com.pdf', '0', 'null', 'op**开源', '{\"mode\":\"FILE\",\"share_time\":1548765720000,\"name\":\"Netty进阶之路 跟着案例学Netty@www.java1234.com.pdf\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"op**开源\"}', '', '_Fk34bBIEgTv-TLM0PDNfA', '2020-05-26 15:55:00', '2020-05-26 15:55:00', '', 0, 'VALID', '', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-cAspE', 22, NULL, NULL, 'JAVA SQL Sever.zip', NULL, '', '2020-06-06 12:45:00', '2015-10-24 22:11:00', 'https://pan.baidu.com/s/1cAspE', NULL, 'FILE', 'JAVA+SQL Sever.zip', '0', 'null', '我的***q1', '{\"mode\":\"FILE\",\"share_time\":1445695860000,\"name\":\"JAVA+SQL Sever.zip\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"我的***q1\"}', '', 'cAspE', '2020-06-06 12:45:00', NULL, '', 0, 'VALID', '', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-4npI4HfOgmXXcHnQ8i9rdg', 23, NULL, NULL, '告别996 实现高效编程 减少开发压力', NULL, '', NULL, '2020-06-01 19:18:00', 'https://pan.baidu.com/s/14npI4HfOgmXXcHnQ8i9rdg', NULL, '', '', '0', '', NULL, '', '', '4npI4HfOgmXXcHnQ8i9rdg', NULL, NULL, '', NULL, 'VALID', '', '');
INSERT INTO `yun_data` VALUES ('BDY-sjLy9Ut', 24, NULL, NULL, 'jujueshangban.apk', NULL, '', NULL, '2020-06-04 15:11:00', 'https://pan.baidu.com/s/1sjLy9Ut', NULL, '', '', '0', '', NULL, '', '', 'sjLy9Ut', NULL, NULL, '', NULL, 'VALID', '', '');
INSERT INTO `yun_data` VALUES ('BDY-0m1461GWmZwBOFclTLXNqw', 25, NULL, NULL, 'jujunaught.zip', NULL, '', NULL, '2020-06-04 15:03:00', 'https://pan.baidu.com/s/10m1461GWmZwBOFclTLXNqw', NULL, '', '', '0', '', NULL, '', '', '0m1461GWmZwBOFclTLXNqw', NULL, NULL, '', NULL, 'VALID', 'srwx', '');
INSERT INTO `yun_data` VALUES ('BDY-eQ3Ok5W', 26, NULL, NULL, 'juju · 桜雨.mp3', NULL, '', '2020-06-06 12:52:00', '2014-02-23 03:00:00', 'https://pan.baidu.com/s/1eQ3Ok5W', NULL, 'FILE', 'JUJU · 桜雨.mp3', '0', 'null', '隐*恶疾', '{\"mode\":\"FILE\",\"share_time\":1393095600000,\"name\":\"JUJU · 桜雨.mp3\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"隐*恶疾\"}', '', 'eQ3Ok5W', '2020-06-06 12:52:00', NULL, '', 0, 'VALID', '', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-Z9uriKNO2vqM5p0QcHj6lQ?fid=1022963047254148', 27, NULL, NULL, '[NGC][0508]塔克和符咒的神力 Tak and the Power of JuJu（美） .7z', NULL, '', NULL, '2020-06-04 16:22:00', 'https://pan.baidu.com/s/1Z9uriKNO2vqM5p0QcHj6lQ?fid=1022963047254148', NULL, '', '', '0', '', NULL, '', '', 'Z9uriKNO2vqM5p0QcHj6lQ?fid=1022963047254148', NULL, NULL, '', NULL, 'VALID', NULL, '');
INSERT INTO `yun_data` VALUES ('BDY-_WGk73gIXpTuByjsfBNrDw', 28, NULL, NULL, 'JUJU - ラストシーン.rar', NULL, '', NULL, '2020-06-04 16:30:00', 'https://pan.baidu.com/s/1_WGk73gIXpTuByjsfBNrDw', NULL, '', '', NULL, '', NULL, '', '', '_WGk73gIXpTuByjsfBNrDw', NULL, NULL, '', NULL, 'VALID', NULL, '');
INSERT INTO `yun_data` VALUES ('BDY-bHxDEIA_aIS0aodToNFXrA?fid=956830754276391', 29, NULL, NULL, '视频【瑞客论坛 www.ruike1.com】 .avi', NULL, '', NULL, '2020-06-04 20:49:00', 'https://pan.baidu.com/s/1bHxDEIA_aIS0aodToNFXrA?fid=956830754276391', NULL, '', '', '0', '', NULL, '', '', 'bHxDEIA_aIS0aodToNFXrA?fid=956830754276391', NULL, NULL, '', NULL, 'VALID', NULL, '');
INSERT INTO `yun_data` VALUES ('BDY-8mtrElEspEYjEyn8F2W5Tw', 30, NULL, NULL, '闪电侠V1.0.zip', NULL, '', NULL, '2020-06-04 20:51:00', 'https://pan.baidu.com/s/18mtrElEspEYjEyn8F2W5Tw', NULL, '', '', NULL, '', NULL, '', '', '8mtrElEspEYjEyn8F2W5Tw', NULL, NULL, '', NULL, 'VALID', NULL, '');
INSERT INTO `yun_data` VALUES ('BDY-dnT1v', 31, NULL, NULL, '闪电侠', NULL, '', NULL, '2020-06-04 21:32:00', 'https://pan.baidu.com/s/1dnT1v', NULL, '', '', '0', '', NULL, '', '', 'dnT1v', NULL, NULL, '', NULL, 'VALID', '', '');
INSERT INTO `yun_data` VALUES ('BDY-0_Yj2RHU7ds402ZfP-uH6g', 32, NULL, NULL, '《redis入门指南（第2版）-李子骅》电子书.zip', NULL, '', NULL, '2020-06-04 21:34:00', 'https://pan.baidu.com/s/10_Yj2RHU7ds402ZfP-uH6g', NULL, '', '', '8.9M', '', NULL, '', '', '0_Yj2RHU7ds402ZfP-uH6g', NULL, NULL, '', NULL, 'VALID', '', '');
INSERT INTO `yun_data` VALUES ('BDY-zUNP6vR8fOzprqPJblgucw', 33, NULL, NULL, '24、Netty源码剖析与实战（更新完毕）', NULL, '', NULL, '2020-06-04 21:41:00', 'https://pan.baidu.com/s/1zUNP6vR8fOzprqPJblgucw', NULL, '', '', NULL, '', NULL, '', '', 'zUNP6vR8fOzprqPJblgucw', NULL, NULL, '', NULL, 'VALID', NULL, '');
INSERT INTO `yun_data` VALUES ('BDY-OW_tLkwI6qkTiqsxOXZlcg', 34, NULL, NULL, '《dubbo分布式系统架构实战》视频教程', NULL, '', NULL, '2020-06-04 22:46:00', 'https://pan.baidu.com/s/1OW_tLkwI6qkTiqsxOXZlcg', NULL, '', '', '1KB', '', NULL, '', '', 'OW_tLkwI6qkTiqsxOXZlcg', NULL, NULL, '', NULL, 'VALID', '', '');
INSERT INTO `yun_data` VALUES ('BDY-bqEHRg4aVtYsUdiBLwBQRg?fid=252810770012365', 35, NULL, NULL, '「java分布式系列」dubboduboo zookeeper.doc', NULL, '', NULL, '2020-06-04 22:47:00', 'https://pan.baidu.com/s/1bqEHRg4aVtYsUdiBLwBQRg?fid=252810770012365', NULL, '', '', '333K', '', NULL, '', '', 'bqEHRg4aVtYsUdiBLwBQRg?fid=252810770012365', NULL, NULL, '', NULL, 'VALID', '', '');
INSERT INTO `yun_data` VALUES ('BDY-i55rZd7#list/path=/2018尚学堂IT好视频/互联网架构师/尚学堂_白鹤翔_架构师内部视频/尚学堂_白鹤翔_10_dubbo视频/dubbo资料', 36, NULL, NULL, '尚学堂_白鹤翔_10_dubbo视频dubbo资料', NULL, '', NULL, '2020-06-04 22:47:00', 'https://pan.baidu.com/s/1i55rZd7#list/path=/2018尚学堂IT好视频/互联网架构师/尚学堂_白鹤翔_架构师内部视频/尚学堂_白鹤翔_10_dubbo视频/dubbo资料', NULL, '', '', '1KB', '', NULL, '', '', 'i55rZd7#list/path=/2018尚学堂IT好视频/互联网架构师/尚学堂_白鹤翔_架构师内部视频/尚学堂_白鹤翔_10_dubbo视频/dubbo资料', NULL, NULL, '', NULL, 'VALID', '', '');
INSERT INTO `yun_data` VALUES ('BDY-4uDrz', 37, NULL, NULL, 'dubbo教程', NULL, '', NULL, '2020-06-04 22:47:00', 'https://pan.baidu.com/s/14uDrz', NULL, '', '', '0', '', NULL, '', '', '4uDrz', NULL, NULL, '', NULL, 'VALID', '', '');
INSERT INTO `yun_data` VALUES ('BDY-EROz_cTQMqifnkp3bz5mgw', 38, NULL, NULL, '尚硅谷大数据技术之ELK.doc', NULL, '', '2020-06-06 12:46:00', '2020-04-11 16:34:00', 'https://pan.baidu.com/s/1EROz_cTQMqifnkp3bz5mgw', NULL, 'FILE', '尚硅谷大数据技术之ELK.doc', '0', 'null', '156*****532', '{\"mode\":\"FILE\",\"share_time\":1586594040000,\"name\":\"尚硅谷大数据技术之ELK.doc\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"156*****532\"}', '', 'EROz_cTQMqifnkp3bz5mgw', '2020-06-06 12:46:00', NULL, '[{\"url\":\"undefined\",\"title\":\"undefined\"}]', 0, 'VALID', 'f2vq', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-mGBIZj4RPHu2KM2E7TGvKA', 39, NULL, NULL, '', NULL, '', '2020-06-06 13:53:00', '2020-06-06 12:46:00', 'https://pan.baidu.com/s/1mGBIZj4RPHu2KM2E7TGvKA', NULL, 'DIR', '42 MySQL实战45讲', '0', 'null', '水瓶**DP', '{\"mode\":\"DIR\",\"share_time\":1591418760000,\"name\":\"42 MySQL实战45讲\",\"size\":\"0\",\"user_id\":\"null\",\"user_name\":\"水瓶**DP\"}', '', 'mGBIZj4RPHu2KM2E7TGvKA', '2020-06-06 13:53:00', '2020-06-06 13:52:00', '[{\"url\":\"undefined\",\"title\":\"undefined\"}]', 0, 'VALID', '06al', 'BDY');
INSERT INTO `yun_data` VALUES ('BDY-xf6PTJedUWlf4AwEH4UDSg', 40, NULL, NULL, 'redis-3.2.11.tar.gz', NULL, '', NULL, '2020-06-06 16:43:00', 'https://pan.baidu.com/s/1xf6PTJedUWlf4AwEH4UDSg', NULL, '', '', '0', '', NULL, '', '', 'xf6PTJedUWlf4AwEH4UDSg', NULL, NULL, '', NULL, 'VALID', '15mp', '');
INSERT INTO `yun_data` VALUES ('BDY-GgBdTY3dSpaTGtK3qP4FYQ', 41, NULL, NULL, '002_动力节点_Redis视频教程_RDBMS的劣势.rar', NULL, '', NULL, '2020-06-06 16:49:00', 'https://pan.baidu.com/s/1GgBdTY3dSpaTGtK3qP4FYQ', NULL, '', '', NULL, '', NULL, '', '', 'GgBdTY3dSpaTGtK3qP4FYQ', NULL, NULL, '', NULL, 'VALID', NULL, '');

-- ----------------------------
-- Table structure for yun_user
-- ----------------------------
DROP TABLE IF EXISTS `yun_user`;
CREATE TABLE `yun_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uk` bigint(20) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pubshare_count` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `pubshare_crawled` bit(1) NULL DEFAULT NULL,
  `follow_crawled` bit(1) NULL DEFAULT NULL,
  `follow_time` datetime(0) NULL DEFAULT NULL,
  `fans_crawled` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_yun_user_uk`(`uk`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yun_user
-- ----------------------------
INSERT INTO `yun_user` VALUES (1, '51xuejava_com', 2889076181, '2020-04-11 15:50:26', 'http://himg.bdimg.com/sys/portrait/item/83ac0a37.jpg', 152, 3, b'0', b'1', '2017-04-14 21:23:58', b'1');
INSERT INTO `yun_user` VALUES (2, '秀秀e图书', 3678306532, '2020-04-11 15:50:26', 'http://himg.bdimg.com/sys/portrait/item/cedb3b3a.jpg', 2076, 3, b'0', b'1', '2020-04-12 03:05:19', b'1');

-- ----------------------------
-- Procedure structure for mypro
-- ----------------------------
DROP PROCEDURE IF EXISTS `mypro`;
delimiter ;;
CREATE PROCEDURE `mypro`()
BEGIN
DELETE from yun_data where state!='VALID';
alter table yun_data auto_increment= 1;
SET @rownum = 0;
UPDATE yun_data SET id = @rownum :=@rownum +1;
end
;;
delimiter ;

-- ----------------------------
-- Event structure for eventJob
-- ----------------------------
DROP EVENT IF EXISTS `eventJob`;
delimiter ;;
CREATE EVENT `eventJob`
ON SCHEDULE
EVERY '10' MINUTE STARTS '2020-04-18 11:05:04'
ON COMPLETION PRESERVE
DO call mypro()
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
