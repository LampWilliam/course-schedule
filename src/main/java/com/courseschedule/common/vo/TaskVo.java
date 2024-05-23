package com.courseschedule.common.vo;

import lombok.Data;

@Data
public class TaskVo extends BaseVo {

    /**
     * id
     */
    private Long id;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 教师编号
     */
    private String teacherNo;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 课程属性 01理论 02实验 03实践 04体育课
     */
    private String courseAttr;

    /**
     * 周数
     */
    private Integer weeksSum;

    /**
     * 周学时 - 必须是2的倍数
     */
    private Integer weeksNumber;

    /**
     * 开始周
     */
    private String startWeek;

    /**
     * 结束周
     */
    private String endWeek;

    /**
     * 单双周 0代表非单双周 1代表单周 2代表双周
     */
    private String biweekly;

    /**
     * 所在教学区域(教学楼、体育场等)编号
     */
    private String areaNo;

    /**
     * 同时上课班级数(>1为大班课)
     */
    private String classCount;

    /**
     * 班级名
     */
    private String className;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 课程名
     */
    private String courseName;
}
