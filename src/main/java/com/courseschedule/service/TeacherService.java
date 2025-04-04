package com.courseschedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Teacher;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 教师服务类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
public interface TeacherService extends IService<Teacher> {

    Result getList();

    //新增老师
    void add(Teacher teacher);

    //更新老师信息
    void update(Teacher teacher);

    //删除老师
    void delete(Long id);



    //根据教师id查找教师编号
    String getClassNo(String teacherId);

}
