package com.courseschedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.courseschedule.entity.TeacherComment;

import java.util.List;

public interface TeacherCommentService extends IService<TeacherComment> {
    //查找教师评价列表
    List<TeacherComment> getList();

    //增加教师评价
    void add(TeacherComment teacherComment);

    //更新教师评价
    void update(TeacherComment teacherComment);

    //删除教师评价
    void delete(Integer id);
}
