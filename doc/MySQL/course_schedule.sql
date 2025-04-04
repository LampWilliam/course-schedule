/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : course_schedule

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2024-06-16 13:52:19
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
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `course_no` varchar(8) NOT NULL COMMENT '课程编号',
  `course_name` varchar(36) NOT NULL COMMENT '课程名',
  `course_attr` varchar(2) NOT NULL COMMENT '学时类型编号 01理论 02实验 03实践 04体育课',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
INSERT INTO `semester` VALUES ('1', '2023-2024学年 第1学期', '20', '2023-09-03', '2024-01-21', '1', null, '2024-05-20 14:30:35', '1', '2024-06-09 13:49:49', '0');
INSERT INTO `semester` VALUES ('2', '2023-2024学年 第2学期', '20', '2024-03-03', '2024-07-21', '1', null, '2024-05-20 14:31:30', '1', '2024-05-20 14:45:15', '0');
INSERT INTO `semester` VALUES ('1800541251011895298', '花花幼儿园第1学期', '20', '2024-06-09', '2024-10-27', '1', '1', '2024-06-11 22:51:01', null, '2024-06-13 01:28:49', '0');
INSERT INTO `semester` VALUES ('1800886939994308610', '天才学院的第1学期', '20', '2024-06-02', '2024-10-20', '0', '1', '2024-06-12 21:44:40', null, '2024-06-12 21:44:40', '0');
INSERT INTO `semester` VALUES ('1801134910191185922', '花花幼儿园 第2学期', '20', '2024-06-09', '2024-10-27', '1', '2', '2024-06-13 14:10:00', null, '2024-06-13 14:10:00', '0');
INSERT INTO `semester` VALUES ('1801138268029321217', '演示 第1学期', '21', '2024-06-02', '2024-10-27', '1', '1', '2024-06-13 14:23:21', '1', '2024-06-13 14:23:21', '0');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `class_no` varchar(8) NOT NULL COMMENT '班级编号',
  `teacher_no` varchar(8) NOT NULL COMMENT '教师编号',
  `course_no` varchar(8) NOT NULL COMMENT '课程编号',
  `course_attr` varchar(2) DEFAULT NULL COMMENT '学时类型 01理论 02实验 03实践 04体育课',
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
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL,
  `teacher_no` varchar(8) NOT NULL,
  `teacher_name` varchar(36) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` int(11) DEFAULT '0' COMMENT '0未删除（默认），1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
INSERT INTO `timetable` VALUES ('1800941221061885954', '00000003', '20200202', '00000001', '20000001', '18', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221061885955', '00000003', '20200202', '00000001', '30000002', '24', '1', '16', '1', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221061885956', '00000003', '20200203', '00000002', '20000002', '4', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221061885957', '00000003', '20200203', '00000002', '30000003', '9', '1', '16', '2', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221095440386', '00000003', '20200204', '00000003', '20000003', '6', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221095440387', '00000003', '20200204', '00000003', '30000001', '3', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221095440388', '00000003', '20200205', '00000004', '10000001', '23', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221095440389', '00000003', '20200205', '00000004', '30000001', '1', '17', '18', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221095440390', '00000002', '20200202', '00000001', '20000003', '10', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221095440391', '00000002', '20200202', '00000001', '30000002', '23', '1', '16', '1', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221095440392', '00000002', '20200203', '00000002', '20000002', '2', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221095440393', '00000002', '20200203', '00000002', '30000002', '14', '1', '16', '2', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221095440394', '00000002', '20200204', '00000003', '20000002', '21', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221095440395', '00000002', '20200204', '00000003', '30000002', '12', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221158354946', '00000002', '20200205', '00000004', '10000001', '24', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221158354947', '00000002', '20200205', '00000004', '30000002', '16', '17', '18', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221158354948', '00000001', '20200202', '00000001', '20000001', '3', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221158354949', '00000001', '20200202', '00000001', '30000002', '6', '1', '16', '1', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221158354950', '00000001', '20200203', '00000002', '20000002', '15', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221158354951', '00000001', '20200203', '00000002', '30000002', '7', '1', '16', '2', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221158354952', '00000001', '20200204', '00000003', '20000003', '19', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221158354953', '00000001', '20200204', '00000003', '30000003', '0', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221158354954', '00000001', '20200205', '00000004', '10000003', '22', '1', '16', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1800941221158354955', '00000001', '20200205', '00000004', '30000002', '13', '17', '18', '0', '2', null, '2024-06-13 01:20:21', null, '2024-06-13 01:20:21', '0');
INSERT INTO `timetable` VALUES ('1801100145064988673', '00000003', '20200202', '00000001', '20000002', '6', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145064988674', '00000003', '20200202', '00000001', '30000001', '18', '1', '16', '1', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145064988675', '00000003', '20200203', '00000002', '20000003', '11', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145064988676', '00000003', '20200203', '00000002', '30000002', '20', '1', '16', '2', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145127903234', '00000003', '20200204', '00000003', '20000003', '3', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145127903235', '00000003', '20200204', '00000003', '30000003', '17', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145127903236', '00000003', '20200205', '00000004', '10000002', '12', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145127903237', '00000003', '20200205', '00000004', '30000003', '22', '17', '18', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145127903238', '00000003', '99999999', '00000005', '30000003', '7', '17', '18', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145127903239', '00000002', '20200202', '00000001', '20000001', '22', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145127903240', '00000002', '20200202', '00000001', '30000001', '2', '1', '16', '1', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012097', '00000002', '20200203', '00000002', '20000002', '14', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012098', '00000002', '20200203', '00000002', '30000001', '4', '1', '16', '2', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012099', '00000002', '20200204', '00000003', '20000001', '12', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012100', '00000002', '20200204', '00000003', '30000002', '24', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012101', '00000002', '20200205', '00000004', '10000002', '23', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012102', '00000002', '20200205', '00000004', '30000003', '5', '17', '18', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012103', '00000002', '99999999', '00000005', '30000002', '10', '17', '18', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012104', '00000001', '20200202', '00000001', '20000002', '0', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012105', '00000001', '20200202', '00000001', '30000001', '4', '1', '16', '1', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012106', '00000001', '20200203', '00000002', '20000002', '3', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012107', '00000001', '20200203', '00000002', '30000001', '10', '1', '16', '2', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145195012108', '00000001', '20200204', '00000003', '20000002', '22', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145257926657', '00000001', '20200204', '00000003', '30000003', '19', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145257926658', '00000001', '20200205', '00000004', '10000002', '13', '1', '16', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145257926659', '00000001', '20200205', '00000004', '30000001', '23', '17', '18', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100145257926660', '00000001', '99999999', '00000005', '30000003', '18', '17', '18', '0', '1800541251011895298', null, '2024-06-13 11:51:52', null, '2024-06-13 11:51:52', '0');
INSERT INTO `timetable` VALUES ('1801100277252673537', '00000003', '20200202', '00000001', '20000002', '16', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277252673538', '00000003', '20200202', '00000001', '30000003', '21', '1', '16', '1', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277252673539', '00000003', '20200203', '00000002', '20000001', '4', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277252673540', '00000003', '20200203', '00000002', '30000002', '22', '1', '16', '2', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782402', '00000003', '20200204', '00000003', '20000003', '11', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782403', '00000003', '20200204', '00000003', '30000002', '2', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782404', '00000003', '20200205', '00000004', '10000003', '15', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782405', '00000003', '20200205', '00000004', '30000002', '3', '17', '18', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782406', '00000003', '99999999', '00000005', '30000001', '9', '17', '18', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782407', '00000002', '20200202', '00000001', '20000002', '5', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782408', '00000002', '20200202', '00000001', '30000002', '7', '1', '16', '1', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782409', '00000002', '20200203', '00000002', '20000003', '6', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782410', '00000002', '20200203', '00000002', '30000003', '13', '1', '16', '2', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782411', '00000002', '20200204', '00000003', '20000001', '10', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782412', '00000002', '20200204', '00000003', '30000002', '14', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782413', '00000002', '20200205', '00000004', '10000003', '21', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782414', '00000002', '20200205', '00000004', '30000001', '4', '17', '18', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277319782415', '00000002', '99999999', '00000005', '30000002', '24', '17', '18', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277386891265', '00000001', '20200202', '00000001', '20000003', '18', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277386891266', '00000001', '20200202', '00000001', '30000001', '5', '1', '16', '1', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277386891267', '00000001', '20200203', '00000002', '20000003', '1', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277386891268', '00000001', '20200203', '00000002', '30000001', '14', '1', '16', '2', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277386891269', '00000001', '20200204', '00000003', '20000002', '23', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277386891270', '00000001', '20200204', '00000003', '30000002', '13', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277386891271', '00000001', '20200205', '00000004', '10000003', '19', '1', '16', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277386891272', '00000001', '20200205', '00000004', '30000003', '13', '17', '18', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801100277386891273', '00000001', '99999999', '00000005', '30000002', '8', '17', '18', '0', '1', null, '2024-06-13 11:52:23', null, '2024-06-13 11:52:23', '0');
INSERT INTO `timetable` VALUES ('1801135263007649793', '00000003', '20200202', '00000001', '20000001', '7', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263007649794', '00000003', '20200202', '00000001', '30000001', '18', '1', '16', '1', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263007649795', '00000003', '20200203', '00000002', '20000002', '20', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263007649796', '00000003', '20200203', '00000002', '30000001', '17', '1', '16', '2', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263007649797', '00000003', '20200204', '00000003', '20000002', '21', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263070564353', '00000003', '20200204', '00000003', '30000001', '16', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263070564354', '00000003', '20200205', '00000004', '10000001', '23', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263070564355', '00000003', '20200205', '00000004', '30000002', '2', '17', '18', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263070564356', '00000002', '20200202', '00000001', '20000002', '15', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263070564357', '00000002', '20200202', '00000001', '30000003', '11', '1', '16', '1', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263070564358', '00000002', '20200203', '00000002', '20000002', '22', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263070564359', '00000002', '20200203', '00000002', '30000003', '0', '1', '16', '2', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263070564360', '00000002', '20200204', '00000003', '20000002', '24', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263133478914', '00000002', '20200204', '00000003', '30000002', '18', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263133478915', '00000002', '20200205', '00000004', '10000002', '19', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263133478916', '00000002', '20200205', '00000004', '30000003', '6', '17', '18', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263133478917', '00000001', '20200202', '00000001', '20000002', '1', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263133478918', '00000001', '20200202', '00000001', '30000003', '9', '1', '16', '1', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263133478919', '00000001', '20200203', '00000002', '20000001', '21', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263200587777', '00000001', '20200203', '00000002', '30000001', '14', '1', '16', '2', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263200587778', '00000001', '20200204', '00000003', '20000003', '23', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263200587779', '00000001', '20200204', '00000003', '30000003', '12', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263200587780', '00000001', '20200205', '00000004', '10000003', '22', '1', '16', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801135263200587781', '00000001', '20200205', '00000004', '30000001', '24', '17', '18', '0', '1801134910191185922', null, '2024-06-13 14:11:24', null, '2024-06-13 14:11:24', '0');
INSERT INTO `timetable` VALUES ('1801139367771635713', '00000003', '20200202', '00000001', '20000003', '7', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367771635714', '00000003', '20200202', '00000001', '30000003', '5', '1', '16', '1', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367771635715', '00000003', '20200203', '00000002', '20000002', '6', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367771635716', '00000003', '20200203', '00000002', '30000003', '12', '1', '16', '2', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367771635717', '00000003', '20200204', '00000003', '20000002', '17', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367838744578', '00000003', '20200204', '00000003', '30000003', '19', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367838744579', '00000003', '20200205', '00000004', '10000003', '21', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367838744580', '00000003', '20200205', '00000004', '30000003', '16', '17', '18', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367838744581', '00000002', '20200202', '00000001', '20000003', '14', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367838744582', '00000002', '20200202', '00000001', '30000001', '8', '1', '16', '1', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367905853442', '00000002', '20200203', '00000002', '20000002', '22', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367905853443', '00000002', '20200203', '00000002', '30000003', '0', '1', '16', '2', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367905853444', '00000002', '20200204', '00000003', '20000001', '9', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367905853445', '00000002', '20200204', '00000003', '30000001', '4', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367905853446', '00000002', '20200205', '00000004', '10000002', '3', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367905853447', '00000002', '20200205', '00000004', '30000001', '7', '17', '18', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367972962305', '00000001', '20200202', '00000001', '20000001', '18', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367972962306', '00000001', '20200202', '00000001', '30000001', '13', '1', '16', '1', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367972962307', '00000001', '20200203', '00000002', '20000001', '21', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367972962308', '00000001', '20200203', '00000002', '30000002', '11', '1', '16', '2', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367972962309', '00000001', '20200204', '00000003', '20000003', '6', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139367972962310', '00000001', '20200204', '00000003', '30000001', '5', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139368040071170', '00000001', '20200205', '00000004', '10000001', '24', '1', '16', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');
INSERT INTO `timetable` VALUES ('1801139368040071171', '00000001', '20200205', '00000004', '30000001', '5', '17', '18', '0', '1801138268029321217', null, '2024-06-13 14:27:43', null, '2024-06-13 14:27:43', '0');

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
INSERT INTO `timetable_rehearsal` VALUES ('1801139367771635713', '00000003', '00000001', '20000003', '7', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367771635714', '00000003', '00000001', '30000003', '5', '1', '16', '1');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367771635715', '00000003', '00000002', '20000002', '6', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367771635716', '00000003', '00000002', '30000003', '12', '1', '16', '2');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367771635717', '00000003', '00000003', '20000002', '17', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367838744578', '00000003', '00000003', '30000003', '19', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367838744579', '00000003', '00000004', '10000003', '21', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367838744580', '00000003', '00000004', '30000003', '16', '17', '18', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367838744581', '00000002', '00000001', '20000003', '14', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367838744582', '00000002', '00000001', '30000001', '8', '1', '16', '1');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367905853442', '00000002', '00000002', '20000002', '24', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367905853443', '00000002', '00000002', '30000003', '0', '1', '16', '2');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367905853444', '00000002', '00000003', '20000001', '9', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367905853445', '00000002', '00000003', '30000001', '4', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367905853446', '00000002', '00000004', '10000002', '3', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367905853447', '00000002', '00000004', '30000001', '7', '17', '18', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367972962305', '00000001', '00000001', '20000001', '18', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367972962306', '00000001', '00000001', '30000001', '13', '1', '16', '1');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367972962307', '00000001', '00000002', '20000001', '21', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367972962308', '00000001', '00000002', '30000002', '11', '1', '16', '2');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367972962309', '00000001', '00000003', '20000003', '6', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139367972962310', '00000001', '00000003', '30000001', '5', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139368040071170', '00000001', '00000004', '10000001', '24', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1801139368040071171', '00000001', '00000004', '30000001', '5', '17', '18', '0');

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
INSERT INTO `user` VALUES ('2', '123', '1234', '1', null, '2024-05-19 22:40:38', null, '2024-06-13 14:09:00', '0');
INSERT INTO `user` VALUES ('3', 'admin1', '1234', '1', null, '2024-05-19 22:40:38', null, '2024-06-13 14:09:01', '0');


-- ----------------------------
-- 改进
-- ----------------------------
# 功能：连排
ALTER TABLE task ADD COLUMN duration varchar(1) DEFAULT NULL COMMENT '连排节次' AFTER biweekly;

ALTER TABLE timetable ADD COLUMN duration varchar(1) DEFAULT NULL COMMENT '连排节次' AFTER biweekly;
ALTER TABLE timetable_rehearsal ADD COLUMN duration varchar(1) DEFAULT NULL COMMENT '连排节次' AFTER biweekly;
update timetable set duration = '2';
update timetable_rehearsal set duration = '2';

# 功能：禁排
CREATE TABLE `exclusion_rule` (
                        `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
                        `course_no` varchar(8) NULL COMMENT '课程编号',
                        `class_no` varchar(8) NULL COMMENT '班级编号',
                        `teacher_no` varchar(8) NULL COMMENT '讲师编号',
                        `room_no` varchar(8) NULL COMMENT '教室编号',
                        `created_by` bigint(20) DEFAULT NULL,
                        `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                        `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='禁排规则';
