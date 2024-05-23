/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : course_schedule

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2024-05-23 23:56:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` bigint(20) NOT NULL COMMENT '班级id',
  `class_no` varchar(8) NOT NULL COMMENT '班级编号',
  `class_name` varchar(36) NOT NULL COMMENT '班级名称',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '班级人数',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` int(11) DEFAULT '0' COMMENT '0未删除（默认），1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='班级';

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('1', '00000001', '1班', '48', null, null, null, null, '0');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` bigint(20) NOT NULL COMMENT '教室id',
  `room_no` varchar(8) NOT NULL COMMENT '教室编号',
  `room_name` varchar(36) DEFAULT NULL COMMENT '教室名称',
  `area_no` varchar(4) NOT NULL COMMENT '所在教学区域(教学楼、体育场等)编号',
  `area_name` varchar(36) DEFAULT NULL COMMENT '教学区域名',
  `capacity` int(11) NOT NULL DEFAULT '0' COMMENT '教室人数容量',
  `attr` varchar(2) DEFAULT NULL COMMENT '教室属性 01理论 02实验 03实践 04体育课',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='[input]教室';

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '00000001', '明理1', '01', '北教', '50', '01', '空调坏了', null, '2024-05-19 22:29:40', null, '2024-05-21 19:32:28', '0');
INSERT INTO `room` VALUES ('2', '00000002', '明理2', '02', '明理楼', '50', '01', null, null, '2024-05-19 22:29:40', null, '2024-05-21 20:53:56', '0');
INSERT INTO `room` VALUES ('3', '00000003', '明理3', '01', '北教', '30', '01', null, null, '2024-05-19 22:29:40', null, '2024-05-21 19:31:35', '0');
INSERT INTO `room` VALUES ('4', '00000004', '明理4', '01', '北教', '50', '02', null, null, '2024-05-19 22:29:40', null, '2024-05-21 19:31:43', '0');

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester` (
  `id` bigint(20) NOT NULL,
  `semester_name` varchar(255) DEFAULT NULL COMMENT '学期名',
  `semester_weeks_sum` int(11) DEFAULT NULL COMMENT '学期总周数',
  `semester_start_date` date DEFAULT NULL COMMENT '学期开始日（必须为星期一）',
  `semester_end_date` date DEFAULT NULL COMMENT '学期结束日',
  `semester_status` int(11) DEFAULT '0' COMMENT '是否已排课 0未排课 1已排课',
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of semester
-- ----------------------------
INSERT INTO `semester` VALUES ('1', '2023-2024学年 第1学期', '20', '2023-09-03', '2024-01-21', '0', null, '2024-05-20 14:30:35', '1', '2024-05-20 14:45:07', '0');
INSERT INTO `semester` VALUES ('2', '2023-2024学年 第2学期', '20', '2024-03-03', '2024-07-21', '0', null, '2024-05-20 14:31:30', '1', '2024-05-20 14:45:15', '0');
INSERT INTO `semester` VALUES ('1792525112327553026', 'test2', '12', '2024-05-05', '2024-07-28', '0', null, '2024-05-20 19:57:44', '1', '2024-05-20 23:29:34', '0');
INSERT INTO `semester` VALUES ('1792565727375486977', 'test13', '2', '2024-05-05', '2024-05-19', '0', '1', '2024-05-20 22:39:08', '1', '2024-05-20 23:29:35', '0');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL COMMENT 'id',
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
  `class_name` varchar(36) DEFAULT NULL COMMENT '班级名',
  `teacher_name` varchar(36) NOT NULL COMMENT '教师姓名',
  `course_name` varchar(36) DEFAULT NULL COMMENT '课程名',
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='[input]教学任务表(包括教学大纲，教师选课)';

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '00000001', '00000001', '00000001', '01', '01', '01', '0', '01', '1', '1', '4', '1班', '张三', '高数', null, '2024-05-19 22:36:10', null, '2024-05-21 15:40:41', '0');
INSERT INTO `task` VALUES ('2', '00000001', '00000002', '00000002', '01', '01', '01', '0', '01', '1', '1', '4', '1班', '李四', 'JAVA', null, '2024-05-19 22:36:10', null, '2024-05-21 15:40:46', '0');

