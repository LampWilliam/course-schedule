package com.courseschedule.service;

import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.UserVo;
import com.courseschedule.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户(管理员) 服务类
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
public interface UserService extends IService<User> {

    Result login(HttpServletResponse response, UserVo vo);

    Result logout();
}
