/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : course_schedule

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

*/

create database if not exists course_schedule default charset utf8mb4;
use course_schedule;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `classes`
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `size` int(11) DEFAULT NULL,
  `c_name` varchar(11) DEFAULT NULL,
  `remark` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO classes VALUES ('1', '40', '18计算机1班', null);
INSERT INTO classes VALUES ('2', '40', '18计算机2班', null);
INSERT INTO classes VALUES ('3', '40', '18计算机3班', null);
INSERT INTO classes VALUES ('4', '40', '18计算机4班', null);
INSERT INTO classes VALUES ('5', '40', '18电信1班', null);
INSERT INTO classes VALUES ('6', '40', '18电信2班', null);
INSERT INTO classes VALUES ('7', '40', '18网络1班', null);
INSERT INTO classes VALUES ('8', '40', '18网络2班', null);
INSERT INTO classes VALUES ('9', '40', '18通信1班', null);
INSERT INTO classes VALUES ('10', '40', '18通信2班', null);
INSERT INTO classes VALUES ('11', '40', '18信安1班', null);
INSERT INTO classes VALUES ('12', '40', '18信安2班', null);
INSERT INTO classes VALUES ('13', '40', '18光信息1班', null);

-- ----------------------------
-- Table structure for `classes_course`
-- ----------------------------
DROP TABLE IF EXISTS `classes_course`;
CREATE TABLE `classes_course` (
  `cc_id` int(11) NOT NULL AUTO_INCREMENT,
  `cc_name` varchar(40) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `cc_size` int(11) DEFAULT NULL,
  `cc_remark` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes_course
-- ----------------------------
INSERT INTO classes_course VALUES ('7', '18计算机1班,18计算机2班,18计算机3班,18计算机4班', '1', '160', '');
INSERT INTO classes_course VALUES ('8', '18计算机1班,18计算机2班', '2', '80', '');
INSERT INTO classes_course VALUES ('9', '18计算机1班,18计算机2班', '3', '80', '');
INSERT INTO classes_course VALUES ('10', '18计算机1班,18计算机2班', '4', '80', '');
INSERT INTO classes_course VALUES ('11', '18计算机1班,18计算机2班', '5', '80', '');
INSERT INTO classes_course VALUES ('12', '18计算机1班,18计算机2班', '6', '80', '');
INSERT INTO classes_course VALUES ('13', '18计算机1班,18计算机2班', '7', '80', '');
INSERT INTO classes_course VALUES ('14', '18计算机1班,18计算机2班', '8', '80', '');
INSERT INTO classes_course VALUES ('15', '18计算机1班,18计算机2班,18计算机3班,18计算机4班', '9', '160', '');
INSERT INTO classes_course VALUES ('16', '18计算机1班,18计算机2班,18计算机3班,18计算机4班', '10', '160', '');
INSERT INTO classes_course VALUES ('17', '18计算机3班,18计算机4班', '2', '80', '');
INSERT INTO classes_course VALUES ('18', '18计算机3班,18计算机4班', '3', '80', '');
INSERT INTO classes_course VALUES ('19', '18计算机3班,18计算机4班', '4', '80', '');
INSERT INTO classes_course VALUES ('20', '18计算机3班,18计算机4班', '5', '80', '');
INSERT INTO classes_course VALUES ('21', '18计算机3班,18计算机4班', '6', '80', '');
INSERT INTO classes_course VALUES ('22', '18计算机3班,18计算机4班', '7', '80', '');
INSERT INTO classes_course VALUES ('23', '18计算机3班,18计算机4班', '8', '80', '');
INSERT INTO classes_course VALUES ('26', '18网络1班,18网络2班', '7', '80', '');
INSERT INTO classes_course VALUES ('27', '18计算机1班,18计算机2班', '10', '160', '');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_code` varchar(10) DEFAULT NULL,
  `c_name` varchar(20) DEFAULT NULL,
  `c_remark` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO course VALUES ('1', '0600029', '高等数学(一)', '');
INSERT INTO course VALUES ('2', '0600073', '离散数学(C)', null);
INSERT INTO course VALUES ('3', '1200142 ', 'C/C++程序设计(B) ', null);
INSERT INTO course VALUES ('4', '1200206', '数据库系统(B) ', null);
INSERT INTO course VALUES ('5', '1201600', 'JAVA语言程序设计', null);
INSERT INTO course VALUES ('6', '1201924', 'WEB技术及应用', null);
INSERT INTO course VALUES ('7', '1300245', '电工电子技术', null);
INSERT INTO course VALUES ('8', '1201401', '汇编语言', null);
INSERT INTO course VALUES ('9', '0100803', '马克思主义基本原理概论', null);
INSERT INTO course VALUES ('10', '1900597', '专业英语', null);

-- ----------------------------
-- Table structure for `course_table`
-- ----------------------------
DROP TABLE IF EXISTS `course_table`;
CREATE TABLE `course_table` (
  `task_id` int(11) NOT NULL,
  `room_id` int(20) DEFAULT NULL,
  `timeslot_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_table
-- ----------------------------
INSERT INTO course_table VALUES ('1', '9', '11');
INSERT INTO course_table VALUES ('2', '3', '1');
INSERT INTO course_table VALUES ('3', '8', '2');
INSERT INTO course_table VALUES ('4', '4', '4');
INSERT INTO course_table VALUES ('5', '2', '14');
INSERT INTO course_table VALUES ('6', '10', '24');
INSERT INTO course_table VALUES ('7', '6', '9');
INSERT INTO course_table VALUES ('8', '1', '21');
INSERT INTO course_table VALUES ('9', '1', '13');
INSERT INTO course_table VALUES ('10', '1', '19');
INSERT INTO course_table VALUES ('11', '2', '4');
INSERT INTO course_table VALUES ('12', '10', '22');
INSERT INTO course_table VALUES ('13', '2', '2');
INSERT INTO course_table VALUES ('14', '1', '7');
INSERT INTO course_table VALUES ('15', '1', '6');
INSERT INTO course_table VALUES ('16', '4', '23');
INSERT INTO course_table VALUES ('17', '3', '14');
INSERT INTO course_table VALUES ('26', '5', '1');
INSERT INTO course_table VALUES ('27', '7', '12');
INSERT INTO course_table VALUES ('28', '6', '24');

-- ----------------------------
-- Table structure for `room`
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `r_name` varchar(12) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `remark` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO room VALUES ('1', '主教101', '160', '光线较暗');
INSERT INTO room VALUES ('2', '主教102', '80', null);
INSERT INTO room VALUES ('3', '主教103', '80', null);
INSERT INTO room VALUES ('4', '主教104', '80', null);
INSERT INTO room VALUES ('5', '主教201', '80', null);
INSERT INTO room VALUES ('6', '主教202', '80', null);
INSERT INTO room VALUES ('7', '主教203', '80', null);
INSERT INTO room VALUES ('8', '主教204', '80', null);
INSERT INTO room VALUES ('9', '二教201', '160', null);
INSERT INTO room VALUES ('10', '二教202', '80', null);
INSERT INTO room VALUES ('11', '二教203', '40', null);
INSERT INTO room VALUES ('12', '二教204', '80', null);
INSERT INTO room VALUES ('13', '二教205', '40', null);

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cc_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `start_week` int(11) DEFAULT NULL,
  `end_week` int(11) DEFAULT NULL,
  `week_node` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO task VALUES ('1', '8', '1520182502', '3', '15', '1');
INSERT INTO task VALUES ('2', '9', '1520182504', '3', '15', '1');
INSERT INTO task VALUES ('3', '10', '1520182506', '3', '15', '1');
INSERT INTO task VALUES ('4', '11', '1520182508', '3', '15', '1');
INSERT INTO task VALUES ('5', '12', '1520182510', '3', '15', '1');
INSERT INTO task VALUES ('6', '13', '1520182512', '3', '15', '1');
INSERT INTO task VALUES ('7', '14', '1520182514', '3', '15', '1');
INSERT INTO task VALUES ('8', '7', '1520182500', '3', '15', '1');
INSERT INTO task VALUES ('9', '15', '1520182512', '3', '15', '1');
INSERT INTO task VALUES ('10', '16', '1520182509', '3', '15', '1');
INSERT INTO task VALUES ('11', '17', '1520182503', '3', '15', '1');
INSERT INTO task VALUES ('12', '18', '1520182505', '3', '15', '1');
INSERT INTO task VALUES ('13', '19', '1520182507', '3', '15', '1');
INSERT INTO task VALUES ('14', '20', '1520182509', '3', '15', '1');
INSERT INTO task VALUES ('15', '21', '1520182511', '3', '15', '1');
INSERT INTO task VALUES ('16', '22', '1520182513', '3', '15', '1');
INSERT INTO task VALUES ('17', '23', '1520182514', '3', '15', '1');
INSERT INTO task VALUES ('26', '26', '1520182512', '4', '15', '1');
INSERT INTO task VALUES ('27', '26', '1520182512', '3', '15', '2');
INSERT INTO task VALUES ('28', '26', '1520182513', '3', '15', '3');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(10) DEFAULT NULL,
  `password` varchar(11) DEFAULT NULL,
  `remark` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1520182516 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO teacher VALUES ('1520182500', '赵一', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182501', '钱二', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182502', '孙三', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182503', '李四', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182504', '周五', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182505', '吴六', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182506', '郑七', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182507', '王吧', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182508', '冯九', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182509', '陈十', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182510', '褚十一', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182511', '卫十二', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182512', '蒋十三', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182513', '沈十四', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182514', '韩十五', '000000', '信息工程学院');
INSERT INTO teacher VALUES ('1520182515', '庞十六', '10086', '信息工程学院');

-- ----------------------------
-- Table structure for `teacher_course`
-- ----------------------------
DROP TABLE IF EXISTS `teacher_course`;
CREATE TABLE `teacher_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `remark` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_course
-- ----------------------------
INSERT INTO teacher_course VALUES ('1', '1', '1520182500', '');
INSERT INTO teacher_course VALUES ('2', '1', '1520182501', '');
INSERT INTO teacher_course VALUES ('3', '2', '1520182502', '');
INSERT INTO teacher_course VALUES ('4', '2', '1520182503', '');
INSERT INTO teacher_course VALUES ('10', '3', '1520182504', '');
INSERT INTO teacher_course VALUES ('11', '3', '1520182505', '');
INSERT INTO teacher_course VALUES ('12', '4', '1520182506', '');
INSERT INTO teacher_course VALUES ('13', '4', '1520182507', '');
INSERT INTO teacher_course VALUES ('14', '5', '1520182508', '');
INSERT INTO teacher_course VALUES ('15', '5', '1520182509', '');
INSERT INTO teacher_course VALUES ('16', '6', '1520182510', '');
INSERT INTO teacher_course VALUES ('17', '6', '1520182511', '');
INSERT INTO teacher_course VALUES ('18', '7', '1520182512', '');
INSERT INTO teacher_course VALUES ('19', '7', '1520182513', '');
INSERT INTO teacher_course VALUES ('20', '8', '1520182514', '');
INSERT INTO teacher_course VALUES ('21', '8', '1520182513', '');
INSERT INTO teacher_course VALUES ('22', '9', '1520182512', '');
INSERT INTO teacher_course VALUES ('23', '9', '1520182511', '');
INSERT INTO teacher_course VALUES ('24', '10', '1520182509', '');
INSERT INTO teacher_course VALUES ('25', '10', '1520182508', '');
INSERT INTO teacher_course VALUES ('26', '2', '1520182500', '');
INSERT INTO teacher_course VALUES ('27', '2', '1520182501', '');

-- ----------------------------
-- Table structure for `timeslot`
-- ----------------------------
DROP TABLE IF EXISTS `timeslot`;
CREATE TABLE `timeslot` (
  `time_id` int(11) NOT NULL AUTO_INCREMENT,
  `timeslot` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`time_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timeslot
-- ----------------------------
INSERT INTO timeslot VALUES ('1', '星期一 第一大节');
INSERT INTO timeslot VALUES ('2', '星期一 第二大节');
INSERT INTO timeslot VALUES ('3', '星期一 第三大节');
INSERT INTO timeslot VALUES ('4', '星期一 第四大节');
INSERT INTO timeslot VALUES ('5', '星期一 第五大节');
INSERT INTO timeslot VALUES ('6', '星期二 第一大节');
INSERT INTO timeslot VALUES ('7', '星期二 第二大节');
INSERT INTO timeslot VALUES ('8', '星期二 第三大节');
INSERT INTO timeslot VALUES ('9', '星期二 第四大节');
INSERT INTO timeslot VALUES ('10', '星期二 第五大节');
INSERT INTO timeslot VALUES ('11', '星期三 第一大节');
INSERT INTO timeslot VALUES ('12', '星期三 第二大节');
INSERT INTO timeslot VALUES ('13', '星期三 第三大节');
INSERT INTO timeslot VALUES ('14', '星期三 第四大节');
INSERT INTO timeslot VALUES ('15', '星期三 第五大节');
INSERT INTO timeslot VALUES ('16', '星期四 第一大节');
INSERT INTO timeslot VALUES ('17', '星期四 第二大节');
INSERT INTO timeslot VALUES ('18', '星期四 第三大节');
INSERT INTO timeslot VALUES ('19', '星期四 第四大节');
INSERT INTO timeslot VALUES ('20', '星期四 第五大节');
INSERT INTO timeslot VALUES ('21', '星期五 第一大节');
INSERT INTO timeslot VALUES ('22', '星期五 第二大节');
INSERT INTO timeslot VALUES ('23', '星期五 第三大节');
INSERT INTO timeslot VALUES ('24', '星期五 第四大节');
INSERT INTO timeslot VALUES ('25', '星期五 第五大节');
INSERT INTO timeslot VALUES ('26', '星期六 第一大节');
INSERT INTO timeslot VALUES ('27', '星期六 第二大节');
INSERT INTO timeslot VALUES ('28', '星期六 第三大节');
INSERT INTO timeslot VALUES ('29', '星期六 第四大节');
INSERT INTO timeslot VALUES ('30', '星期六 第五大节');
INSERT INTO timeslot VALUES ('31', '星期日 第一大节');
INSERT INTO timeslot VALUES ('32', '星期日 第二大节');
INSERT INTO timeslot VALUES ('33', '星期日 第三大节');
INSERT INTO timeslot VALUES ('34', '星期日 第四大节');
INSERT INTO timeslot VALUES ('35', '星期日 第五大节');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_name` (`u_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('1', 'root', '1008611', '0');
INSERT INTO user VALUES ('2', 'pxw', '123456', '0');
INSERT INTO user VALUES ('3', 'zhangsan', '123456', '0');
INSERT INTO user VALUES ('4', '王五', '222222', '0');
INSERT INTO user VALUES ('5', '王二', '222222', '0');
INSERT INTO user VALUES ('7', 'haowu', '1000011', '1');
INSERT INTO user VALUES ('8', 'laoliu', '1000011', '1');
