/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : course_schedule

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2024-05-11 15:50:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `class_no` varchar(20) NOT NULL COMMENT '班级编号',
  `class_name` varchar(36) NOT NULL COMMENT '班级名称',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '班级人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='班级';

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教室id',
  `room_no` varchar(20) NOT NULL COMMENT '教室编号',
  `room_name` varchar(36) DEFAULT NULL COMMENT '教室名称',
  `teachbuild_no` varchar(4) NOT NULL COMMENT '所在教学楼编号',
  `capacity` int(11) NOT NULL DEFAULT '0' COMMENT '教室人数容量',
  `attr` varchar(2) DEFAULT NULL COMMENT '教室属性 01理论 02实验 03实践 04体育课',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='[input]教室';

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `class_no` varchar(20) NOT NULL COMMENT '班级编号',
  `course_no` varchar(20) NOT NULL COMMENT '课程编号',
  `course_name` varchar(36) DEFAULT NULL COMMENT '课程名',
  `teacher_no` varchar(20) NOT NULL COMMENT '教师编号',
  `teacher_name` varchar(36) NOT NULL COMMENT '教师姓名',
  `course_attr` varchar(2) NOT NULL COMMENT '课程属性 01理论 02实验 03实践 04体育课',
  `size` int(11) DEFAULT '0' COMMENT '班级人数',
  `weeks_sum` int(11) DEFAULT '0' COMMENT '周数',
  `weeks_number` int(11) DEFAULT '0' COMMENT '周学时 - 必须是2的倍数',
  `start_week` int(11) DEFAULT '0' COMMENT '开始周',
  `end_week` int(11) DEFAULT '0' COMMENT '结束周',
  `bi_biweekly` int(11) DEFAULT '0' COMMENT '单双周 0代表非单双周 1代表单周 2代表双周',
  `class_count` int(11) DEFAULT '1' COMMENT '同时上课班级数(>1为大班课)',
  `point` int(11) DEFAULT '0' COMMENT '学分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='[input]教学任务表(包括教学大纲，教师选课)';

-- ----------------------------
-- Table structure for timetable
-- ----------------------------
DROP TABLE IF EXISTS `timetable`;
CREATE TABLE `timetable` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `classes_no` varchar(20) NOT NULL COMMENT '班级编号',
  `course_no` varchar(20) NOT NULL COMMENT '课程编号',
  `teacher_no` varchar(20) NOT NULL COMMENT '讲师编号',
  `room_no` varchar(20) NOT NULL COMMENT '教室编号',
  `timeslot` varchar(10) NOT NULL COMMENT '上课时间',
  `task_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='[output]课程表';

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
