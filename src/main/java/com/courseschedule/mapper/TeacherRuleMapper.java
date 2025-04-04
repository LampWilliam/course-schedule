package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.ExclusionRule;
import com.courseschedule.entity.TeacherRule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TeacherRuleMapper extends BaseMapper<TeacherRule> {
    //获取禁排规则表
    @Select("select * from teacher_rule" )
    List<TeacherRule> getList();

    //增加禁排规则
    @Insert("Insert into teacher_rule(teacher_no, teacherName, dTimeslot, mTimeslot, eTimeslot) " +
            "VALUES(#{teacherNo},#{teacherName},#{dTimeslot},#{mTimeslot},#{eTimeslot})")
    void add(TeacherRule teacherRule);

    //更新禁排规则
    @Update("Update teacher_rule set teacher_no=#{teacherNo},teacherName=#{teacherName},dTimeslot=#{dTimeslot},mTimeslot=#{mTimeslot},eTimeslot=#{eTimeslot} where id=#{id}")
    void update(TeacherRule teacherRule);

    //删除禁排规则
    @Delete("delete from teacher_rule where id=#{id}")
    void delete(Integer id);

    @Select("select dTimeslot from teacher_rule where teacher_no=#{teacherNo}" )
    Integer findDTByTeacherNo(String teacherNo);

    @Select("select mTimeslot from teacher_rule where teacher_no=#{teacherNo}" )
    Integer findMTByTeacherNo(String teacherNo);

    @Select("select eTimeslot from teacher_rule where teacher_no=#{teacherNo}" )
    Integer findETByTeacherNo(String teacherNo);
}
