/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : course_schedule

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2024-05-18 23:40:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `class_no` varchar(8) NOT NULL COMMENT '班级编号',
  `class_name` varchar(36) NOT NULL COMMENT '班级名称',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '班级人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='班级';

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('1', '00000001', '1班', '50');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教室id',
  `room_no` varchar(8) NOT NULL COMMENT '教室编号',
  `room_name` varchar(36) DEFAULT NULL COMMENT '教室名称',
  `area_no` varchar(4) NOT NULL COMMENT '所在教学区域(教学楼、体育场等)编号',
  `capacity` int(11) NOT NULL DEFAULT '0' COMMENT '教室人数容量',
  `attr` varchar(2) DEFAULT NULL COMMENT '教室属性 01理论 02实验 03实践 04体育课',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='[input]教室';

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '00000001', '明理1', '01', '50', '01', null);
INSERT INTO `room` VALUES ('2', '00000002', '明理2', '02', '50', '01', null);
INSERT INTO `room` VALUES ('3', '00000003', '明理3', '01', '30', '01', null);
INSERT INTO `room` VALUES ('4', '00000004', '明理4', '01', '50', '02', null);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `class_no` varchar(8) NOT NULL COMMENT '班级编号',
  `teacher_no` varchar(8) NOT NULL COMMENT '教师编号',
  `course_no` varchar(8) NOT NULL COMMENT '课程编号',
  `course_attr` varchar(2) DEFAULT NULL COMMENT '课程属性 01理论 02实验 03实践 04体育课',
  `start_week` varchar(2) DEFAULT NULL COMMENT '开始周',
  `end_week` varchar(2) DEFAULT NULL COMMENT '结束周',
  `biweekly` varchar(1) DEFAULT '0' COMMENT '单双周 0代表非单双周 1代表单周 2代表双周',
  `area_no` varchar(2) DEFAULT NULL COMMENT '所在教学区域(教学楼、体育场等)编号',
  `class_count` varchar(1) DEFAULT '1' COMMENT '同时上课班级数(>1为大班课)',
  `weeks_sum` int(11) DEFAULT '0' COMMENT '周数',
  `weeks_number` int(11) DEFAULT '0' COMMENT '周学时 - 必须是2的倍数',
  `course_name` varchar(36) DEFAULT NULL COMMENT '课程名',
  `teacher_name` varchar(36) NOT NULL COMMENT '教师姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='[input]教学任务表(包括教学大纲，教师选课)';

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '00000001', '00000001', '00000001', '01', '01', '01', '0', '01', '1', '1', '4', '高数', '张三');
INSERT INTO `task` VALUES ('2', '00000001', '00000002', '00000002', '01', '01', '01', '0', '01', '1', '1', '4', 'JAVA', '李四');

-- ----------------------------
-- Table structure for timetable
-- ----------------------------
DROP TABLE IF EXISTS `timetable`;
CREATE TABLE `timetable` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `classes_no` varchar(8) NOT NULL COMMENT '班级编号',
  `course_no` varchar(8) NOT NULL COMMENT '课程编号',
  `teacher_no` varchar(8) NOT NULL COMMENT '讲师编号',
  `room_no` varchar(8) NOT NULL COMMENT '教室编号',
  `timeslot` int(11) NOT NULL COMMENT '上课时间(大节)',
  `start_week` int(11) DEFAULT NULL COMMENT '开始周',
  `end_week` int(11) DEFAULT NULL COMMENT '结束周',
  `biweekly` int(11) DEFAULT NULL COMMENT '单双周 0代表非单双周 1代表单周 2代表双周',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='[output]课程表';

-- ----------------------------
-- Records of timetable
-- ----------------------------
INSERT INTO `timetable` VALUES ('1', '1', '1', '1', '1', '5', '1', '1', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(36) NOT NULL COMMENT '用户名',
  `password` varchar(36) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户(管理员)';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '1234');
INSERT INTO `user` VALUES ('2', '123', '1234');
INSERT INTO `user` VALUES ('3', 'admin1', '1234');
