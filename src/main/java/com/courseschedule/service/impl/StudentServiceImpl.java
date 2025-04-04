package com.courseschedule.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.dto.StudentDto;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.common.vo.StudentVo;
import com.courseschedule.entity.Student;
import com.courseschedule.mapper.StudentMapper;
import com.courseschedule.service.StudentService;
import com.courseschedule.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public List<Student> getList() {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        Long userId = userDto.getId();
        List<Student> studentList = new ArrayList<>();
        return studentMapper.list(userId);
    }

    @Override
    public void add(Student student) {
        student.setCreatedTime(LocalDateTime.now());
        student.setUpdatedTime(LocalDateTime.now());

        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        student.setCreatedBy(userDto.getId());
        studentMapper.add(student);
    }

    @Override
    public void update(Student student) {
        student.setUpdatedTime(LocalDateTime.now());
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        student.setCreatedBy(userDto.getId());
        studentMapper.update(student);
    }

    @Override
    public void delete(Long id) {
        studentMapper.delete(id);
    }

    @Override
    public Student findByUserName(String studentName) {
        Student stu = studentMapper.findByUserName(studentName);
        return stu;
    }

    @Override
    public String getClassNo(String studentId) {
        String classNo = studentMapper.getClassNo(studentId);
        return classNo;
    }

    @Override
    public Student findByUserId(String id) {
        Student student = studentMapper.findByUserId(id);
        return student;
    }

}
