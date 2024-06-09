package com.courseschedule.mapper;

import com.courseschedule.entity.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * [input]教室 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2024-05-10
 */
public interface RoomMapper extends BaseMapper<Room> {

    /**
     * 获得班级编号
     */
    @Select("select room_no from room")
    List<Room> selectRoomNo();
}
