package com.courseschedule.service.impl;

import com.courseschedule.entity.Task;
import com.courseschedule.mapper.TaskMapper;
import com.courseschedule.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * [input]教学任务表(包括教学大纲，教师选课) 服务实现类
 * </p>
 *
 * @author 
 * @since 2024-05-10
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

}
