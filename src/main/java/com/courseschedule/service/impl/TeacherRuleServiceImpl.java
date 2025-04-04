package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.entity.TeacherRule;
import com.courseschedule.mapper.TeacherMapper;
import com.courseschedule.mapper.TeacherRuleMapper;
import com.courseschedule.service.TeacherRuleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeacherRuleServiceImpl extends ServiceImpl<TeacherRuleMapper, TeacherRule> implements TeacherRuleService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherRuleMapper teacherRuleMapper;

    @Override
    public List<TeacherRule> getList() {
        return teacherRuleMapper.getList();
    }

    public void add(TeacherRule teacherRule) {
        teacherRule.setCreatedTime(LocalDateTime.now());
        teacherRule.setUpdatedTime(LocalDateTime.now());
        String teacherNo = teacherMapper.selectTeacherNo(teacherRule.getTeacherName()) == null ? "" : teacherMapper.selectTeacherNo(teacherRule.getTeacherName());
        teacherRule.setTeacherNo(teacherNo);

        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        teacherRule.setCreatedBy(userDto.getId());
        teacherRuleMapper.add(teacherRule);
    }

    @Override
    public void update(TeacherRule teacherRule) {
        teacherRule.setUpdatedTime(LocalDateTime.now());
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        teacherRule.setCreatedBy(userDto.getId());
        String teacherNo = teacherMapper.selectTeacherNo(teacherRule.getTeacherName()) == null ? "" : teacherMapper.selectTeacherNo(teacherRule.getTeacherName());
        teacherRule.setTeacherNo(teacherNo);
        teacherRuleMapper.update(teacherRule);
    }

    @Override
    public void delete(Integer id) {
        teacherRuleMapper.delete(id);
    }
}
