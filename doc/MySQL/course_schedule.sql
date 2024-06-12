/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : course_schedule

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2024-06-13 01:29:37
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
INSERT INTO `classes` VALUES ('1800559403364016130', '00000001', '1班', '50', null, '2024-06-12 00:03:09', null, '2024-06-12 00:03:09', '0');
INSERT INTO `classes` VALUES ('1800559403364016131', '00000002', '2班', '50', null, '2024-06-12 00:03:09', null, '2024-06-12 00:03:09', '0');
INSERT INTO `classes` VALUES ('1800559403364016132', '00000003', '3班', '50', null, '2024-06-12 00:03:09', null, '2024-06-12 00:03:09', '0');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `course_no` varchar(8) NOT NULL COMMENT '课程编号',
  `course_name` varchar(36) NOT NULL COMMENT '课程名',
  `course_attr` varchar(2) NOT NULL COMMENT '课程属性',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '20200101', '高数', '01', null, '2024-05-29 11:28:01', null, '0');
INSERT INTO `course` VALUES ('2', '20200201', 'Java', '01', null, '2024-06-08 17:35:33', null, '0');
INSERT INTO `course` VALUES ('3', '20200102', '大学英语', '01', null, '2024-06-11 14:02:11', '2024-06-11 14:02:11', '0');
INSERT INTO `course` VALUES ('4', '20200202', '人工智能基础', '01', null, '2024-06-11 23:52:57', '2024-06-11 23:52:57', '0');
INSERT INTO `course` VALUES ('5', '20200202', '人工智能基础', '02', null, '2024-06-11 23:53:34', '2024-06-11 23:53:34', '0');
INSERT INTO `course` VALUES ('6', '20200203', '操作系统', '01', null, '2024-06-11 23:54:01', '2024-06-11 23:54:01', '0');
INSERT INTO `course` VALUES ('7', '20200203', '操作系统', '02', null, '2024-06-11 23:54:22', '2024-06-11 23:54:22', '0');
INSERT INTO `course` VALUES ('8', '20200204', 'Python程序语言设计', '01', null, '2024-06-11 23:54:43', '2024-06-11 23:54:43', '0');
INSERT INTO `course` VALUES ('9', '20200204', 'Python程序语言设计', '02', null, '2024-06-11 23:55:03', '2024-06-11 23:55:03', '0');
INSERT INTO `course` VALUES ('10', '20200205', 'J2EE架构与程序设计', '01', null, '2024-06-11 23:55:23', '2024-06-11 23:55:23', '0');
INSERT INTO `course` VALUES ('11', '20200205', 'J2EE架构与程序设计', '02', null, '2024-06-11 23:55:39', '2024-06-11 23:55:39', '0');

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
INSERT INTO `room` VALUES ('1800559474105147393', '20000001', '明理101', '02', '明理楼', '50', '01', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474105147394', '20000002', '明理102', '02', '明理楼', '50', '01', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474172256257', '20000003', '明理103', '02', '明理楼', '50', '01', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474172256258', '20000004', '明理104', '02', '明理楼', '50', '02', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474172256259', '20000005', '明理105', '02', '明理楼', '50', '02', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474172256260', '20000006', '明理106', '02', '明理楼', '50', '02', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474172256261', '10000001', '精工101', '01', '精工楼', '50', '01', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474235170817', '10000002', '精工102', '01', '精工楼', '50', '01', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474235170818', '10000003', '精工103', '01', '精工楼', '50', '01', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474235170819', '10000004', '精工104', '01', '精工楼', '50', '02', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474235170820', '10000005', '精工105', '01', '精工楼', '50', '02', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474235170821', '10000006', '精工106', '01', '精工楼', '50', '02', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474298085378', '30000001', '专业机房101', '03', '逸夫楼', '50', '02', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474298085379', '30000002', '专业机房102', '03', '逸夫楼', '50', '02', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');
INSERT INTO `room` VALUES ('1800559474298085380', '30000003', '专业机房103', '03', '逸夫楼', '50', '02', null, null, '2024-06-12 00:03:26', null, '2024-06-12 00:03:26', '0');

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
INSERT INTO `semester` VALUES ('1800541251011895298', '花花幼儿园第1学期', '20', '2024-06-09', '2024-10-27', '0', '1', '2024-06-11 22:51:01', null, '2024-06-13 01:28:49', '0');
INSERT INTO `semester` VALUES ('1800886939994308610', '天才学院的第1学期', '20', '2024-06-02', '2024-10-20', '0', '1', '2024-06-12 21:44:40', null, '2024-06-12 21:44:40', '0');

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
INSERT INTO `task` VALUES ('1800935922401824769', '00000001', '00000001', '20200202', '01', '01', '16', '0', '02', '1', '16', '2', '1班', '张三', '人工智能基础', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922464739330', '00000001', '00000001', '20200202', '02', '01', '16', '1', '03', '1', '16', '2', '1班', '张三', '人工智能基础', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922464739331', '00000001', '00000002', '20200203', '01', '01', '16', '0', '02', '2', '16', '2', '1班', '李四', '操作系统', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922464739332', '00000001', '00000002', '20200203', '02', '01', '16', '2', '03', '1', '16', '2', '1班', '李四', '操作系统', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922464739333', '00000001', '00000003', '20200204', '01', '01', '16', '0', '02', '1', '16', '2', '1班', '王五', 'Python程序语言设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922464739334', '00000001', '00000003', '20200204', '02', '01', '16', '0', '03', '1', '16', '2', '1班', '王五', 'Python程序语言设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922531848193', '00000001', '00000004', '20200205', '01', '01', '16', '0', '01', '1', '16', '2', '1班', '赵六', 'J2EE架构与程序设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922531848194', '00000001', '00000004', '20200205', '02', '17', '18', '0', '03', '1', '16', '2', '1班', '赵六', 'J2EE架构与程序设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922531848195', '00000002', '00000001', '20200202', '01', '01', '16', '0', '02', '1', '16', '2', '2班', '张三', '人工智能基础', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922531848196', '00000002', '00000001', '20200202', '02', '01', '16', '1', '03', '1', '16', '2', '2班', '张三', '人工智能基础', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922531848197', '00000002', '00000002', '20200203', '01', '01', '16', '0', '02', '2', '16', '2', '2班', '李四', '操作系统', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922531848198', '00000002', '00000002', '20200203', '02', '01', '16', '2', '03', '1', '16', '2', '2班', '李四', '操作系统', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922603151361', '00000002', '00000003', '20200204', '01', '01', '16', '0', '02', '1', '16', '2', '2班', '王五', 'Python程序语言设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922603151362', '00000002', '00000003', '20200204', '02', '01', '16', '0', '03', '1', '16', '2', '2班', '王五', 'Python程序语言设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922603151363', '00000002', '00000004', '20200205', '01', '01', '16', '0', '01', '1', '16', '2', '2班', '赵六', 'J2EE架构与程序设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922603151364', '00000002', '00000004', '20200205', '02', '17', '18', '0', '03', '1', '16', '2', '2班', '赵六', 'J2EE架构与程序设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922670260226', '00000003', '00000001', '20200202', '01', '01', '16', '0', '02', '1', '16', '2', '3班', '张三', '人工智能基础', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922670260227', '00000003', '00000001', '20200202', '02', '01', '16', '1', '03', '1', '16', '2', '3班', '张三', '人工智能基础', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922670260228', '00000003', '00000002', '20200203', '01', '01', '16', '0', '02', '2', '16', '2', '3班', '李四', '操作系统', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922670260229', '00000003', '00000002', '20200203', '02', '01', '16', '2', '03', '1', '16', '2', '3班', '李四', '操作系统', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922737369090', '00000003', '00000003', '20200204', '01', '01', '16', '0', '02', '1', '16', '2', '3班', '王五', 'Python程序语言设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922737369091', '00000003', '00000003', '20200204', '02', '01', '16', '0', '03', '1', '16', '2', '3班', '王五', 'Python程序语言设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922737369092', '00000003', '00000004', '20200205', '01', '01', '16', '0', '01', '1', '16', '2', '3班', '赵六', 'J2EE架构与程序设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');
INSERT INTO `task` VALUES ('1800935922737369093', '00000003', '00000004', '20200205', '02', '17', '18', '0', '03', '1', '16', '2', '3班', '赵六', 'J2EE架构与程序设计', null, '2024-06-13 00:59:18', null, '2024-06-13 00:59:18', '0');

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
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '00000001', '张三', null, '2024-06-09 15:43:33', null, '2024-06-09 15:43:33', '0');
INSERT INTO `teacher` VALUES ('2', '00000002', '李四', null, '2024-06-09 15:45:13', null, '2024-06-09 15:45:13', '0');
INSERT INTO `teacher` VALUES ('3', '00000003', '王五', null, '2024-06-12 00:04:36', null, '2024-06-12 00:04:36', '0');
INSERT INTO `teacher` VALUES ('4', '00000004', '赵六', null, '2024-06-12 00:04:48', null, '2024-06-12 00:04:48', '0');

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
INSERT INTO `timetable` VALUES ('1800936115264311298', '00000003', '20200202', '00000001', '20000001', '18', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115264311299', '00000003', '20200202', '00000001', '30000003', '11', '1', '16', '1', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115264311300', '00000003', '20200203', '00000002', '20000002', '8', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115264311301', '00000003', '20200203', '00000002', '30000001', '2', '1', '16', '2', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115264311302', '00000003', '20200204', '00000003', '20000003', '3', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115264311303', '00000003', '20200204', '00000003', '30000001', '17', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115264311304', '00000003', '20200205', '00000004', '10000001', '7', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115264311305', '00000003', '20200205', '00000004', '30000002', '14', '17', '18', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115264311306', '00000002', '20200202', '00000001', '20000003', '8', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115264311307', '00000002', '20200202', '00000001', '30000001', '10', '1', '16', '1', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614465', '00000002', '20200203', '00000002', '20000001', '5', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614466', '00000002', '20200203', '00000002', '30000003', '23', '1', '16', '2', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614467', '00000002', '20200204', '00000003', '20000002', '0', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614468', '00000002', '20200204', '00000003', '30000002', '1', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614469', '00000002', '20200205', '00000004', '10000001', '12', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614470', '00000002', '20200205', '00000004', '30000001', '23', '17', '18', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614471', '00000001', '20200202', '00000001', '20000001', '23', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614472', '00000001', '20200202', '00000001', '30000003', '24', '1', '16', '1', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614473', '00000001', '20200203', '00000002', '20000003', '9', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614474', '00000001', '20200203', '00000002', '30000003', '24', '1', '16', '2', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614475', '00000001', '20200204', '00000003', '20000002', '19', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614476', '00000001', '20200204', '00000003', '30000001', '21', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115335614477', '00000001', '20200205', '00000004', '10000003', '2', '1', '16', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
INSERT INTO `timetable` VALUES ('1800936115402723329', '00000001', '20200205', '00000004', '30000003', '16', '17', '18', '0', '1', null, '2024-06-13 01:00:04', null, '2024-06-13 01:00:04', '0');
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
INSERT INTO `timetable_rehearsal` VALUES ('1800936115264311298', '00000003', '00000001', '20000001', '18', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115264311299', '00000003', '00000001', '30000003', '11', '1', '16', '1');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115264311300', '00000003', '00000002', '20000002', '8', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115264311301', '00000003', '00000002', '30000001', '2', '1', '16', '2');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115264311302', '00000003', '00000003', '20000003', '3', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115264311303', '00000003', '00000003', '30000001', '17', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115264311304', '00000003', '00000004', '10000001', '7', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115264311305', '00000003', '00000004', '30000002', '14', '17', '18', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115264311306', '00000002', '00000001', '20000003', '8', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115264311307', '00000002', '00000001', '30000001', '10', '1', '16', '1');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614465', '00000002', '00000002', '20000001', '5', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614466', '00000002', '00000002', '30000003', '23', '1', '16', '2');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614467', '00000002', '00000003', '20000002', '0', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614468', '00000002', '00000003', '30000002', '1', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614469', '00000002', '00000004', '10000001', '12', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614470', '00000002', '00000004', '30000001', '23', '17', '18', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614471', '00000001', '00000001', '20000001', '23', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614472', '00000001', '00000001', '30000003', '24', '1', '16', '1');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614473', '00000001', '00000002', '20000003', '9', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614474', '00000001', '00000002', '30000003', '24', '1', '16', '2');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614475', '00000001', '00000003', '20000002', '19', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614476', '00000001', '00000003', '30000001', '21', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115335614477', '00000001', '00000004', '10000003', '2', '1', '16', '0');
INSERT INTO `timetable_rehearsal` VALUES ('1800936115402723329', '00000001', '00000004', '30000003', '16', '17', '18', '0');

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
