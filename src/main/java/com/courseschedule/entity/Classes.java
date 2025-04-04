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
 * 班级
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
//班级
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级id
     */
    @JsonSerialize(using = ToStringSerializer.class) //适应前端--字段类型从数值转换为字符串
    @TableId(value = "id", type = IdType.ASSIGN_ID) //雪花算法
    private Long id;

    /**
     * 班级编号
     */
    @Excel(name = "班级编号")
    private String classNo;

    /**
     * 班级名称
     */
    @Excel(name = "班级名称")
    private String className;

    /**
     * 班级人数
     */
    @Excel(name = "班级人数")
    private Integer size;

    /**
     * 固定教室编号
     */
    @Excel(name = "固定教室编号")
    private String fixRoomNo;

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
