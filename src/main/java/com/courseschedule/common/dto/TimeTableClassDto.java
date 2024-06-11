package com.courseschedule.common.dto;

import com.courseschedule.entity.Timetable;
import lombok.Data;

@Data
public class TimeTableClassDto extends Timetable {

    private String className;

    private Integer size;

    private String teacherName;

    private String courseName;

    private String roomName;

    /**
     * 教学区域名
     */
    private String areaName;

}
