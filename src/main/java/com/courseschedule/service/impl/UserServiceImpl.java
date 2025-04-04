package com.courseschedule.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.common.lang.Result;
import com.courseschedule.common.vo.BaseVo;
import com.courseschedule.common.vo.UserVo;
import com.courseschedule.entity.User;
import com.courseschedule.mapper.UserMapper;
import com.courseschedule.service.TeacherService;
import com.courseschedule.service.UserService;
import com.courseschedule.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

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
    @Autowired
    private JwtUtils jwtUtils;


    @Autowired
    private TeacherService teacherService;

    @Override
    public Result login(HttpServletResponse response, UserVo vo) {
        User user = super.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, vo.getUsername())
                .eq(User::getIsDeleted, BaseVo.NOT_DELETED));
        if (user == null) {
            return Result.error("用户不存在");
        }
        /**
         * 如果数据库内密码是加密的，则需要如下行。为了方便测试就不加密了
         * !user.getPassword().equals(SecureUtil.md5(vo.getPassword()))
         */
        if(user != null) {
            if(!user.getPassword().equals(vo.getPassword())){
                return Result.error("账号或密码错误");
            }

            String jwt = jwtUtils.generateToken(user.getId());

            // 为了后续的jwt的延期，所以把jwt放在header上
            response.setHeader("Authorization", jwt);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");

            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(user, userDto);
            userDto.setPassword("");
            return Result.success("登录成功", MapUtil.builder()
                    .put("userDto", userDto)
                    .map());
//            return Result.success("登录成功", jwt);
        }

        return Result.success("登录成功");
    }

    @Override
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success("退出成功");
    }
}
