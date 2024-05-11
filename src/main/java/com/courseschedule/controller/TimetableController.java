package com.courseschedule.controller;


import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Timetable;
import com.courseschedule.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

/**
 * <p>
 * [output]课程表 前端控制器
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@RestController
@RequestMapping("/timetable")
public class TimetableController {
    @Autowired
    private TimetableService timetableService;

    /**
     * 根据班级编号查找课表
     */
    @GetMapping("/{classNo}")
    public Result getTimetable(@PathVariable String classNo) {
        return Result.success("查询成功", new LinkedList<Timetable>());
    }
    // 查询所有班级课表(根据班级查找课表)


    // 根据教师编号查找课表
    // 查询所有教师课表


    // 根据教室编号查找课表
    // 查询所有教室课表

}
