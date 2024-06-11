package com.courseschedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Task;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)//该方法中抛出Exception及其子类时，当前事务回滚
    Result courseScheduling(Long id);

    Result getList();

//    ServerResponse classScheduling(@Param("semester") String semester);
}
