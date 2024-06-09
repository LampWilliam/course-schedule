package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.Classes;
import com.courseschedule.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

}
