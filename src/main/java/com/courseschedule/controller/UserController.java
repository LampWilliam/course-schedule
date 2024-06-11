package com.courseschedule.controller;


import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.UserVo;
import com.courseschedule.entity.User;
import com.courseschedule.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
        return Result.success("[测试]成功返回id为1的user", user);
    }

    @PostMapping("/login")
    public Result login(HttpServletResponse response, @Validated @RequestBody UserVo vo) {
        return userService.login(response, vo);
    }

    @GetMapping("/logout")
    public Result logout() {
        return userService.logout();
    }
}
