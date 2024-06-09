package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.Classes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 班级 Mapper 接口
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
public interface ClassesMapper extends BaseMapper<Classes> {

    /**
     * 获得班级的人数
      */
    @Select("select size from classes where class_no = #{classNo}")
    int selectStuSize(@Param("classNo") String classNo);

    /**
     * 获得班级编号
     */
    @Select("select class_no from classes")
    List<Classes> selectClassNo();

    /**
     * 修改班级信息
     * @param classNo
     * @param className
     * @param size
     */
    @Update("update classes set class_name = #{className},size = #{size} where class_no = #{classNo}")
    void updateClasses(String classNo, @Param("className") String className, @Param("size") int size);

}
