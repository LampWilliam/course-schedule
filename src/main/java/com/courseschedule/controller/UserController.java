package com.courseschedule.controller;


import com.courseschedule.common.lang.Result;
import com.courseschedule.entity.User;
import com.courseschedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户(管理员) 前端控制器
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/index")
    public Result index() {
        User user = userService.getById(1L);
        return Result.success("操作成功", user);
    }
}
