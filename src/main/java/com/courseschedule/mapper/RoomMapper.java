package com.courseschedule.mapper;

import com.courseschedule.entity.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.courseschedule.entity.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 获得班级编号
     */
    @Select("select room_no from room WHERE room_name=#{roomName}")
    String selectRoomNo1(String roomName);

    //新增教室
    @Insert("Insert into room(room_no, room_name, area_no, area_name, capacity, remark, created_by, created_time, updated_time) "+
            "values(#{roomNo},#{roomName},#{areaNo},#{areaName},#{capacity},#{remark},#{createdBy},#{createdTime},#{updatedTime})")
    void add(Room room);

    //更新教室信息
    @Update("update room set room_no=#{roomNo},room_name=#{roomName},area_no=#{areaNo},area_name=#{areaName},capacity=#{capacity},remark=#{remark},updated_time=#{updatedTime} where id=#{id}")
    void update(Room room);

    //删除教室
    @Delete("delete from room where id=#{id}")
    void delete(Long id);
}
