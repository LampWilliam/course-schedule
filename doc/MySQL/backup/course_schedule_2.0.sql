/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : course_schedule

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2024-05-15 21:23:45
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
-- Records of classes
-- ----------------------------

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
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '01-101', '01-101', '01', '50', '01', '备注');
INSERT INTO `room` VALUES ('2', '01-102', '01-102', '01', '50', '01', null);
INSERT INTO `room` VALUES ('3', '01-103', '01-103', '01', '50', '01', null);
INSERT INTO `room` VALUES ('4', '01-104', '01-104', '01', '50', '01', null);
INSERT INTO `room` VALUES ('5', '01-105', '01-105', '01', '50', '01', null);
INSERT INTO `room` VALUES ('6', '01-201', '01-201', '01', '50', '01', null);
INSERT INTO `room` VALUES ('7', '01-202', '01-202', '01', '50', '01', null);
INSERT INTO `room` VALUES ('8', '01-203', '01-203', '01', '50', '01', null);
INSERT INTO `room` VALUES ('9', '01-204', '01-204', '01', '50', '01', null);
INSERT INTO `room` VALUES ('10', '01-205', '01-205', '01', '50', '01', null);
INSERT INTO `room` VALUES ('11', '01-301', '01-301', '01', '50', '01', null);
INSERT INTO `room` VALUES ('12', '01-302', '01-302', '01', '50', '01', null);
INSERT INTO `room` VALUES ('13', '01-303', '01-303', '01', '50', '01', null);
INSERT INTO `room` VALUES ('14', '01-304', '01-304', '01', '50', '01', null);
INSERT INTO `room` VALUES ('15', '01-305', '01-305', '01', '50', '01', null);
INSERT INTO `room` VALUES ('16', '01-401', '01-401', '01', '50', '01', null);
INSERT INTO `room` VALUES ('17', '01-402', '01-402', '01', '50', '01', null);
INSERT INTO `room` VALUES ('18', '01-403', '01-403', '01', '50', '01', null);
INSERT INTO `room` VALUES ('19', '01-404', '01-404', '01', '50', '01', null);
INSERT INTO `room` VALUES ('20', '01-405', '01-405', '01', '50', '01', null);
INSERT INTO `room` VALUES ('21', '01-501', '01-501', '01', '50', '01', null);
INSERT INTO `room` VALUES ('22', '01-502', '01-502', '01', '50', '01', null);
INSERT INTO `room` VALUES ('23', '01-503', '01-503', '01', '50', '01', null);
INSERT INTO `room` VALUES ('24', '01-504', '01-504', '01', '50', '01', null);
INSERT INTO `room` VALUES ('25', '01-505', '01-505', '01', '50', '01', null);
INSERT INTO `room` VALUES ('26', '02-101', '02-101', '02', '60', '01', null);
INSERT INTO `room` VALUES ('27', '02-102', '02-102', '02', '60', '01', null);
INSERT INTO `room` VALUES ('28', '02-103', '02-103', '02', '60', '01', null);
INSERT INTO `room` VALUES ('29', '02-104', '02-104', '02', '60', '01', null);
INSERT INTO `room` VALUES ('30', '02-105', '02-105', '02', '60', '01', null);
INSERT INTO `room` VALUES ('31', '02-201', '02-202', '02', '60', '01', null);
INSERT INTO `room` VALUES ('32', '02-202', '02-202', '02', '60', '01', null);
INSERT INTO `room` VALUES ('33', '02-203', '02-203', '02', '60', '01', null);
INSERT INTO `room` VALUES ('34', '02-204', '02-204', '02', '60', '01', null);
INSERT INTO `room` VALUES ('35', '02-205', '02-205', '02', '60', '01', null);
INSERT INTO `room` VALUES ('36', '02-301', '02-301', '02', '60', '01', null);
INSERT INTO `room` VALUES ('37', '02-302', '02-302', '02', '60', '01', null);
INSERT INTO `room` VALUES ('38', '02-303', '02-303', '02', '60', '01', null);
INSERT INTO `room` VALUES ('39', '02-304', '02-304', '02', '60', '01', null);
INSERT INTO `room` VALUES ('40', '02-305', '02-305', '02', '60', '01', null);
INSERT INTO `room` VALUES ('41', '02-401', '02-401', '02', '60', '01', null);
INSERT INTO `room` VALUES ('42', '02-402', '02-402', '02', '60', '01', null);
INSERT INTO `room` VALUES ('43', '02-403', '02-403', '02', '60', '01', null);
INSERT INTO `room` VALUES ('44', '02-404', '02-404', '02', '60', '01', null);
INSERT INTO `room` VALUES ('45', '02-405', '02-405', '02', '60', '01', null);
INSERT INTO `room` VALUES ('46', '03-101', '03-101', '03', '50', '01', null);
INSERT INTO `room` VALUES ('47', '03-102', '03-102', '03', '50', '01', null);
INSERT INTO `room` VALUES ('48', '03-103', '03-103', '03', '50', '01', null);
INSERT INTO `room` VALUES ('49', '03-104', '03-104', '03', '50', '01', null);
INSERT INTO `room` VALUES ('50', '03-105', '03-105', '03', '50', '01', null);
INSERT INTO `room` VALUES ('51', '03-201', '03-201', '03', '50', '01', null);
INSERT INTO `room` VALUES ('52', '03-202', '03-202', '03', '50', '01', null);
INSERT INTO `room` VALUES ('53', '03-203', '03-203', '03', '50', '01', null);
INSERT INTO `room` VALUES ('54', '03-204', '03-204', '03', '50', '01', null);
INSERT INTO `room` VALUES ('55', '03-205', '03-205', '03', '50', '01', null);
INSERT INTO `room` VALUES ('56', '03-301', '03-301', '03', '50', '01', null);
INSERT INTO `room` VALUES ('57', '03-302', '03-302', '03', '50', '01', null);
INSERT INTO `room` VALUES ('58', '03-303', '03-303', '03', '50', '01', null);
INSERT INTO `room` VALUES ('59', '03-304', '03-304', '03', '50', '01', null);
INSERT INTO `room` VALUES ('60', '03-305', '03-305', '03', '50', '01', null);
INSERT INTO `room` VALUES ('61', '03-401', '03-401', '03', '50', '01', null);
INSERT INTO `room` VALUES ('62', '03-402', '03-402', '03', '50', '01', null);
INSERT INTO `room` VALUES ('63', '03-403', '03-403', '03', '50', '01', null);
INSERT INTO `room` VALUES ('64', '03-404', '03-404', '03', '50', '01', null);
INSERT INTO `room` VALUES ('65', '03-405', '03-405', '03', '50', '01', null);
INSERT INTO `room` VALUES ('66', '03-501', '03-501', '03', '50', '01', null);
INSERT INTO `room` VALUES ('67', '03-502', '03-502', '03', '50', '01', null);
INSERT INTO `room` VALUES ('68', '03-503', '03-503', '03', '50', '01', '');
INSERT INTO `room` VALUES ('69', '03-504', '03-504', '03', '50', '01', null);
INSERT INTO `room` VALUES ('70', '03-505', '03-505', '03', '50', '01', null);
INSERT INTO `room` VALUES ('71', '04-101', '04-101', '04', '50', '01', null);
INSERT INTO `room` VALUES ('72', '04-102', '04-102', '04', '50', '01', null);
INSERT INTO `room` VALUES ('73', '04-103', '04-103', '04', '50', '01', null);
INSERT INTO `room` VALUES ('74', '04-104', '04-104', '04', '50', '01', null);
INSERT INTO `room` VALUES ('75', '04-105', '04-105', '04', '50', '01', null);
INSERT INTO `room` VALUES ('76', '04-201', '04-201', '04', '50', '01', null);
INSERT INTO `room` VALUES ('77', '04-202', '04-202', '04', '50', '01', null);
INSERT INTO `room` VALUES ('78', '04-203', '04-203', '04', '50', '01', null);
INSERT INTO `room` VALUES ('79', '04-204', '04-204', '04', '50', '01', null);
INSERT INTO `room` VALUES ('80', '04-205', '04-205', '04', '50', '01', null);
INSERT INTO `room` VALUES ('81', '04-301', '04-301', '04', '50', '01', null);
INSERT INTO `room` VALUES ('82', '04-302', '04-302', '04', '50', '01', null);
INSERT INTO `room` VALUES ('83', '04-303', '04-303', '04', '50', '01', null);
INSERT INTO `room` VALUES ('84', '04-304', '04-304', '04', '50', '01', null);
INSERT INTO `room` VALUES ('85', '04-305', '04-305', '04', '50', '01', null);
INSERT INTO `room` VALUES ('86', '04-401', '04-401', '04', '50', '01', null);
INSERT INTO `room` VALUES ('87', '04-402', '04-402', '04', '50', '01', null);
INSERT INTO `room` VALUES ('88', '04-403', '04-403', '04', '50', '01', null);
INSERT INTO `room` VALUES ('99', '04-404', '04-404', '04', '50', '01', null);
INSERT INTO `room` VALUES ('100', '04-405', '04-405', '04', '50', '01', null);
INSERT INTO `room` VALUES ('101', '04-501', '04-501', '04', '50', '01', null);
INSERT INTO `room` VALUES ('102', '04-502', '04-502', '04', '50', '01', null);
INSERT INTO `room` VALUES ('103', '04-503', '04-503', '04', '50', '01', null);
INSERT INTO `room` VALUES ('104', '04-504', '04-504', '04', '50', '01', null);
INSERT INTO `room` VALUES ('105', '04-505', '04-505', '04', '50', '01', null);
INSERT INTO `room` VALUES ('106', '05-101', '05-101', '05', '50', '01', null);
INSERT INTO `room` VALUES ('107', '05-102', '05-102', '05', '50', '01', null);
INSERT INTO `room` VALUES ('108', '05-103', '05-103', '05', '50', '01', null);
INSERT INTO `room` VALUES ('109', '05-104', '05-104', '05', '50', '01', null);
INSERT INTO `room` VALUES ('110', '05-105', '05-105', '05', '50', '01', null);
INSERT INTO `room` VALUES ('111', '05-201', '05-201', '05', '50', '01', null);
INSERT INTO `room` VALUES ('112', '05-202', '05-202', '05', '50', '01', null);
INSERT INTO `room` VALUES ('113', '05-203', '05-203', '05', '50', '01', null);
INSERT INTO `room` VALUES ('114', '05-204', '05-204', '05', '50', '01', null);
INSERT INTO `room` VALUES ('115', '05-205', '05-205', '05', '50', '01', null);
INSERT INTO `room` VALUES ('116', '05-301', '05-301', '05', '50', '01', null);
INSERT INTO `room` VALUES ('117', '05-302', '05-302', '05', '50', '01', null);
INSERT INTO `room` VALUES ('118', '05-303', '05-303', '05', '50', '01', null);
INSERT INTO `room` VALUES ('119', '05-304', '05-304', '05', '50', '01', null);
INSERT INTO `room` VALUES ('120', '05-305', '05-305', '05', '50', '01', null);
INSERT INTO `room` VALUES ('121', '05-401', '05-401', '05', '50', '01', '');
INSERT INTO `room` VALUES ('122', '05-402', '05-402', '05', '50', '01', null);
INSERT INTO `room` VALUES ('123', '05-403', '05-403', '05', '50', '01', null);
INSERT INTO `room` VALUES ('124', '05-404', '05-404', '05', '50', '01', null);
INSERT INTO `room` VALUES ('125', '05-405', '05-405', '05', '50', '01', null);
INSERT INTO `room` VALUES ('126', '05-501', '05-501', '05', '50', '01', null);
INSERT INTO `room` VALUES ('127', '05-502', '05-502', '05', '50', '01', null);
INSERT INTO `room` VALUES ('128', '05-503', '05-503', '05', '50', '01', null);
INSERT INTO `room` VALUES ('129', '05-504', '05-504', '05', '50', '01', null);
INSERT INTO `room` VALUES ('130', '05-505', '05-505', '05', '50', '01', null);
INSERT INTO `room` VALUES ('133', '12-101', '12-101', '12', '120', '04', '体育楼');
INSERT INTO `room` VALUES ('134', '12-102', '12-102', '12', '120', '04', null);
INSERT INTO `room` VALUES ('135', '12-103', '12-103', '12', '120', '04', null);
INSERT INTO `room` VALUES ('136', '12-104', '12-104', '12', '120', '04', null);
INSERT INTO `room` VALUES ('137', '12-201', '12-201', '12', '120', '04', null);
INSERT INTO `room` VALUES ('138', '12-202', '12-202', '12', '120', '04', null);
INSERT INTO `room` VALUES ('139', '12-203', '12-203', '12', '120', '04', null);
INSERT INTO `room` VALUES ('140', '12-204', '12-204', '12', '120', '04', null);
INSERT INTO `room` VALUES ('141', '08-101', '08-101', '08', '50', '03', '实验楼');
INSERT INTO `room` VALUES ('142', '08-102', '08-102', '08', '50', '03', null);
INSERT INTO `room` VALUES ('143', '08-103', '08-103', '08', '50', '03', null);
INSERT INTO `room` VALUES ('144', '08-104', '08-104', '08', '50', '03', null);
INSERT INTO `room` VALUES ('145', '08-105', '08-105', '08', '50', '03', null);
INSERT INTO `room` VALUES ('146', '08-201', '08-201', '08', '50', '03', null);
INSERT INTO `room` VALUES ('147', '08-202', '08-202', '08', '50', '03', null);
INSERT INTO `room` VALUES ('148', '08-203', '08-203', '08', '50', '03', null);
INSERT INTO `room` VALUES ('149', '08-204', '08-204', '08', '50', '03', null);
INSERT INTO `room` VALUES ('150', '08-205', '08-205', '08', '50', '03', null);
INSERT INTO `room` VALUES ('151', '08-301', '08-301', '08', '50', '03', null);
INSERT INTO `room` VALUES ('152', '08-302', '08-302', '08', '50', '03', null);
INSERT INTO `room` VALUES ('153', '08-303', '08-303', '08', '50', '03', null);
INSERT INTO `room` VALUES ('154', '08-304', '08-304', '08', '50', '03', null);
INSERT INTO `room` VALUES ('155', '08-305', '08-305', '08', '50', '03', null);

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
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '20200101', '100001', '高一语文必修1', '10010', '梁晓明', '01', '42', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('2', '20200101', '100033', '高一数学必修1', '10012', '李雪雪', '01', '37', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('3', '20200101', '100056', '高一英语必修1', '10013', '王小芳', '01', '39', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('4', '20200101', '100004', '高一物理1', '10025', '张德良', '02', '42', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('5', '20200101', '100014', '高一化学必修1', '10033', '韩云', '02', '40', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('6', '20200101', '100041', '高一思想政治必修1', '10045', '江大波', '02', '40', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('7', '20200101', '100021', '高一历史必修1', '10044', '吴天盛', '02', '40', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('8', '20200101', '100007', '高一地理必修1', '10043', '王杰', '02', '40', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('9', '20200101', '100027', '高一生物必修1：分子与细胞', '10042', '谭咏麟', '02', '40', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('10', '20200101', '100051', '体育课', '10041', '张杰', '04', '40', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('11', '20200101', '100066', '物理实验', '10025', '张德良', '03', '40', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('12', '20200101', '100067', '化学实验', '10023', '张靓颖', '03', '40', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('13', '20200102', '100001', '高一语文必修1', '10010', '梁晓明', '01', '42', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('14', '20200102', '100033', '高一数学必修1', '10012', '李雪雪', '01', '37', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('15', '20200102', '100056', '高一英语必修1', '10013', '王小芳', '01', '39', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('16', '20200102', '100004', '高一物理1', '10025', '张德良', '02', '42', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('17', '20200102', '100014', '高一化学必修1', '10033', '韩云', '02', '40', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('18', '20200102', '100041', '高一思想政治必修1', '10045', '江大波', '02', '40', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('19', '20200102', '100021', '高一历史必修1', '10044', '吴天盛', '02', '40', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('20', '20200102', '100007', '高一地理必修1', '10043', '王杰', '02', '40', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('21', '20200102', '100027', '高一生物必修1：分子与细胞', '10042', '谭咏麟', '02', '40', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('22', '20200102', '100051', '体育课', '10041', '张杰', '04', '40', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('23', '20200102', '100066', '物理实验', '10025', '张德良', '03', '40', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('24', '20200102', '100067', '化学实验', '10023', '张靓颖', '03', '40', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('25', '20200103', '100001', '高一语文必修1', '10034', '韦雪琪', '01', '45', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('26', '20200103', '100003', '高一数学1', '10035', '张三封', '01', '45', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('27', '20200103', '100056', '高一英语必修1', '10029', '郑小红', '01', '45', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('28', '20200103', '100004', '高一物理1', '10025', '张德良', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('29', '20200103', '100015', '高一化学必修2', '10037', '莫小新', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('30', '20200103', '100028', '高一生物必修2：遗传与进化', '10038', '甘楠', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('31', '20200103', '100022', '高一历史必修2', '10036', '胡小小', '02', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('32', '20200103', '100008', '高一地理必修2', '10031', '张小龙', '02', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('33', '20200103', '100042', '高一思想政治必修2', '10040', '夏紫若', '02', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('34', '20200103', '100062', '信息与技术1', '10039', '江晓东', '03', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('35', '20200103', '100051', '体育课', '10041', '张杰', '04', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('36', '20200103', '100066', '物理实验', '10025', '张德良', '03', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('37', '20200103', '100067', '化学实验', '10023', '张靓颖', '03', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('38', '20200104', '100001', '高一语文必修1', '10029', '郑小红', '01', '45', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('39', '20200104', '100003', '高一数学1', '10033', '韩云', '01', '45', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('40', '20200104', '100056', '高一英语必修1', '10020', '胡冬梅', '01', '45', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('41', '20200104', '100004', '高一物理1', '10035', '张三封', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('42', '20200104', '100015', '高一化学必修2', '10039', '江晓东', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('43', '20200104', '100028', '高一生物必修2：遗传与进化', '10038', '甘楠', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('44', '20200104', '100022', '高一历史必修2', '10036', '胡小小', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('45', '20200104', '100008', '高一地理必修2', '10031', '张小龙', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('46', '20200104', '100042', '高一思想政治必修2', '10040', '夏紫若', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('47', '20200104', '100062', '信息与技术1', '10039', '江晓东', '03', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('48', '20200104', '100051', '体育课', '10041', '张杰', '04', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('49', '20200104', '100066', '物理实验', '10025', '张德良', '03', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('50', '20200104', '100067', '化学实验', '10023', '张靓颖', '03', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('51', '20200105', '100001', '高一语文必修1', '10029', '郑小红', '01', '45', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('52', '20200105', '100003', '高一数学1', '10033', '韩云', '01', '45', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('53', '20200105', '100056', '高一英语必修1', '10020', '胡冬梅', '01', '45', '20', '6', null, null, null, null, null);
INSERT INTO `task` VALUES ('54', '20200105', '100004', '高一物理1', '10035', '张三封', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('55', '20200105', '100015', '高一化学必修2', '10039', '江晓东', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('56', '20200105', '100028', '高一生物必修2：遗传与进化', '10038', '甘楠', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('57', '20200105', '100022', '高一历史必修2', '10036', '胡小小', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('58', '20200105', '100008', '高一地理必修2', '10031', '张小龙', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('59', '20200105', '100042', '高一思想政治必修2', '10040', '夏紫若', '02', '45', '20', '4', null, null, null, null, null);
INSERT INTO `task` VALUES ('60', '20200105', '100062', '信息与技术1', '10039', '江晓东', '03', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('61', '20200105', '100051', '体育课', '10041', '张杰', '04', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('62', '20200105', '100066', '物理实验', '10025', '张德良', '03', '45', '20', '2', null, null, null, null, null);
INSERT INTO `task` VALUES ('63', '20200105', '100067', '化学实验', '10023', '张靓颖', '03', '45', '20', '2', null, null, null, null, null);

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
-- Records of timetable
-- ----------------------------
INSERT INTO `timetable` VALUES ('1', '20200105', '100001', '10029', '01-401', '06', null);
INSERT INTO `timetable` VALUES ('2', '20200105', '100001', '10029', '01-202', '16', null);
INSERT INTO `timetable` VALUES ('3', '20200105', '100001', '10029', '01-402', '14', null);
INSERT INTO `timetable` VALUES ('4', '20200105', '100003', '10033', '01-304', '05', null);
INSERT INTO `timetable` VALUES ('5', '20200105', '100003', '10033', '01-403', '22', null);
INSERT INTO `timetable` VALUES ('6', '20200105', '100003', '10033', '01-104', '10', null);
INSERT INTO `timetable` VALUES ('7', '20200105', '100056', '10020', '01-104', '04', null);
INSERT INTO `timetable` VALUES ('8', '20200105', '100056', '10020', '01-302', '21', null);
INSERT INTO `timetable` VALUES ('9', '20200105', '100056', '10020', '01-103', '12', null);
INSERT INTO `timetable` VALUES ('10', '20200105', '100004', '10035', '01-405', '07', null);
INSERT INTO `timetable` VALUES ('11', '20200105', '100004', '10035', '01-305', '18', null);
INSERT INTO `timetable` VALUES ('12', '20200105', '100015', '10039', '01-203', '25', null);
INSERT INTO `timetable` VALUES ('13', '20200105', '100015', '10039', '01-105', '03', null);
INSERT INTO `timetable` VALUES ('14', '20200105', '100028', '10038', '01-402', '19', null);
INSERT INTO `timetable` VALUES ('15', '20200105', '100028', '10038', '01-303', '24', null);
INSERT INTO `timetable` VALUES ('16', '20200105', '100022', '10036', '01-502', '02', null);
INSERT INTO `timetable` VALUES ('17', '20200105', '100022', '10036', '01-203', '20', null);
INSERT INTO `timetable` VALUES ('18', '20200105', '100008', '10031', '01-505', '13', null);
INSERT INTO `timetable` VALUES ('19', '20200105', '100008', '10031', '01-103', '08', null);
INSERT INTO `timetable` VALUES ('20', '20200105', '100042', '10040', '01-501', '23', null);
INSERT INTO `timetable` VALUES ('21', '20200105', '100042', '10040', '01-502', '17', null);
INSERT INTO `timetable` VALUES ('22', '20200105', '100062', '10039', '08-304', '09', null);
INSERT INTO `timetable` VALUES ('23', '20200105', '100051', '10041', '12-201', '01', null);
INSERT INTO `timetable` VALUES ('24', '20200105', '100066', '10025', '08-304', '15', null);
INSERT INTO `timetable` VALUES ('25', '20200105', '100067', '10023', '08-302', '11', null);
INSERT INTO `timetable` VALUES ('26', '20200104', '100001', '10029', '01-503', '22', null);
INSERT INTO `timetable` VALUES ('27', '20200104', '100001', '10029', '01-103', '10', null);
INSERT INTO `timetable` VALUES ('28', '20200104', '100001', '10029', '01-204', '03', null);
INSERT INTO `timetable` VALUES ('29', '20200104', '100003', '10033', '01-503', '21', null);
INSERT INTO `timetable` VALUES ('30', '20200104', '100003', '10033', '01-201', '23', null);
INSERT INTO `timetable` VALUES ('31', '20200104', '100003', '10033', '01-205', '25', null);
INSERT INTO `timetable` VALUES ('32', '20200104', '100056', '10020', '01-405', '11', null);
INSERT INTO `timetable` VALUES ('33', '20200104', '100056', '10020', '01-101', '19', null);
INSERT INTO `timetable` VALUES ('34', '20200104', '100056', '10020', '01-202', '08', null);
INSERT INTO `timetable` VALUES ('35', '20200104', '100004', '10035', '01-101', '24', null);
INSERT INTO `timetable` VALUES ('36', '20200104', '100004', '10035', '01-105', '17', null);
INSERT INTO `timetable` VALUES ('37', '20200104', '100015', '10039', '01-404', '13', null);
INSERT INTO `timetable` VALUES ('38', '20200104', '100015', '10039', '01-504', '05', null);
INSERT INTO `timetable` VALUES ('39', '20200104', '100028', '10038', '01-101', '15', null);
INSERT INTO `timetable` VALUES ('40', '20200104', '100028', '10038', '01-304', '01', null);
INSERT INTO `timetable` VALUES ('41', '20200104', '100022', '10036', '01-505', '07', null);
INSERT INTO `timetable` VALUES ('42', '20200104', '100022', '10036', '01-403', '06', null);
INSERT INTO `timetable` VALUES ('43', '20200104', '100008', '10031', '01-205', '04', null);
INSERT INTO `timetable` VALUES ('44', '20200104', '100008', '10031', '01-403', '12', null);
INSERT INTO `timetable` VALUES ('45', '20200104', '100042', '10040', '01-203', '02', null);
INSERT INTO `timetable` VALUES ('46', '20200104', '100042', '10040', '01-405', '18', null);
INSERT INTO `timetable` VALUES ('47', '20200104', '100062', '10039', '08-203', '20', null);
INSERT INTO `timetable` VALUES ('48', '20200104', '100051', '10041', '12-204', '16', null);
INSERT INTO `timetable` VALUES ('49', '20200104', '100066', '10025', '08-303', '14', null);
INSERT INTO `timetable` VALUES ('50', '20200104', '100067', '10023', '08-205', '09', null);
INSERT INTO `timetable` VALUES ('51', '20200103', '100051', '10041', '12-102', '15', null);
INSERT INTO `timetable` VALUES ('52', '20200103', '100001', '10034', '01-301', '06', null);
INSERT INTO `timetable` VALUES ('53', '20200103', '100001', '10034', '01-104', '17', null);
INSERT INTO `timetable` VALUES ('54', '20200103', '100001', '10034', '01-303', '21', null);
INSERT INTO `timetable` VALUES ('55', '20200103', '100003', '10035', '01-402', '16', null);
INSERT INTO `timetable` VALUES ('56', '20200103', '100003', '10035', '01-301', '08', null);
INSERT INTO `timetable` VALUES ('57', '20200103', '100003', '10035', '01-202', '13', null);
INSERT INTO `timetable` VALUES ('58', '20200103', '100056', '10029', '01-501', '24', null);
INSERT INTO `timetable` VALUES ('59', '20200103', '100056', '10029', '01-503', '07', null);
INSERT INTO `timetable` VALUES ('60', '20200103', '100056', '10029', '01-304', '02', null);
INSERT INTO `timetable` VALUES ('61', '20200103', '100004', '10025', '01-103', '18', null);
INSERT INTO `timetable` VALUES ('62', '20200103', '100004', '10025', '01-303', '12', null);
INSERT INTO `timetable` VALUES ('63', '20200103', '100015', '10037', '01-202', '05', null);
INSERT INTO `timetable` VALUES ('64', '20200103', '100015', '10037', '01-505', '25', null);
INSERT INTO `timetable` VALUES ('65', '20200103', '100028', '10038', '01-405', '23', null);
INSERT INTO `timetable` VALUES ('66', '20200103', '100028', '10038', '01-201', '14', null);
INSERT INTO `timetable` VALUES ('67', '20200103', '100022', '10036', '01-403', '04', null);
INSERT INTO `timetable` VALUES ('68', '20200103', '100008', '10031', '01-104', '20', null);
INSERT INTO `timetable` VALUES ('69', '20200103', '100042', '10040', '01-504', '03', null);
INSERT INTO `timetable` VALUES ('70', '20200103', '100062', '10039', '08-202', '19', null);
INSERT INTO `timetable` VALUES ('71', '20200103', '100066', '10025', '08-201', '09', null);
INSERT INTO `timetable` VALUES ('72', '20200103', '100067', '10023', '08-301', '22', null);
INSERT INTO `timetable` VALUES ('73', '20200102', '100051', '10041', '12-204', '19', null);
INSERT INTO `timetable` VALUES ('74', '20200102', '100066', '10025', '08-205', '13', null);
INSERT INTO `timetable` VALUES ('75', '20200102', '100067', '10023', '08-302', '08', null);
INSERT INTO `timetable` VALUES ('76', '20200102', '100001', '10010', '01-401', '14', null);
INSERT INTO `timetable` VALUES ('77', '20200102', '100001', '10010', '01-203', '07', null);
INSERT INTO `timetable` VALUES ('78', '20200102', '100001', '10010', '01-203', '21', null);
INSERT INTO `timetable` VALUES ('79', '20200102', '100033', '10012', '01-305', '12', null);
INSERT INTO `timetable` VALUES ('80', '20200102', '100033', '10012', '01-504', '04', null);
INSERT INTO `timetable` VALUES ('81', '20200102', '100033', '10012', '01-401', '11', null);
INSERT INTO `timetable` VALUES ('82', '20200102', '100056', '10013', '01-202', '01', null);
INSERT INTO `timetable` VALUES ('83', '20200102', '100056', '10013', '01-104', '06', null);
INSERT INTO `timetable` VALUES ('84', '20200102', '100004', '10025', '01-204', '25', null);
INSERT INTO `timetable` VALUES ('85', '20200102', '100014', '10033', '01-302', '17', null);
INSERT INTO `timetable` VALUES ('86', '20200102', '100041', '10045', '01-201', '18', null);
INSERT INTO `timetable` VALUES ('87', '20200102', '100041', '10045', '01-305', '15', null);
INSERT INTO `timetable` VALUES ('88', '20200102', '100021', '10044', '01-302', '09', null);
INSERT INTO `timetable` VALUES ('89', '20200102', '100021', '10044', '01-204', '20', null);
INSERT INTO `timetable` VALUES ('90', '20200102', '100007', '10043', '01-305', '05', null);
INSERT INTO `timetable` VALUES ('91', '20200102', '100007', '10043', '01-105', '22', null);
INSERT INTO `timetable` VALUES ('92', '20200102', '100027', '10042', '01-205', '03', null);
INSERT INTO `timetable` VALUES ('93', '20200101', '100051', '10041', '12-201', '05', null);
INSERT INTO `timetable` VALUES ('94', '20200101', '100066', '10025', '08-302', '06', null);
INSERT INTO `timetable` VALUES ('95', '20200101', '100066', '10025', '08-205', '02', null);
INSERT INTO `timetable` VALUES ('96', '20200101', '100067', '10023', '08-303', '19', null);
INSERT INTO `timetable` VALUES ('97', '20200101', '100001', '10010', '01-205', '23', null);
INSERT INTO `timetable` VALUES ('98', '20200101', '100001', '10010', '01-205', '17', null);
INSERT INTO `timetable` VALUES ('99', '20200101', '100033', '10012', '01-504', '22', null);
INSERT INTO `timetable` VALUES ('100', '20200101', '100033', '10012', '01-201', '01', null);
INSERT INTO `timetable` VALUES ('101', '20200101', '100033', '10012', '01-502', '18', null);
INSERT INTO `timetable` VALUES ('102', '20200101', '100056', '10013', '01-105', '16', null);
INSERT INTO `timetable` VALUES ('103', '20200101', '100056', '10013', '01-102', '07', null);
INSERT INTO `timetable` VALUES ('104', '20200101', '100056', '10013', '01-502', '09', null);
INSERT INTO `timetable` VALUES ('105', '20200101', '100004', '10025', '01-501', '08', null);
INSERT INTO `timetable` VALUES ('106', '20200101', '100004', '10025', '01-305', '03', null);
INSERT INTO `timetable` VALUES ('107', '20200101', '100004', '10025', '01-203', '10', null);
INSERT INTO `timetable` VALUES ('108', '20200101', '100014', '10033', '01-203', '24', null);
INSERT INTO `timetable` VALUES ('109', '20200101', '100014', '10033', '01-302', '13', null);
INSERT INTO `timetable` VALUES ('110', '20200101', '100041', '10045', '01-503', '20', null);
INSERT INTO `timetable` VALUES ('111', '20200101', '100041', '10045', '01-501', '25', null);
INSERT INTO `timetable` VALUES ('112', '20200101', '100021', '10044', '01-405', '12', null);
INSERT INTO `timetable` VALUES ('113', '20200101', '100021', '10044', '01-404', '04', null);
INSERT INTO `timetable` VALUES ('114', '20200101', '100007', '10043', '01-305', '11', null);
INSERT INTO `timetable` VALUES ('115', '20200101', '100007', '10043', '01-503', '15', null);
INSERT INTO `timetable` VALUES ('116', '20200101', '100027', '10042', '01-403', '14', null);
INSERT INTO `timetable` VALUES ('117', '20200101', '100027', '10042', '01-101', '21', null);

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
