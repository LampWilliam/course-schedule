package com.courseschedule.service;

import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * [input]教学任务表(包括教学大纲，教师选课) 服务类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
public interface TaskService extends IService<Task> {

    /**
     * 排课算法
     */
    Result courseScheduling();
}
