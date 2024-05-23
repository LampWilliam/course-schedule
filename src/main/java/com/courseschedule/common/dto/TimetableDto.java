package com.courseschedule.common.dto;

import com.courseschedule.entity.Timetable;
import lombok.Data;

@Data
public class TimetableDto extends Timetable {
    // 冲突类型
    private String conflictType;
}
