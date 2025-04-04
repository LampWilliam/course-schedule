package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.TeacherComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TeacherCommentMapper extends BaseMapper<TeacherComment> {
    @Select("select * from teacher_comment" )
    List<TeacherComment> getList();

    //新增教师评价
    @Insert("Insert into teacher_comment(teacher_no, teacher_name, comment, advice, created_time, updated_time) " +
            "VALUES(#{teacherNo},#{teacherName},#{comment},#{advice},#{createdTime},#{updatedTime})")
    void add(TeacherComment teacherComment);

    //更新教师评价
    @Update("Update teacher_comment set teacher_no=#{teacherNo},teacher_name=#{teacherName},comment=#{comment},advice=#{advice},updated_time=#{updatedTime} where id=#{id}")
    void update(TeacherComment teacherComment);

    //删除教师评价
    @Delete("delete from teacher_comment where id=#{id}")
    void delete(Integer id);

    //获取教师评价
    @Select("select * from teacher_comment where teacher_no=#{teacherNo}")
    List<TeacherComment> getTeacherComment(String teacherNo);
}
