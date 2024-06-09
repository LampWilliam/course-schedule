package com.courseschedule.common.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.courseschedule.entity.Timetable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableClassDto implements Serializable {
    private static final long serialVersionUID = -4879059791317395064L;
    /**
     * 班级编号
     */
    private String className;

    /**
     * 课程编号
     */
    private String courseName;

    /**
     * 教室编号
     */
    private String roomName;

    /**
     * 教学区域名
     */
    private String areaName;

    /**
     * 上课时间(大节)
     */
    private Integer timeslot;

    /**
     * 开始周
     */
    private Integer startWeek;

    /**
     * 开始周
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
