package com.courseschedule.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.groups.Default;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StudentScore {
    private static final long serialVersionUID=1L;

    @JsonSerialize(using = ToStringSerializer.class) //适应前端--字段类型从数值转换为字符串
    @TableId(value = "id", type = IdType.ASSIGN_ID) //雪花算法
    private Long id;

    /**
     * 学生姓名
     */
    @Excel(name = "学生姓名")
    private String studentName;

    /**
     * 班级编号
     */
    @Excel(name = "班级编号")
    private String classNo;

    /**
     * 课程
     */
    @Excel(name = "课程")
    private String courseName;

    /**
     * 课程分数
     */
    @Excel(name = "课程分数")
    private int score;

    public interface Add extends Default {

    }

    public interface Update extends Default {

    }

}
