package com.courseschedule.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Task;
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
    @GetMapping("/{page}")
    public Result task(@PathVariable("page") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        Page<Task> ipage = taskService.page(new Page<>(page, limit), wrapper);
        return ipage != null ? Result.success("查询开课任务成功", ipage) : Result.error("查询开课任务失败");
    }

    /**
     * 排课算法接口。通过查询task表进行排课，排课后结果存入timetable表
     */
    @PostMapping("/arrange")
    public Result arrange() {
        return taskService.courseScheduling();
    }
}
