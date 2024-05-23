package com.courseschedule.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.courseschedule.common.dto.UserDto;
import com.courseschedule.entity.User;
import com.courseschedule.service.UserService;
import com.courseschedule.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


/**
 * UserRealm是shiro进行登录或者权限校验的逻辑所在，是核心了
 *
 * 重写3个方法，分别是：
 * supports：为了让realm支持jwt的凭证校验
 * doGetAuthorizationInfo：权限校验
 * doGetAuthenticationInfo：登录认证校验
 */
@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;


    /**
     * 为了让realm支持jwt的凭证校验
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权（登录之后是否具有某些权限）
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取当前登录的user
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();

        // 这个放 用户权限的CODE
        Set<String> permissionCode = new HashSet<>();
        // 这个放用户角色的名字
        Set<String> roleName = new HashSet<>();

        switch (userDto.getType()) {
            case 0: roleName.add("admin"); break;
            case 1: roleName.add("superAdmin"); break;
            case 2: roleName.add("teacher"); break;// 这个不打算做 非必须
            case 3: roleName.add("student"); break;// 这个不打算做 非必须
        }

        // 将 角色名称级权限的名称放进 info中 以供 shiroConfig 拦截
        info.setRoles(roleName);
        return info;
    }

    /**
     * 认证（登录） <br />
     * 通过jwt获取到用户信息，判断用户的状态，最后异常就抛出对应的异常信息。
     * 否则封装成SimpleAuthenticationInfo返回给shiro。
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwt = (JwtToken) token; // 强转superclass转subclass
        log.info("jwt----------------->{}", jwt);
        String userId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if (user == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        // if (user.getStatus() == 1) {
        //     throw new LockedAccountException("账户已注销！"); // 账户已被锁定
        // }

        UserDto userDto = new UserDto();
        BeanUtil.copyProperties(user, userDto);

        // 根据userType查询不同角色信息，放入对象
        // if(userDto.getUserType() == 1){ // teacher
        //     userDto.setTeacherInfo(teacherInfoService.getOne(new QueryWrapper<TeacherInfo>()
        //             .eq("relation_user_id",userDto.getId())));
        // } else if (userDto.getUserType() == 2){ // student
        //     userDto.setStudentInfo(studentInfoService.getOne(new QueryWrapper<StudentInfo>()
        //             .eq("relation_user_id",userDto.getId())));
        //     Long classId = userDto.getStudentInfo().getRelationClassId();
        //     userDto.setClassName(classInfoService.getById(classId).getClassName());
        // }

        log.info("profile----------------->{}", userDto.toString());
        return new SimpleAuthenticationInfo(userDto, jwt.getCredentials(), getName());
        // Credentials正确就通过，否则抛异常。这里jwt.getCredentials本来是user.getUserPassword
        // getName()获取当前的类名，也就是UserDto
    }
}
