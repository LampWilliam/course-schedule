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
    Result courseScheduling(Long id,int ifScheduledInNight,int ifExpInNight);

    Result getList();

    // 选择排课任务是否排课
    void ifScheduled(Task task);

    // 排课任务全选
    void updateAll(boolean check);

    // 是否启用班级固定教室
    void ifFixRoom(Task task);

    // 根据班级编号查询班级开课任务
    Result getListByClassNo(String classNo);

    // 选择班级排课任务全选
    void updateAllByClassNo(boolean check,String classNo);

    // 启用班级固定教室全选
    void updateFixAll(boolean check);

    // 根据班级启用班级固定教室全选
    void updateFixAllByClassNo(boolean check, String classNo);

//    ServerResponse classScheduling(@Param("semester") String semester);
}