ALTER TABLE exclusion_rule ADD COLUMN timeslot int DEFAULT NULL COMMENT '禁排节次' AFTER id;
ALTER TABLE exclusion_rule ADD COLUMN courseName varchar(36) DEFAULT NULL COMMENT '课程名' AFTER course_no;
ALTER TABLE exclusion_rule ADD COLUMN className varchar(36) DEFAULT NULL COMMENT '班级名' AFTER class_no;
ALTER TABLE exclusion_rule ADD COLUMN teacherName varchar(36) DEFAULT NULL COMMENT '教师名' AFTER teacher_no;
ALTER TABLE exclusion_rule ADD COLUMN roomName varchar(36) DEFAULT NULL COMMENT '教室名' AFTER room_no;

INSERT INTO `exclusion_rule`(timeslot,course_no,courseName) VALUES (4, '20200202','人工智能基础');
INSERT INTO `exclusion_rule`(timeslot,course_no,courseName) VALUES (9, '20200202','人工智能基础');
INSERT INTO `exclusion_rule`(timeslot,course_no,courseName) VALUES (14, '20200202','人工智能基础');
INSERT INTO `exclusion_rule`(timeslot,course_no,courseName) VALUES (19, '20200202','人工智能基础');
INSERT INTO `exclusion_rule`(timeslot,course_no,courseName) VALUES (24, '20200202','人工智能基础');
INSERT INTO `exclusion_rule`(timeslot,class_no,className) VALUES (17, '00000001','1班');
INSERT INTO `exclusion_rule`(timeslot,class_no,className) VALUES (18, '00000001','1班');
INSERT INTO `exclusion_rule`(timeslot,class_no,className) VALUES (19, '00000001','1班');
INSERT INTO `exclusion_rule`(timeslot,teacher_no,teacherName) VALUES (18, '00000001','张三');
INSERT INTO `exclusion_rule`(timeslot,teacher_no,teacherName) VALUES (19, '00000001','张三');
INSERT INTO `exclusion_rule`(timeslot,teacher_no,teacherName) VALUES (23, '00000001','张三');
INSERT INTO `exclusion_rule`(timeslot,teacher_no,teacherName) VALUES (24, '00000001','张三');
INSERT INTO `exclusion_rule`(timeslot,room_no,roomName) VALUES (0, '20000001','明理101');
INSERT INTO `exclusion_rule`(timeslot,room_no,roomName) VALUES (5, '20000001','明理101');
INSERT INTO `exclusion_rule`(timeslot,room_no,roomName) VALUES (10, '20000001','明理101');
INSERT INTO `exclusion_rule`(timeslot,room_no,roomName) VALUES (15, '20000001','明理101');
INSERT INTO `exclusion_rule`(timeslot,room_no,roomName) VALUES (20, '20000001','明理101');

