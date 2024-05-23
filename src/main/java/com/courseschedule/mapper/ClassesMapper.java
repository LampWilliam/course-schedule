package com.courseschedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.Classes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

}
