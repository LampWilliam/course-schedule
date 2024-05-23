package com.courseschedule.controller;


import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Semester;
import com.courseschedule.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/autoSchedule")
    public Result autoSchedule(Semester semester) {// TODO 这里要改来的 这样传参不了
        return taskService.courseScheduling(semester);
    }

}
