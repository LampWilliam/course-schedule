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

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * [output]课程表
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
public class Timetable implements Serializable {

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
    @Excel(name = "班级")
    private String classNo;

    /**
     * 课程编号
     */
    @Excel(name = "课程")
    private String courseNo;

    /**
     * 讲师编号
     */
    @Excel(name = "讲师")
    private String teacherNo;

    /**
     * 教室编号
     */
    @Excel(name = "教室")
    private String roomNo;

    /**
     * 上课时间(大节)
     */
    @Excel(name = "上课时间（大节）")
    private Integer timeslot;

    /**
     * 开始周
     */
    @Excel(name = "开始周")
    private Integer startWeek;

    /**
     * 结束周
     */
    @Excel(name = "结束周")
    private Integer endWeek;

    /**
     * 单双周 0代表非单双周 1代表单周 2代表双周
     */
    @Excel(name = "单双周")
    private Integer biweekly;

    /**
     * 关联学期Id
     */
    @Excel(name = "学期")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long semesterId;


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
