package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.ExclusionRule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ExclusionRuleMapper extends BaseMapper<ExclusionRule> {
    //获取禁排规则表
    @Select("select * from exclusion_rule order by timeslot asc" )
    List<ExclusionRule> getList();

    //增加禁排规则
    @Insert("Insert into exclusion_rule(timeslot,courseName,course_no,className, class_no,teacherName, teacher_no,roomName, room_no) " +
            "VALUES(#{timeslot},#{courseName},#{courseNo},#{className},#{classNo},#{teacherName},#{teacherNo},#{roomName},#{roomNo})")
    void add(ExclusionRule exclusionRule);

    //更新禁排规则
    @Update("Update exclusion_rule set timeslot=#{timeslot},course_no=#{courseNo},courseName=#{courseName},class_no=#{classNo},className=#{className},teacher_no=#{teacherNo},teacherName=#{teacherName},room_no=#{roomNo},roomName=#{roomName} where id=#{id}")
    void update(ExclusionRule exclusionRule);

    //删除禁排规则
    @Delete("delete from exclusion_rule where id=#{id}")
    void delete(Integer id);

    //获取课程禁排列表
    @Select("select * from exclusion_rule WHERE course_no IS NOT NULL" )
    List<ExclusionRule> getCourseList();

    //获取班级禁排列表
    @Select("select * from exclusion_rule WHERE class_no IS NOT NULL" )
    List<ExclusionRule> getClassList();

    //获取教师禁排列表
    @Select("select * from exclusion_rule WHERE teacher_no IS NOT NULL" )
    List<ExclusionRule> getTeacherList();

    //获取教室禁排列表
    @Select("select * from exclusion_rule WHERE room_no IS NOT NULL" )
    List<ExclusionRule> getRoomList();
}
