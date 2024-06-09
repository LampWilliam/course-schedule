package com.courseschedule.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class CourseVo extends BaseVo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程属性
     */
    private String courseAttr;

    /**
     * 备注
     */
    private String remark;
}
