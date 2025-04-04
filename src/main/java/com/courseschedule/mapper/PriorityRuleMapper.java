package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.ExclusionRule;
import com.courseschedule.entity.PriorityRule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PriorityRuleMapper extends BaseMapper<PriorityRule> {
    //获取优先排规则表
    @Select("select * from priority_rule" )
    List<PriorityRule> getList();

    //增加优先排规则
    @Insert("Insert into priority_rule(timeslot, course_department_no,course_department_name, task_attr,task_attr_name, course_attr,course_attr_name) " +
            "VALUES(#{timeslot},#{courseDepartmentNo},#{courseDepartmentName},#{taskAttr},#{taskAttrName},#{courseAttr},#{courseAttrName})")
    void add(PriorityRule priorityRule);

    //更新禁排规则
    @Update("Update priority_rule set timeslot=#{timeslot},course_department_no=#{courseDepartmentNo},course_department_name=#{courseDepartmentName},task_attr=#{taskAttr},task_attr_name=#{taskAttrName},course_attr=#{courseAttr},course_attr_name=#{courseAttrName} where id=#{id}")
    void update(PriorityRule priorityRule);

    //删除禁排规则
    @Delete("delete from priority_rule where id=#{id}")
    void delete(Integer id);

    //获取开课院系优先排排列表
    @Select("select * from priority_rule WHERE course_department_no IS NOT NULL" )
    List<PriorityRule> getCourseDepartmentList();

    //获取课程性质优先排列表
    @Select("select * from priority_rule WHERE task_attr IS NOT NULL" )
    List<PriorityRule> getTaskAttrList();

    //获取学时类型优先排列表
    @Select("select * from priority_rule WHERE course_attr IS NOT NULL" )
    List<PriorityRule> getCourseAttrList();
}
