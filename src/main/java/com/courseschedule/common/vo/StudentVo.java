package com.courseschedule.common.vo;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户前端接收类
 */
@Data
public class StudentVo extends BaseVo implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String newPassword;

    private String telephone;


}
