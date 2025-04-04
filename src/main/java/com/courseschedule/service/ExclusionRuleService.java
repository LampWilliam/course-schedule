package com.courseschedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.courseschedule.entity.ExclusionRule;

import java.util.List;

public interface ExclusionRuleService extends IService<ExclusionRule> {
    //获取禁排列表
    List<ExclusionRule> getList();

    //获得课程禁排列表
    List<ExclusionRule> getCourseList();

    //获得班级禁排列表
    List<ExclusionRule> getClassList();

    //获得教师禁排列表
    List<ExclusionRule> getTeacherList();

    //获得教室禁排列表
    List<ExclusionRule> getRoomList();

    void add(ExclusionRule exclusionRule);

    void update(ExclusionRule exclusionRule);

    void delete(Integer id);

}
