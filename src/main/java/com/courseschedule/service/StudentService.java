package com.courseschedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.StudentVo;
import com.courseschedule.entity.Student;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface StudentService extends IService<Student> {
    //获取学生列表
    List<Student> getList();

    //新增学生
    void add(Student student);

    //更新学生信息
    void update(Student student);

    //删除学生
    void delete(Long id);

    //根据学生名查询学生
    Student findByUserName(String studentName);

    //根据用户id获取班级编号
    String getClassNo(String studentId);

    //根据id获取学生信息
    Student findByUserId(String id);
}
