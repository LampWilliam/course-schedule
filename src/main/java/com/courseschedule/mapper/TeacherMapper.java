package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Classes;
import com.courseschedule.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 教师 Mapper 接口
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    //新增老师
    @Insert("Insert into teacher(teacher_no, teacher_name, created_by, created_time, updated_time) "+
            "values(#{teacherNo},#{teacherName},#{createdBy},#{createdTime},#{updatedTime})")
    void add(Teacher teacher);

    //更新老师信息
    @Update("update teacher set teacher_no=#{teacherNo},teacher_name=#{teacherName},updated_time=#{updatedTime} where id=#{id}")
    void update(Teacher teacher);

    //删除老师
    @Delete("delete from teacher where id=#{id}")
    void delete(Long id);

    //根据老师名查询老师
    @Select("select * from teacher where teacher_name=#{teacherName}")
    Teacher findByTeacherName(String teacherName);

    //根据教师id查找教师编号
    @Select("select teacher_no from teacher where id=#{teacherId}")
    String getClassNo(String teacherId);

    @Select("select teacher_no from teacher where teacher_name=#{teacherName}")
    String selectTeacherNo(String teacherName);
}
