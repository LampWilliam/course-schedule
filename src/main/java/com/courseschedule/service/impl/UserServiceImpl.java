package com.courseschedule.service.impl;

import com.courseschedule.entity.User;
import com.courseschedule.mapper.UserMapper;
import com.courseschedule.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户(管理员) 服务实现类
 * </p>
 *
 * @author 
 * @since 2024-05-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
