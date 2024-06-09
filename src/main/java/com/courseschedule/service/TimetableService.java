package com.courseschedule.service;

import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Timetable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * [output]课程表 服务类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
public interface TimetableService extends IService<Timetable> {

    Result getConflictingColumnNameList(Long semesterId, String columnName);

    Result getConflictingTimeslotIdList(Long semesterId, String columnName, String no);

    Result getConfilctList(Long id);

    Result rehearsalChangeTimeslot(Long id);

    Result getList();

    Result getTimetableByClassNo(String classNo);

    Result queryTimetableByClassNo(List<Timetable> timetableList);
}
