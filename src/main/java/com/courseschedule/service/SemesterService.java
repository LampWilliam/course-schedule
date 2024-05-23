package com.courseschedule.service;

import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.SemesterVo;
import com.courseschedule.entity.Semester;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2024-05-19
 */
public interface SemesterService extends IService<Semester> {
    Result getList();


    Result add(SemesterVo vo);

    Result update(SemesterVo vo);

    Result delete(Long id);
}
