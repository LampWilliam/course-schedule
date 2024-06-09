package com.courseschedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Classes;
import com.courseschedule.entity.Teacher;

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
}