-- ----------------------------
-- Table structure for timetable
-- ----------------------------
DROP TABLE IF EXISTS `timetable`;
CREATE TABLE `timetable` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `class_no` varchar(8) NOT NULL COMMENT '班级编号',
  `course_no` varchar(8) NOT NULL COMMENT '课程编号',
  `teacher_no` varchar(8) NOT NULL COMMENT '讲师编号',
  `room_no` varchar(8) NOT NULL COMMENT '教室编号',
  `timeslot` int(11) NOT NULL COMMENT '上课时间(大节)',
  `start_week` int(11) DEFAULT NULL COMMENT '开始周',
  `end_week` int(11) DEFAULT NULL COMMENT '结束周',
  `biweekly` int(11) DEFAULT NULL COMMENT '单双周 0代表非单双周 1代表单周 2代表双周',
  `semester_id` bigint(20) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='[output]课程表';

-- ----------------------------
-- Records of timetable
-- ----------------------------
INSERT INTO `timetable` VALUES ('1', '1', '', '1', '1', '1', '1', '1', '0', '1', null, '2024-05-19 22:39:00', null, '2024-05-23 14:19:41', '0');
INSERT INTO `timetable` VALUES ('2', '8', '', '1', '1', '1', '1', '1', '0', '2', null, '2024-05-19 22:39:00', null, '2024-05-23 15:08:25', '0');
INSERT INTO `timetable` VALUES ('3', '2', '', '1', '2', '1', '1', '1', '0', '1', null, '2024-05-19 22:39:00', null, '2024-05-23 14:19:43', '0');
INSERT INTO `timetable` VALUES ('4', '2', '', '2', '1', '1', '1', '1', '0', '1', null, '2024-05-19 22:39:00', null, '2024-05-23 14:19:44', '0');
INSERT INTO `timetable` VALUES ('5', '3', '', '1', '3', '1', '2', '2', '0', '1', null, '2024-05-23 13:55:09', null, '2024-05-23 15:06:06', '0');
INSERT INTO `timetable` VALUES ('6', '99', '', '99', '99', '23', '1', '1', '0', '1', null, '2024-05-23 22:13:31', null, '2024-05-23 22:15:12', '0');

-- ----------------------------
-- Table structure for timetable_rehearsal
-- ----------------------------
DROP TABLE IF EXISTS `timetable_rehearsal`;
CREATE TABLE `timetable_rehearsal` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `class_no` varchar(8) NOT NULL COMMENT '班级编号',
  `teacher_no` varchar(8) NOT NULL COMMENT '讲师编号',
  `room_no` varchar(8) NOT NULL COMMENT '教室编号',
  `timeslot` int(11) NOT NULL COMMENT '上课时间(大节)',
  `start_week` int(11) DEFAULT NULL COMMENT '开始周',
  `end_week` int(11) DEFAULT NULL COMMENT '结束周',
  `biweekly` int(11) DEFAULT NULL COMMENT '单双周 0代表非单双周 1代表单周 2代表双周',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='排练课程表';

-- ----------------------------
-- Records of timetable_rehearsal
-- ----------------------------
INSERT INTO `timetable_rehearsal` VALUES ('1', '1', '1', '1', '24', '1', '1', '0');
INSERT INTO `timetable_rehearsal` VALUES ('3', '2', '1', '2', '1', '1', '1', '0');
INSERT INTO `timetable_rehearsal` VALUES ('4', '2', '2', '1', '1', '1', '1', '0');
INSERT INTO `timetable_rehearsal` VALUES ('5', '3', '1', '3', '1', '2', '2', '0');
INSERT INTO `timetable_rehearsal` VALUES ('6', '99', '99', '99', '23', '1', '1', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `username` varchar(36) NOT NULL COMMENT '用户名',
  `password` varchar(36) NOT NULL COMMENT '密码',
  `type` int(1) DEFAULT '0' COMMENT '0管理员(默认) 1超级管理员',
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户(管理员)';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '1234', '1', null, '2024-05-19 22:40:38', null, '2024-05-19 22:40:38', '0');
INSERT INTO `user` VALUES ('2', '123', '1234', '0', null, '2024-05-19 22:40:38', null, '2024-05-19 22:40:38', '0');
INSERT INTO `user` VALUES ('3', 'admin1', '1234', '0', null, '2024-05-19 22:40:38', null, '2024-05-19 22:40:38', '0');
