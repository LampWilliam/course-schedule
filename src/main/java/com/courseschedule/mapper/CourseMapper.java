package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {
    //查询所有
    @Select("select * from course")
    List<Course> list(Long userId);

    //新增课程
    @Insert("Insert into course(course_no, course_name, course_attr, remark, created_time,updated_time) "+
            "values(#{courseNo},#{courseName},#{courseAttr},#{remark},#{createdTime},#{updatedTime})")
    void add(Course course);

    //更新课程信息
    @Update("update course set course_no=#{courseNo},course_name=#{courseName},course_attr=#{courseAttr},remark=#{remark},created_time=#{createdTime},updated_time=#{updatedTime} " +
            " where id=#{id}")
    void update(Course course);

    //删除课程
    @Delete("delete from course where id=#{id}")
    void delete(Long id);

    @Select("select course_no from course where course_name=#{courseName}")
    String selectCourseNo(String courseName);
}
