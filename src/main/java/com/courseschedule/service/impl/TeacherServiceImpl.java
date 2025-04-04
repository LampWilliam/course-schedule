package com.courseschedule.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.entity.Classes;
import com.courseschedule.entity.Student;
import com.courseschedule.entity.Teacher;
import com.courseschedule.entity.TeacherComment;
import com.courseschedule.mapper.ClassesMapper;
import com.courseschedule.mapper.TeacherCommentMapper;
import com.courseschedule.mapper.TeacherMapper;
import com.courseschedule.service.ClassesService;
import com.courseschedule.service.TeacherService;
import com.courseschedule.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
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

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private TeacherCommentMapper teacherCommentMapper;

    @Override
    public Result getList() {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<Teacher>()
                .eq(Teacher::getIsDeleted, BaseVo.NOT_DELETED);
        List<Teacher> list = super.list(wrapper);
        return Result.success("成功返回教师表", list);
    }

    @Override
    public void add(Teacher teacher) {
        teacher.setCreatedTime(LocalDateTime.now());
        teacher.setUpdatedTime(LocalDateTime.now());

        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        teacher.setCreatedBy(userDto.getId());
        teacherMapper.add(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacher.setUpdatedTime(LocalDateTime.now());
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        teacher.setCreatedBy(userDto.getId());
        teacherMapper.update(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherMapper.delete(id);
    }

    @Override
    public Teacher findByTeacherName(String teacherName) {
        Teacher tea = teacherMapper.findByTeacherName(teacherName);
        return tea;
    }

    @Override
    public String getClassNo(String teacherId) {
        return teacherMapper.getClassNo(teacherId);
    }

    @Override
    public List<TeacherComment> getTeacherComment(String teacherNo) {
        return teacherCommentMapper.getTeacherComment(teacherNo);
    }

}
