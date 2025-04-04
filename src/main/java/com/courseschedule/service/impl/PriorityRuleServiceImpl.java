package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.entity.PriorityRule;
import com.courseschedule.mapper.PriorityRuleMapper;
import com.courseschedule.service.PriorityRuleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriorityRuleServiceImpl extends ServiceImpl<PriorityRuleMapper, PriorityRule> implements PriorityRuleService {

    @Autowired
    private PriorityRuleMapper priorityRuleMapper;

    @Override
    public List<PriorityRule> getList() {
        return priorityRuleMapper.getList();
    }

    @Override
    public void add(PriorityRule priorityRule) {
        priorityRule.setCreatedTime(LocalDateTime.now());
        priorityRule.setUpdatedTime(LocalDateTime.now());
        String courseDepartmentName = priorityRule.getCourseDepartmentName() == null ? "" : priorityRule.getCourseDepartmentName();
        String courseDepartmentNo = priorityRule.getDepartmentNo(courseDepartmentName);
        priorityRule.setCourseDepartmentNo(courseDepartmentNo);
        String taskAttrName = priorityRule.getTaskAttrName() == null ? "" : priorityRule.getTaskAttrName();
        String taskAttr = priorityRule.getTaskAttr(taskAttrName);
        priorityRule.setTaskAttr(taskAttr);
        String courseAttrName = priorityRule.getCourseAttrName() == null ? "" : priorityRule.getCourseAttrName();
        String courseAttr = priorityRule.getCourseAttr(courseAttrName);
        priorityRule.setCourseAttr(courseAttr);

        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        priorityRule.setCreatedBy(userDto.getId());
        priorityRuleMapper.add(priorityRule);
    }

    @Override
    public void update(PriorityRule priorityRule) {
        priorityRule.setUpdatedTime(LocalDateTime.now());
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        priorityRule.setCreatedBy(userDto.getId());
        priorityRule.setCreatedTime(LocalDateTime.now());
        priorityRule.setUpdatedTime(LocalDateTime.now());
        String courseDepartmentName = priorityRule.getCourseDepartmentName() == null ? "" : priorityRule.getCourseDepartmentName();
        String courseDepartmentNo = priorityRule.getDepartmentNo(courseDepartmentName);
        priorityRule.setCourseDepartmentNo(courseDepartmentNo);
        String taskAttrName = priorityRule.getTaskAttrName() == null ? "" : priorityRule.getTaskAttrName();
        String taskAttr = priorityRule.getTaskAttr(taskAttrName);
        priorityRule.setTaskAttr(taskAttr);
        String courseAttrName = priorityRule.getCourseAttrName() == null ? "" : priorityRule.getCourseAttrName();
        String courseAttr = priorityRule.getCourseAttr(courseAttrName);
        priorityRule.setCourseAttr(courseAttr);
        priorityRuleMapper.update(priorityRule);
    }

    @Override
    public void delete(Integer id) {
        priorityRuleMapper.delete(id);
    }
}
