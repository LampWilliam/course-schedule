package com.courseschedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.entity.Classes;
import com.courseschedule.entity.Teacher;
import com.courseschedule.mapper.ClassesMapper;
import com.courseschedule.mapper.TeacherMapper;
import com.courseschedule.service.ClassesService;
import com.courseschedule.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 教师 服务实现类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Result getList() {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<Teacher>()
                .eq(Teacher::getIsDeleted, BaseVo.NOT_DELETED);
        List<Teacher> list = super.list(wrapper);
        return Result.success("成功返回教师表", list);
    }

}
