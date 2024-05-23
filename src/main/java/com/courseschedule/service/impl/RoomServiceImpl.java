package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.entity.Room;
import com.courseschedule.mapper.RoomMapper;
import com.courseschedule.service.RoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * [input]教室 服务实现类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Override
    public Result getList() {
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<Room>()
                .eq(Room::getIsDeleted, BaseVo.NOT_DELETED);
        List<Room> list = super.list(wrapper);
        return Result.success("成功返回教室表", list);
    }
}
