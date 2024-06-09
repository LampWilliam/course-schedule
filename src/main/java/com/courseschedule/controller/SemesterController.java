package com.courseschedule.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.lang.ServerResponse;
import com.courseschedule.common.vo.SemesterVo;
import com.courseschedule.entity.Semester;
import com.courseschedule.entity.Task;
import com.courseschedule.service.SemesterService;
import com.courseschedule.service.TaskService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2024-05-19
 */
@RestController
@RequestMapping("/semester")
public class SemesterController {
    @Resource
    private SemesterService semesterService;

    // @RequiresAuthentication
    @GetMapping("/getList")
    public Result getList() {
        return semesterService.getList();
    }

    /**
     * 获得学期集合
     */
    @GetMapping("/semester")
    public Result queryAllSemester() {
        LambdaQueryWrapper<Semester> wrapper = new LambdaQueryWrapper<Semester>().select(Semester::getSemesterName);
        List<Semester> list = semesterService.list(wrapper);

        Set<String> set = list.stream().map(Semester::getSemesterName).collect(Collectors.toSet());

        return Result.success("成功返回学期集合", set);
    }

    @RequiresAuthentication
    @PostMapping
    public Result add(@RequestBody @Validated SemesterVo vo) {
        return semesterService.add(vo);
    }

    @RequiresAuthentication
    @PutMapping
    public Result update(@RequestBody @Validated SemesterVo vo) {
        return semesterService.update(vo);
    }

    @RequiresAuthentication
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return semesterService.delete(id);
    }
}
