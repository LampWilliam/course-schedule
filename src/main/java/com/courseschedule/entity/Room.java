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
 * [input]教室
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
//教室
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教室id
     */
    @JsonSerialize(using = ToStringSerializer.class) //适应前端--字段类型从数值转换为字符串
    @TableId(value = "id", type = IdType.ASSIGN_ID) //雪花算法
    private Long id;

    /**
     * 教室编号
     */
    @Excel(name = "教室编号")
    private String roomNo;

    /**
     * 教室名称
     */
    @Excel(name = "教室名称")
    private String roomName;

    /**
     * 所在教学区域(教学楼、体育场等)编号
     */
    @Excel(name = "所在教学区域编号")
    private String areaNo;

    /**
     * 教学区域名
     */
    @Excel(name = "教学区域名")
    private String areaName;

    /**
     * 教室人数容量
     */
    @Excel(name = "教室人数容量")
    private Integer capacity;

    /**
     * 教室属性 01理论 02实验 03实践 04体育课
     */
    @Excel(name = "教室属性")
    private String attr;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;


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
