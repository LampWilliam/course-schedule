package com.courseschedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.courseschedule.entity.TeacherRule;

import java.util.List;

public interface TeacherRuleService extends IService<TeacherRule> {
    //获取教师规则列表
    List<TeacherRule> getList();

    void add(TeacherRule teacherRule);

    void update(TeacherRule teacherRule);

    void delete(Integer id);
}
