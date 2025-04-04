package com.courseschedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.courseschedule.entity.Student;
import com.courseschedule.entity.StudentScore;

import java.util.List;

public interface StudentScoreService extends IService<StudentScore> {
    //获取学生成绩表
    List<StudentScore> getList();

    //增加学生成绩
    void add(StudentScore studentScore);

    //修改学生成绩
    void update(StudentScore studentScore);

    //删除学生成绩
    void delete(Integer id);

    //根据班级查询学生成绩
    List<StudentScore> getListGroupBYClass(String id);
}