# 功能：优先排
CREATE TABLE `priority_rule` (
                                 `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
                                 `timeslot` int NULL COMMENT '优先节次',
                                  `course_department_no` varchar(2) NULL COMMENT '开课院系编号',
                                  `task_attr` varchar(2) NULL COMMENT '课程性质编号 01必修课 02专业拓展课 03专业选修课 04公共必修课 05公共选修课 06公共基础课',
                                  `course_attr` varchar(2) NULL COMMENT '学时类型编号 01理论 02实验 03实践 04体育课',
                                  `created_by` bigint(20) DEFAULT NULL,
                                  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='优先排规则';

ALTER TABLE priority_rule ADD COLUMN course_department_name varchar(15) DEFAULT NULL COMMENT '开课院系' AFTER course_department_no;
ALTER TABLE priority_rule ADD COLUMN task_attr_name varchar(15) DEFAULT NULL COMMENT '课程性质' AFTER task_attr;
ALTER TABLE priority_rule ADD COLUMN course_attr_name varchar(15) DEFAULT NULL COMMENT '学时类型' AFTER course_attr;


INSERT INTO `priority_rule`(timeslot,course_department_no,course_department_name) VALUES (2, '06','信息智能工程学院');
INSERT INTO `priority_rule`(timeslot,course_department_no,course_department_name) VALUES (3, '06','信息智能工程学院');
INSERT INTO `priority_rule`(timeslot,course_department_no,course_department_name) VALUES (7, '06','信息智能工程学院');
INSERT INTO `priority_rule`(timeslot,course_department_no,course_department_name) VALUES (8, '06','信息智能工程学院');
INSERT INTO `priority_rule`(timeslot,course_department_no,course_department_name) VALUES (12, '06','信息智能工程学院');
INSERT INTO `priority_rule`(timeslot,course_department_no,course_department_name) VALUES (13, '06','信息智能工程学院');
INSERT INTO `priority_rule`(timeslot,course_department_no,course_department_name) VALUES (17, '06','信息智能工程学院');
INSERT INTO `priority_rule`(timeslot,course_department_no,course_department_name) VALUES (18, '06','信息智能工程学院');
INSERT INTO `priority_rule`(timeslot,course_department_no,course_department_name) VALUES (22, '06','信息智能工程学院');
INSERT INTO `priority_rule`(timeslot,course_department_no,course_department_name) VALUES (23, '06','信息智能工程学院');
INSERT INTO `priority_rule`(timeslot,task_attr,task_attr_name) VALUES (0, '01','必修课');
INSERT INTO `priority_rule`(timeslot,task_attr,task_attr_name) VALUES (1, '01','必修课');
INSERT INTO `priority_rule`(timeslot,task_attr,task_attr_name) VALUES (2, '01','必修课');
INSERT INTO `priority_rule`(timeslot,task_attr,task_attr_name) VALUES (3, '01','必修课');
INSERT INTO `priority_rule`(timeslot,task_attr,task_attr_name) VALUES (4, '01','必修课');
INSERT INTO `priority_rule`(timeslot,course_attr,course_attr_name) VALUES (20, '02','实验');
INSERT INTO `priority_rule`(timeslot,course_attr,course_attr_name) VALUES (21, '02','实验');
INSERT INTO `priority_rule`(timeslot,course_attr,course_attr_name) VALUES (22, '02','实验');
INSERT INTO `priority_rule`(timeslot,course_attr,course_attr_name) VALUES (23, '02','实验');
INSERT INTO `priority_rule`(timeslot,course_attr,course_attr_name) VALUES (24, '02','实验');


ALTER TABLE task ADD COLUMN course_department varchar(15) DEFAULT NULL COMMENT '开课院系' AFTER course_name;
ALTER TABLE task ADD COLUMN task_attr varchar(2) DEFAULT NULL COMMENT '课程性质编号 01必修课 02专业拓展课 03专业选修课 04公共必修课 05公共选修课 06公共基础课' AFTER course_name;
ALTER TABLE task ADD COLUMN course_attr_name varchar(5) DEFAULT NULL COMMENT '学时类型' AFTER course_no;
ALTER TABLE task ADD COLUMN task_attr_name varchar(5) DEFAULT NULL COMMENT '课程性质' AFTER course_name;
ALTER TABLE task ADD COLUMN course_department_no varchar(2) DEFAULT NULL COMMENT '开课院系编号' AFTER course_department;

# 功能：勾选部分课程先去排课
ALTER TABLE task ADD COLUMN if_scheduled varchar(1) DEFAULT NULL COMMENT '是否排课' AFTER id;
alter table task modify column if_scheduled boolean comment '是否排课 0不排课 1排课';

# 功能：排课教室是否启用固定教室
ALTER TABLE classes ADD COLUMN fix_room_no varchar(8) DEFAULT NULL COMMENT '固定教室编号' AFTER size;

ALTER TABLE task ADD COLUMN if_fix_room boolean DEFAULT NULL COMMENT '是否采用班级固定教室 0否 1是' AFTER id;
UPDATE `task` SET if_fix_room = false;

# 功能：教师特殊安排规则
CREATE TABLE `teacher_rule` (
                                 `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
                                 `teacher_no` varchar(8) NULL COMMENT '教师编号',
                                 `dTimeslot` int NULL COMMENT '每天最大节次',
                                 `mTimeslot` int NULL COMMENT '早上最大节次',
                                 `eTimeslot` int NULL COMMENT '下午最大节次',
                                 `created_by` bigint(20) DEFAULT NULL,
                                 `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='教师规则';

ALTER TABLE teacher_rule ADD COLUMN teacherName varchar(36) DEFAULT NULL COMMENT '教师名' AFTER teacher_no;


INSERT INTO `teacher_rule`(teacher_no,dTimeslot,mTimeslot,eTimeslot) VALUES ('00000001', 3,1,2);
INSERT INTO `teacher_rule`(teacher_no,dTimeslot,mTimeslot,eTimeslot) VALUES ('00000002', 3,1,2);

# 加入学分
ALTER TABLE task ADD COLUMN course_score int DEFAULT NULL COMMENT '学分' AFTER course_department_no;
# 加入所在教学区域
ALTER TABLE task ADD COLUMN room_area varchar(15) DEFAULT NULL COMMENT '所在教学区域' AFTER duration;


