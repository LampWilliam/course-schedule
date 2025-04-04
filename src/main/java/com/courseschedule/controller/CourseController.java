package com.courseschedule.controller;

import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Course;
import com.courseschedule.service.CourseService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequiresAuthentication
    @GetMapping
    public Result getList() {
        List<Course> courses = courseService.getList();
        return Result.success("查询成功", courses);
    }

    @RequiresAuthentication
    @PostMapping
    public Result add(@RequestBody @Validated(Course.Add.class) Course course) {
        courseService.add(course);
        return Result.success("添加成功");
    }

    @RequiresAuthentication
    @PutMapping
    public Result update(@RequestBody @Validated(Course.Update.class) Course course){
        courseService.update(course);
        return Result.success("修改成功");
    }

    @RequiresAuthentication
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        courseService.delete(id);
        return Result.success("删除成功");
    }
}
