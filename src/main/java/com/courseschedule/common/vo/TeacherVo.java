package com.courseschedule.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeacherVo extends BaseVo implements Serializable {
    /**
     * 教师id
     */
    private Long id;

    /**
     * 教师编号
     */
    private String classNo;

    /**
     * 教师名称
     */
    private String className;
}
