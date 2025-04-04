package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.entity.TeacherComment;
import com.courseschedule.mapper.TeacherCommentMapper;
import com.courseschedule.service.TeacherCommentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeacherCommentServiceImpl extends ServiceImpl<TeacherCommentMapper, TeacherComment> implements TeacherCommentService {

    @Autowired
    private TeacherCommentMapper teacherCommentMapper;

    @Override
    public List<TeacherComment> getList() {
        return teacherCommentMapper.getList();
    }

    @Override
    public void add(TeacherComment teacherComment) {
        teacherComment.setCreatedTime(LocalDateTime.now());
        teacherComment.setUpdatedTime(LocalDateTime.now());

        teacherCommentMapper.add(teacherComment);
    }

    @Override
    public void update(TeacherComment teacherComment) {
        teacherComment.setUpdatedTime(LocalDateTime.now());
        teacherCommentMapper.update(teacherComment);
    }

    @Override
    public void delete(Integer id) {
        teacherCommentMapper.delete(id);
    }
}
