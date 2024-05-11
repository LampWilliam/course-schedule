package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.Timetable;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * [output]课程表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
public interface TimetableMapper extends BaseMapper<Timetable> {

    // truncate会重置auto_increment（自增序列）的值为1
    @Update("truncate timetable")
    void deleteAllPlan();

}
