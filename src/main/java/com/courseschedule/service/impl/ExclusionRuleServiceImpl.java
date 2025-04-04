package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.entity.ExclusionRule;
import com.courseschedule.mapper.*;
import com.courseschedule.service.ExclusionRuleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExclusionRuleServiceImpl extends ServiceImpl<ExclusionRuleMapper, ExclusionRule> implements ExclusionRuleService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ClassesMapper classesMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private ExclusionRuleMapper exclusionRuleMapper;

    @Override
    public List<ExclusionRule> getList() {
        return exclusionRuleMapper.getList();
    }

    @Override
    public List<ExclusionRule> getCourseList() {
        return exclusionRuleMapper.getCourseList();
    }

    @Override
    public List<ExclusionRule> getClassList() {
        return exclusionRuleMapper.getClassList();
    }

    @Override
    public List<ExclusionRule> getTeacherList() {
        return exclusionRuleMapper.getTeacherList();
    }

    @Override
    public List<ExclusionRule> getRoomList() {
        return exclusionRuleMapper.getRoomList();
    }

    @Override

    public void add(ExclusionRule exclusionRule) {
        exclusionRule.setCreatedTime(LocalDateTime.now());
        exclusionRule.setUpdatedTime(LocalDateTime.now());
        String classNo = classesMapper.selectClassNo(exclusionRule.getClassName()) == null ? "" : exclusionRule.getClassName();
        exclusionRule.setClassNo(classNo);
        String teacherNo = teacherMapper.selectTeacherNo(exclusionRule.getTeacherName()) == null ? "" : exclusionRule.getTeacherName();
        exclusionRule.setTeacherNo(teacherNo);
        String roomNo = roomMapper.selectRoomNo1(exclusionRule.getRoomName()) == null ? "" : exclusionRule.getRoomName();
        exclusionRule.setRoomNo(roomNo);
        String courseNo = courseMapper.selectCourseNo(exclusionRule.getCourseName()) == null ? "" : exclusionRule.getCourseName();
        exclusionRule.setCourseNo(courseNo);

        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        exclusionRule.setCreatedBy(userDto.getId());
        exclusionRuleMapper.add(exclusionRule);
    }

    @Override
    public void update(ExclusionRule exclusionRule) {
        exclusionRule.setUpdatedTime(LocalDateTime.now());
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        exclusionRule.setCreatedBy(userDto.getId());
        String classNo = classesMapper.selectClassNo(exclusionRule.getClassName()) == null ? "" : classesMapper.selectClassNo(exclusionRule.getClassName());
        exclusionRule.setClassNo(classNo);
        String teacherNo = teacherMapper.selectTeacherNo(exclusionRule.getTeacherName()) == null ? "" : teacherMapper.selectTeacherNo(exclusionRule.getTeacherName());
        exclusionRule.setTeacherNo(teacherNo);
        String roomNo = roomMapper.selectRoomNo1(exclusionRule.getRoomName()) == null ? "" : roomMapper.selectRoomNo1(exclusionRule.getRoomName());
        exclusionRule.setRoomNo(roomNo);
        String courseNo = courseMapper.selectCourseNo(exclusionRule.getCourseName()) == null ? "" : courseMapper.selectCourseNo(exclusionRule.getCourseName());
        exclusionRule.setCourseNo(courseNo);
        exclusionRuleMapper.update(exclusionRule);
    }

    @Override
    public void delete(Integer id) {
        exclusionRuleMapper.delete(id);
    }
}
