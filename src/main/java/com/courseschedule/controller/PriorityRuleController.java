package com.courseschedule.controller;

import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.PriorityRule;
import com.courseschedule.service.PriorityRuleService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priorityRule")
public class PriorityRuleController {
    @Autowired
    private PriorityRuleService priorityRuleService;

    @GetMapping()
    public Result getPriorityRuleList() {
        List<PriorityRule> priorityRules = priorityRuleService.getList();
        return Result.success("查询成功", priorityRules);
    }

    @RequiresAuthentication
    @PostMapping()
    public Result addPriorityRule(@RequestBody @Validated(PriorityRule.Add.class) PriorityRule priorityRule) {
        priorityRuleService.add(priorityRule);
        return Result.success("添加成功");
    }

    @RequiresAuthentication
    @PutMapping()
    public Result updatePriorityRule(@RequestBody @Validated(PriorityRule.Update.class) PriorityRule priorityRule){
        priorityRuleService.update(priorityRule);
        return Result.success("修改成功");
    }

    @RequiresAuthentication
    @DeleteMapping("/{id}")
    public Result deletePriorityRule(@PathVariable("id") Integer id){
        priorityRuleService.delete(id);
        return Result.success("删除成功");
    }

//    @GetMapping()
//    public Result getPriorityRuleList() {
//        List<PriorityRule> priorityRules = priorityRuleService.getList();
//        return Result.success("查询成功", priorityRules);
//    }

}
