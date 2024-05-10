package com.courseschedule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [input]教学任务表(包括教学大纲，教师选课)
 * </p>
 *
 * @author 
 * @since 2024-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 教师编号
     */
    private String teacherNo;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 课程属性 01理论 02实验 03实践 04体育课
     */
    private String courseAttr;

    /**
     * 班级人数
     */
    private Integer size;

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
    private Integer startWeek;

    /**
     * 结束周
     */
    private Integer endWeek;

    /**
     * 单双周 0代表非单双周 1代表单周 2代表双周
     */
    private Integer biBiweekly;

    /**
     * 同时上课班级数(>1为大班课)
     */
    private Integer classCount;

    /**
     * 学分
     */
    private Integer point;


}
