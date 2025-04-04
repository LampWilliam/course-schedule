package com.courseschedule.controller;


import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Course;
import com.courseschedule.entity.Task;
import com.courseschedule.entity.TeacherComment;
import com.courseschedule.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * [input]教学任务表(包括教学大纲，教师选课) 前端控制器
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * 查询开课任务
     */
    @GetMapping("/getList")
    public Result getList() {
        return taskService.getList();
    }

    /**
     * 根据班级编号查询班级开课任务
     */
    @GetMapping("/getList/{classNo}")
    public Result getListByClassNo(@PathVariable String classNo) {
        return taskService.getListByClassNo(classNo);
    }


    /**
     * 排课算法接口。通过查询task表进行排课，排课后结果存入timetable表
     */
    @PostMapping("/autoSchedule/{id}/{ifScheduleInNight}/{ifExpInNight}")
    public Result autoSchedule(@PathVariable Long id,@PathVariable int ifScheduleInNight,@PathVariable int ifExpInNight) {
        return taskService.courseScheduling(id,ifScheduleInNight,ifExpInNight);
    }

    /**
     * 选择排课任务是否启用固定教室
     */
    @PutMapping("/ifFixRoom")
    public Result ifFixRoom(@RequestBody @Validated(Task.Update.class) Task task) {
        taskService.ifFixRoom(task);
        return Result.success("添加成功");
    }

    /**
     * 选择排课任务是否排课
     */
    @PutMapping("/ifScheduled")
    public Result ifScheduled(@RequestBody @Validated(Task.Update.class) Task task) {
        taskService.ifScheduled(task);
        return Result.success("添加成功");
    }

    /**
     * 排课任务全选
     */
    @PutMapping("/updateAll/{check}")
    public Result updateAll(@PathVariable boolean check) {
        taskService.updateAll(check);
        return Result.success("添加成功");
    }

    /**
     * 排课任务全选
     */
    @PutMapping("/updateAll/{check}/{classNo}")
    public Result updateAll(@PathVariable boolean check,@PathVariable String classNo) {
        taskService.updateAllByClassNo(check,classNo);
        return Result.success("添加成功");
    }

    /**
     * 启用班级固定教室全选
     */
    @PutMapping("/updateFixAll/{check}")
    public Result updateFixAll(@PathVariable boolean check) {
        taskService.updateFixAll(check);
        return Result.success("添加成功");
    }

    /**
     * 启用班级固定教室全选
     */
    @PutMapping("/updateFixAll/{check}/{classNo}")
    public Result updateFixAll(@PathVariable boolean check,@PathVariable String classNo) {
        taskService.updateFixAllByClassNo(check,classNo);
        return Result.success("添加成功");
    }
}
