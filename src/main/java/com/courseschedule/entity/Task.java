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
     * 是否采用班级固定教室
     */
    private boolean ifFixRoom;

    /**
     * 是否排课
     */
    private boolean ifScheduled;


    /**
     * 教学班编号
     */
    @Excel(name = "教学班编号")
    private String classNo;

    /**
     * 教师编号
     */
    @Excel(name = "教师工号")
    private String teacherNo;

    /**
     * 课程编号
     */
    @Excel(name = "课程编号")
    private String courseNo;

    /**
     * 学时类型
     */
    @Excel(name = "学时类型")
    private String courseAttrName;

    /**
     * 学时类型编号 01理论 02实验 03实践 04体育课
     */
    @Excel(name = "学时类型编号")
    private String courseAttr;

    /**
     * 周数
     */
    @Excel(name = "周数")
    private Integer weeksSum;

    /**
     * 周学时 - 必须是2的倍数
     */
    @Excel(name = "周学时")
    private Integer weeksNumber;

    /**
     * 开始周
     */
    @Excel(name = "开始周")
    private String startWeek;

    /**
     * 结束周
     */
    @Excel(name = "结束周")
    private String endWeek;

    /**
     * 单双周 0代表非单双周 1代表单周 2代表双周
     */
    @Excel(name = "单双周")
    private String biweekly;


    /**
     * 连排节次，只能有2或4
     */
    @Excel(name = "连排节次")
    private String duration;

    /**
     * 所在教学区域
     */
    @Excel(name = "所在教学区域")
    private String roomArea;

    /**
     * 所在教学区域(教学楼、体育场等)编号
     */
    @Excel(name = "所在教学区域编号")
    private String areaNo;

    /**
     * 同时上课班级数(>1为大班课)
     */
    @Excel(name = "同时上课班级")
    private String classCount;

    /**
     * 班级名
     */
    @Excel(name = "班级名")
    private String className;

    /**
     * 教师姓名
     */
    @Excel(name = "教师名")
    private String teacherName;

    /**
     * 课程名
     */
    @Excel(name = "课程名")
    private String courseName;

    /**
     * 课程性质
     */
    @Excel(name = "课程性质")
    private String taskAttrName;

    /**
     * 课程性质编号 01必修课 02专业拓展课 03专业选修课 04公共必修课 05公共选修课 06公共基础课
     */
    @Excel(name = "课程性质编号")
    private String taskAttr;

    /**
     * 开课院系
     */
    @Excel(name = "开课院系")
    private String courseDepartment;

    /**
     * 开课院系编号
     */
    @Excel(name = "开课院系编号")
    private String courseDepartmentNo;

    /**
     * 课程学分
     */
    @Excel(name = "课程学分")
    private String courseScore;


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

    public interface Add extends Default {

    }

    public interface Update extends Default {

    }
}
