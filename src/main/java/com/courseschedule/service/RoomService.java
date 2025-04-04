package com.courseschedule.service;

import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * [input]教室 服务类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
public interface RoomService extends IService<Room> {

    Result getList();

    //新增教室
    void add(Room room);

    //更新教室信息
    void update(Room room);

    //删除教室
    void delete(Long id);
}
