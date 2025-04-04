package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.TimetableRehearsal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 排练课程表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2024-05-23
 */
public interface TimetableRehearsalMapper extends BaseMapper<TimetableRehearsal> {
    @Update("truncate timetable_rehearsal")
    void deleteAll();

    @Insert("INSERT INTO timetable_rehearsal " +
            "(id, class_no, teacher_no, room_no, timeslot, start_week, end_week, biweekly) " +
            "SELECT id, class_no, teacher_no, room_no, timeslot, start_week, end_week, biweekly " +
            "FROM timetable " +
            "WHERE semester_id = #{semesterId} AND is_deleted=0")
    void insertRehearsalData(Long semesterId);

    @Update("UPDATE timetable_rehearsal " +
            "SET timeslot = #{timeslot2} " +
            "WHERE id = #{id}")
    void updateRehearsalData(Long id, @Param("timeslot2") int timeslot2);

    TimetableRehearsal rehearsalChangeTimeslot(Long id);
}
