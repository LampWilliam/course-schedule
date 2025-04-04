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
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ExclusionRule {
    @JsonSerialize(using = ToStringSerializer.class) //适应前端--字段类型从数值转换为字符串
    @TableId(value = "id", type = IdType.ASSIGN_ID) //雪花算法
    private Long id;

    /**
     * 禁排节次(大节)
     */
    @Excel(name = "禁排节次（大节）")
    private Integer timeslot;

    /**
     * 课程编号
     */
    @Excel(name = "课程编号")
    private String courseNo;

    /**
     * 课程名
     */
    @Excel(name = "课程名")
    private String courseName;

    /**
     * 班级编号
     */
    @Excel(name = "班级")
    private String classNo;

    /**
     * 班级名
     */
    @Excel(name = "班级名")
    private String className;

    /**
     * 讲师编号
     */
    @Excel(name = "讲师")
    private String teacherNo;

    /**
     * 教师名
     */
    @Excel(name = "教师名")
    private String teacherName;

    /**
     * 教室编号
     */
    @Excel(name = "教室")
    private String roomNo;

    /**
     * 教室名
     */
    @Excel(name = "教室名")
    private String roomName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedTime;

    public interface Add extends Default {

    }

    public interface Update extends Default {

    }
}
