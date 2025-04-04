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

    @Select("SELECT distinct teacher_no FROM task")
    List<String> selectTeacherNo();

    // 可能这里是columnName没传进来
    @Select("select distinct ${columnName} from task")
    List<String> selectByColumnName(@Param("columnName") String columnName);

    @Update("truncate task")
    void clearClassTaskOld();

    @Update("UPDATE task set if_scheduled = #{ifScheduled} where id = #{id}")
    void ifScheduled(Task task);

    @Update("UPDATE task set if_fix_room = #{ifFixRoom} where id = #{id}")
    void ifFixRoom(Task task);

    @Select("select * from task where class_no=#{classNo}")
    List<Task> getListByClassNo(String classNo);

    @Update("UPDATE task set if_scheduled = #{check}")
    void updateAll(boolean check);

    @Update("UPDATE task set if_scheduled = #{check} where class_no = #{classNo}")
    void updateAllByClassNo(boolean check, String classNo);

    @Update("UPDATE task set if_fix_room = #{check}")
    void updateFixAll(boolean check);

    @Update("UPDATE task set if_fix_room = #{check} where class_no = #{classNo}")
    void updateFixAllByClassNo(boolean check, String classNo);
}
