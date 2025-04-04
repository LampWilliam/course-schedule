package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.entity.Student;
import com.courseschedule.entity.StudentScore;
import com.courseschedule.mapper.StudentMapper;
import com.courseschedule.mapper.StudentScoreMapper;
import com.courseschedule.service.StudentScoreService;
import com.courseschedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentScoreServiceImpl extends ServiceImpl<StudentScoreMapper, StudentScore> implements StudentScoreService {

    @Autowired
    private StudentScoreMapper studentScoreMapper;

    @Override
    public List<StudentScore> getList() {
        return studentScoreMapper.getList();
    }

    @Override
    public void add(StudentScore studentScore) {
        studentScoreMapper.add(studentScore);
    }

    @Override
    public void update(StudentScore studentScore) {
        studentScoreMapper.update(studentScore);
    }

    @Override
    public void delete(Integer id) {
        studentScoreMapper.delete(id);
    }

    @Override
    public List<StudentScore> getListGroupBYClass(String id) {
        return studentScoreMapper.getListGroupBYClass(id);
    }
}
