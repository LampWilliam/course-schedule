package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.entity.Classes;
import com.courseschedule.mapper.ClassesMapper;
import com.courseschedule.service.ClassesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 班级 服务实现类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {

    @Override
    public Result getList() {
        LambdaQueryWrapper<Classes> wrapper = new LambdaQueryWrapper<Classes>()
                .eq(Classes::getIsDeleted, BaseVo.NOT_DELETED);
        List<Classes> list = super.list(wrapper);
        return Result.success("成功返回班级表", list);
    }
}
