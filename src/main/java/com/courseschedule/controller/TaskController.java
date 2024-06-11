package com.courseschedule.controller;


import com.courseschedule.common.lang.Result;
import com.courseschedule.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 排课算法接口。通过查询task表进行排课，排课后结果存入timetable表
     */
    @PostMapping("/autoSchedule/{id}")
    public Result autoSchedule(@PathVariable Long id) {
        return taskService.courseScheduling(id);
    }

}
