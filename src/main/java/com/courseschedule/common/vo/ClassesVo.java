package com.courseschedule.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClassesVo extends BaseVo implements Serializable {
    /**
     * 班级id
     */
    private Long id;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班级人数
     */
    private Integer size;
}
