package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.StudentScore;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentScoreMapper extends BaseMapper<StudentScore> {
    //获取学生成绩表
    @Select("select * from student_score" )
    List<StudentScore> getList();

    //增加学生成绩
    @Insert("Insert into student_score(student_name, class_no, course_name, score) " +
            "VALUES(#{studentName},#{classNo},#{courseName},#{score})")
    void add(StudentScore studentScore);

    //更新学生成绩
    @Update("Update student_score set student_name=#{studentName},class_no=#{classNo},course_name=#{courseName},score=#{score} where id=#{id}")
    void update(StudentScore studentScore);

    //删除学生成绩
    @Delete("delete from student_score where id=#{id}")
    void delete(Integer id);

    //根据班级查找学生成绩
    @Select("select * from student_score where class_no = #{id}")
    List<StudentScore> getListGroupBYClass(String id);
}
