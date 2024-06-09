package com.courseschedule.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.lang.ServerResponse;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.entity.Classes;
import com.courseschedule.entity.Task;
import com.courseschedule.mapper.ClassesMapper;
import com.courseschedule.service.ClassesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Result getClassNo() {
        LambdaQueryWrapper<Classes> wrapper = new LambdaQueryWrapper<Classes>().select(Classes::getClassNo);
        List<Classes> list = super.list(wrapper);

        Set<String> set = list.stream().map(Classes::getClassNo).collect(Collectors.toSet());

        return Result.success("成功返回学期集合", set);
    }
}
