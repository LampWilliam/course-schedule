package com.courseschedule.common.vo;

import lombok.Data;

@Data
public class TimetableVo extends BaseVo {
    /**
     * id
     */
    private Long id;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 讲师编号
     */
    private String teacherNo;

    /**
     * 教室编号
     */
    private String roomNo;

    /**
     * 上课时间(大节)
     */
    private Integer timeslot;

    /**
     * 开始周
     */
    private Integer startWeek;

    /**
     * 结束周
     */
    private Integer endWeek;

    /**
     * 单双周 0代表非单双周 1代表单周 2代表双周
     */
    private Integer biweekly;

    /**
     * 关联学期Id
     */
    private Long semesterId;
}
