package com.courseschedule.controller;

import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.TeacherRule;
import com.courseschedule.service.TeacherRuleService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacherRule")
public class TeacherRuleController {

    @Autowired
    private TeacherRuleService teacherRuleService;

    @GetMapping()
    public Result getTeacherRuleList() {
        List<TeacherRule> teacherRules = teacherRuleService.getList();
        return Result.success("查询成功", teacherRules);
    }

    @RequiresAuthentication
    @PostMapping()
    public Result addTeacherRule(@RequestBody @Validated(TeacherRule.Add.class) TeacherRule teacherRule) {
        teacherRuleService.add(teacherRule);
        return Result.success("添加成功");
    }

    @RequiresAuthentication
    @PutMapping()
    public Result updateTeacherRule(@RequestBody @Validated(TeacherRule.Update.class) TeacherRule teacherRule){
        teacherRuleService.update(teacherRule);
        return Result.success("修改成功");
    }

    @RequiresAuthentication
    @DeleteMapping("/{id}")
    public Result deleteTeacherRule(@PathVariable("id") Integer id){
        teacherRuleService.delete(id);
        return Result.success("删除成功");
    }
}
