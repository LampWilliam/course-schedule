package com.courseschedule.service;

import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Semester;
import com.courseschedule.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.function.ServerResponse;

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
    Result courseScheduling(Semester semester);

    Result getList();

//    ServerResponse classScheduling(@Param("semester") String semester);
}
