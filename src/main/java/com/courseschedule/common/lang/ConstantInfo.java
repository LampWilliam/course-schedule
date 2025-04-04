package com.courseschedule.common.lang;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 定义属性,方便切割编码的时候选择
 * @description 可以替换为接口，接口天然带 static final
 */
public interface ConstantInfo {

    // 普通教室 01
    String NORMAL_CLASS_ROOM = "01";

    // 班级编号 位
    String CLASS_NO = "class_no";

    // 教师编号  位
    String TEACHER_NO = "teacher_no";

    // 课程编号  位
    String COURSE_NO = "course_no";

    // 课程属性 位
    String COURSE_ATTR = "course_attr";

    // 教室编号 位
    String ROOM_NO = "classroom_no";

    // 上课时间2位
    String TIMESLOT = "timeslot";

    // 开课学期
    String SEMESTER = "semester";

    // 教学区域
    String AREA_NO = "area_no";

    String START_WEEK = "start_week";
    String END_WEEK = "end_week";
    String BIWEEKLY = "biweekly";
    String DURATION = "duration";
    String IFFIXROOM = "ifFixRoom";
    String COURSEATTR = "courseAttr";
    String TASKATTR = "taskAttr";
    String COURSEDEPARTMENTNO = "courseDepartmentNo";
    String CLASS_COUNT = "class_count";
    String IS_FIX = "is_fix";

    // 默认课程的编码
    String DEFAULT_TIMESLOT = "00";

    // 设置各种类型的课程的适应度(码值)
    // 理论课
    String THEORY_COURSE = "01";

    // 实验课
    String LAB_COURSE = "02";

    // 实践课
    String PRACTICE_COURSE = "03";

    // 体育课
    String PHYSICAL_COURSE = "04";

    // 音乐课
    String MUSIC_COURSE = "05";

    // 舞蹈课
    String DANCE_COURSE = "06";

    // 绘画课
    String TECHNOLOGY_COURSE = "07";

    // 设置遗传代数
    int GENERATION = 50;

    /**
     * 编码规则  <code>编码名:编码长度<code/>
     * @description 有先后顺序
     */
    Map<String, Integer> RULES = new LinkedHashMap<String, Integer>(){{
        put(CLASS_NO, 8);
        put(TEACHER_NO, 8);
        put(COURSE_NO, 8);
        put(COURSE_ATTR, 2);
        put(START_WEEK, 2);
        put(END_WEEK, 2);
        put(BIWEEKLY, 1);
        put(AREA_NO, 2);
        put(CLASS_COUNT, 1);
        put(IS_FIX, 1);
        put(IFFIXROOM, 1);
        put(COURSEATTR, 2);
        put(TASKATTR, 2);
        put(COURSEDEPARTMENTNO, 2);
        put(DURATION, 1);
        put(TIMESLOT, 2);
        put(ROOM_NO, 8);
    }};
}
