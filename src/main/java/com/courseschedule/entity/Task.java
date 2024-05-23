package com.courseschedule.entity;

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

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * [input]教学任务表(包括教学大纲，教师选课)
 * </p>
 *
 * @author
 * @since 2024-05-10
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class) //适应前端--字段类型从数值转换为字符串
    @TableId(value = "id", type = IdType.ASSIGN_ID) //雪花算法
    private Long id;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 教师编号
     */
    private String teacherNo;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 课程属性 01理论 02实验 03实践 04体育课
     */
    private String courseAttr;

    /**
     * 周数
     */
    private Integer weeksSum;

    /**
     * 周学时 - 必须是2的倍数
     */
    private Integer weeksNumber;

    /**
     * 开始周
     */
    private String startWeek;

    /**
     * 结束周
     */
    private String endWeek;

    /**
     * 单双周 0代表非单双周 1代表单周 2代表双周
     */
    private String biweekly;

    /**
     * 所在教学区域(教学楼、体育场等)编号
     */
    private String areaNo;

    /**
     * 同时上课班级数(>1为大班课)
     */
    private String classCount;

    /**
     * 班级名
     */
    private String className;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 课程名
     */
    private String courseName;



    /**
     * 创建人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedTime;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;
}
