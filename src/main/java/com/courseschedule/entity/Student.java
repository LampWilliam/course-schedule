package com.courseschedule.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.groups.Default;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Student implements Serializable {
    private static final long serialVersionUID=1L;

    @JsonSerialize(using = ToStringSerializer.class) //适应前端--字段类型从数值转换为字符串
    @TableId(value = "id", type = IdType.ASSIGN_ID) //雪花算法
    private Long id;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String gender;

    /**
     * 生日
     */
    @Excel(name = "出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    /**
     * 电话号码
     */
    @Excel(name = "电话号码")
    private String number;

    /**
     * 学号
     */
    @Excel(name = "学号")
    private String studentNo;

    /**
     * 班级编号
     */
    @Excel(name = "班级编号")
    private String classNo;

    /**
     * 密码
     */
    @Excel(name = "密码")
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;

    public interface Add extends Default {

    }

    public interface Update extends Default {

    }

}
