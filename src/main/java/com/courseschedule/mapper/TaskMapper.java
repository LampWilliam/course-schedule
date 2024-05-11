package com.courseschedule.mapper;

import com.courseschedule.entity.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * [input]教学任务表(包括教学大纲，教师选课) Mapper 接口
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
public interface TaskMapper extends BaseMapper<Task> {

    @Select("SELECT distinct class_no FROM task")
    List<String> selectClassNo();

    // 可能这里是columnName没传进来
    @Select("select distinct ${columnName} from task")
    List<String> selectByColumnName(@Param("columnName") String columnName);

    @Update("truncate task")
    void clearClassTaskOld();
}
