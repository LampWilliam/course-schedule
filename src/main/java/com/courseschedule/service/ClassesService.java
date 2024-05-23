package com.courseschedule.service;

import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.Classes;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 班级 服务类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
public interface ClassesService extends IService<Classes> {

    Result getList();
}
