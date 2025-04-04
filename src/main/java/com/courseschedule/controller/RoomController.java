package com.courseschedule.controller;


import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Room;
import com.courseschedule.entity.Teacher;
import com.courseschedule.service.RoomService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * [input]教室 前端控制器
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/getList")
    public Result getList() {
        return roomService.getList();
    }

    @RequiresAuthentication
    @PostMapping
    public Result add(@RequestBody @Validated(Room.Add.class) Room room) {
        roomService.add(room);
        return Result.success("添加成功");
    }

    @RequiresAuthentication
    @PutMapping
    public Result update(@RequestBody @Validated(Room.Update.class) Room room){
        roomService.update(room);
        return Result.success("修改成功");
    }

    @RequiresAuthentication
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        roomService.delete(id);
        return Result.success("删除成功");
    }
}
