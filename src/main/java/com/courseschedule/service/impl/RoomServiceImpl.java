package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.entity.Room;
import com.courseschedule.entity.Teacher;
import com.courseschedule.mapper.RoomMapper;
import com.courseschedule.service.RoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Result getList() {
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<Room>()
                .eq(Room::getIsDeleted, BaseVo.NOT_DELETED);
        List<Room> list = super.list(wrapper);
        return Result.success("成功返回教室表", list);
    }

    @Override
    public void add(Room room) {
        room.setCreatedTime(LocalDateTime.now());
        room.setUpdatedTime(LocalDateTime.now());

        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        room.setCreatedBy(userDto.getId());
        roomMapper.add(room);
    }

    @Override
    public void update(Room room) {
        room.setUpdatedTime(LocalDateTime.now());
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        room.setCreatedBy(userDto.getId());
        roomMapper.update(room);
    }

    @Override
    public void delete(Long id) {
        roomMapper.delete(id);
    }

}
