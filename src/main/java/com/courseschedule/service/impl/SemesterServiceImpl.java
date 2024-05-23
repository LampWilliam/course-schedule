package com.courseschedule.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.common.vo.SemesterVo;
import com.courseschedule.entity.Semester;
import com.courseschedule.mapper.SemesterMapper;
import com.courseschedule.service.SemesterService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author
 * @since 2024-05-19
 */
@Service
public class SemesterServiceImpl extends ServiceImpl<SemesterMapper, Semester> implements SemesterService {

    @Override
    public Result getList() {
        LambdaQueryWrapper<Semester> wrapper = new LambdaQueryWrapper<Semester>()
                .eq(Semester::getIsDeleted, BaseVo.NOT_DELETED);
        List<Semester> list = super.list(wrapper);
        return Result.success("成功返回学期列表", list);
    }

    @Override
    public Result add(SemesterVo vo) {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();

        Semester semester = new Semester();
        BeanUtil.copyProperties(vo, semester);
        semester.setSemesterEndDate();
        semester.setCreatedBy(userDto.getId());

        boolean b = super.save(semester);
        return b ? Result.success("添加成功") : Result.error("添加失败");
    }

    @Override
    public Result update(SemesterVo vo) {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();

        Semester semester = super.getById(vo.getId());
        if(semester == null || semester.getIsDeleted().equals(BaseVo.DELETED)) {
            return Result.error("学期不存在或已删除");
        }
        BeanUtil.copyProperties(vo, semester);
        semester.setSemesterEndDate();
        semester.setUpdatedBy(userDto.getId());

        boolean b = super.updateById(semester);
        return b ? Result.success("更新成功") : Result.error("更新失败");
    }

    @Override
    public Result delete(Long id) {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();

        Semester semester = super.getById(id);
        if(semester == null || semester.getIsDeleted().equals(BaseVo.DELETED)) {
            return Result.error("学期不存在或已删除");
        }
        semester.setIsDeleted(BaseVo.DELETED);
        semester.setUpdatedBy(userDto.getId());

        boolean b = super.updateById(semester);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }


}
