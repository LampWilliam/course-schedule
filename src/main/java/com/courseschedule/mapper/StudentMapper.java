package com.courseschedule.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.Course;
import com.courseschedule.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {
    @Select("select * from student where created_by = #{userId}")
    List<Student> list(Long userId);

    //新增学生
    @Insert("Insert into student(name,gender,birthday,number,student_no,class_no,created_time,updated_time,created_by) "+
            "values(#{name},#{gender},#{birthday},#{number},#{studentNo},#{classNo},#{createdTime},#{updatedTime},#{createdBy})")
    void add(Student student);

    //更新课程信息
    @Update("update student set name=#{name},gender=#{gender},birthday=#{birthday},number=#{number},student_no=#{studentNo},class_no=#{classNo}," +
            "created_time=#{createdTime},updated_time=#{updatedTime},created_by=#{createdBy} where id=#{id}")
    void update(Student student);

    //删除课程
    @Delete("delete from student where id=#{id}")
    void delete(Long id);

    //根据学生名查询学生
    @Select("select * from student where name=#{studentName}")
    Student findByUserName(String studentName);

    //根据用户id获取班级编号
    @Select("select class_no from student where id=#{studentId}")
    String getClassNo(String studentId);

    //根据用户id获取学生信息
    @Select("select * from student where id=#{studentId}")
    Student findByUserId(String id);
}
