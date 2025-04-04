package com.courseschedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.courseschedule.entity.Course;

import java.util.List;

public interface CourseService extends IService<Course> {
    //获取课程列表
    List<Course> getList();

    void add(Course course);

    //更新老师信息
    void update(Course course);

    //删除老师
    void delete(Long id);
}
