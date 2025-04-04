package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.entity.Course;
import com.courseschedule.mapper.CourseMapper;
import com.courseschedule.service.CourseService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getList() {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        Long userId = userDto.getId();
        return courseMapper.list(userId);
    }

    @Override
    public void add(Course course) {
        course.setCreatedTime(LocalDateTime.now());
        course.setUpdatedTime(LocalDateTime.now());

        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        courseMapper.add(course);
    }

    @Override
    public void update(Course course) {
        course.setUpdatedTime(LocalDateTime.now());
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        courseMapper.update(course);
    }

    @Override
    public void delete(Long id) {
        courseMapper.delete(id);
    }
}
