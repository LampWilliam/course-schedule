package com.courseschedule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [input]教室
 * </p>
 *
 * @author 
 * @since 2024-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教室id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 教室编号
     */
    private String roomNo;

    /**
     * 教室名称
     */
    private String roomName;

    /**
     * 所在教学楼编号
     */
    private String teachbuildNo;

    /**
     * 教室人数容量
     */
    private Integer capacity;

    /**
     * 教室属性 01理论 02实验 03实践 04体育课
     */
    private String attr;

    /**
     * 备注
     */
    private String remark;


}
