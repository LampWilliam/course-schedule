package com.courseschedule.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 班级 前端控制器
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@RestController
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    @GetMapping("/getList")
    public Result getList() {
        return classesService.getList();
    }

    /**
     * 获得班级集合
     */
    @GetMapping("/classNo")
    public Result queryAllClassNo() {
        return classesService.getClassNo();
    }
}
