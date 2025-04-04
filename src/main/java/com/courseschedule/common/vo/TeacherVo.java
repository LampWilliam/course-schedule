package com.courseschedule.common.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    private char gender;

    private String number;

    private LocalDateTime birthday;

    private String password;
}
