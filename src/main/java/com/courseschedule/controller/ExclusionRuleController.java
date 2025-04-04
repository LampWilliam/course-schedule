package com.courseschedule.controller;

import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.ExclusionRule;
import com.courseschedule.service.ExclusionRuleService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exlusionRule")
public class ExclusionRuleController {

    @Autowired
    private ExclusionRuleService exclusionRuleService;

    @GetMapping()
    public Result getExclusionRuleList() {
        List<ExclusionRule> exclusionRules = exclusionRuleService.getList();
        return Result.success("查询成功", exclusionRules);
    }

    @RequiresAuthentication
    @PostMapping()
    public Result addExclusionRule(@RequestBody @Validated(ExclusionRule.Add.class) ExclusionRule exclusionRule) {
        exclusionRuleService.add(exclusionRule);
        return Result.success("添加成功");
    }

    @RequiresAuthentication
    @PutMapping()
    public Result updateExclusionRule(@RequestBody @Validated(ExclusionRule.Update.class) ExclusionRule exclusionRule){
        exclusionRuleService.update(exclusionRule);
        return Result.success("修改成功");
    }

    @RequiresAuthentication
    @DeleteMapping("/{id}")
    public Result deleteExclusionRule(@PathVariable("id") Integer id){
        exclusionRuleService.delete(id);
        return Result.success("删除成功");
    }
}
