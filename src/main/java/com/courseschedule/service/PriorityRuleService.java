package com.courseschedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.courseschedule.entity.PriorityRule;

import java.util.List;

public interface PriorityRuleService extends IService<PriorityRule> {
    //获取优先禁排列表
    List<PriorityRule> getList();

    void add(PriorityRule priorityRule);

    void update(PriorityRule priorityRule);

    void delete(Integer id);
}
